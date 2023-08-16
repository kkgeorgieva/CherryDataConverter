package com.dxc.system;

import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.CSVFileReader;
import com.dxc.file.reader.FileReaderProvider;
import com.dxc.file.writer.CSVFileWriter;
import com.dxc.file.writer.FWFileWriter;
import com.dxc.file.writer.FileWriterProvider;

public class Converter {
	// The converter will store the supported file types
	// Throws exception when the requested file type is not supported by the
	// converter

	public FileReaderProvider fileReader;
	public FileWriterProvider fileWriter;

	public Converter(FileReaderProvider fileReader, FileWriterProvider fileWriter) {
		this.fileReader = fileReader;
		this.fileWriter = fileWriter;
	}

	public void convert(String inputFilePath, String outputFilePath) {
		ArrayList<String> readFile = fileReader.readFile(inputFilePath);

		fileWriter.write(readFile, outputFilePath);
	}

	public static void main(String[] args) {

		// Implementation for case with Properties Class
		String inputFile = args[0];
		String outputFile = args[1];
		String configFile = args[2];
		String inputFileType = args[3];
		String outputFileType = args[4];
		
//		String inputFile = "C:\\ws\\Test.csv";
//		String outputFile = "C:\\ws\\Output.csv";
//		String configFile = "C:\\ws\\configTemplate.yaml";
//		String inputFileType = "CSV";
//		String outputFileType = "FW";

		ConfigProcessor.parseConfig(configFile);

		
		FileReaderProvider fileReader = null;
		FileWriterProvider fileWriter = null;
		
		// Create a generic Class object for any class
		Class<?> fileReaderClass;
		Class<?> fileWriterClass;
		try {
			fileReaderClass = Class.forName("com.dxc.file.reader." + inputFileType + "FileReader");
			// Get a generic Constructor object for any constructor
			Constructor<?> fileReaderConstructor = fileReaderClass.getConstructor(List.class);
			
			// Get the list of properties from the config processor
			List<Property> properties = ConfigProcessor.getByCategory(FileReaderProvider.getConfigCategory());
			
			// Create a new instance of any class with the list of properties
			fileReader = (FileReaderProvider) fileReaderConstructor.newInstance(properties);
			
			
			fileWriterClass = Class.forName("com.dxc.file.writer." + outputFileType + "FileWriter");
			// Get a generic Constructor object for any constructor
			Constructor<?> fileWriterConstructor = fileWriterClass.getConstructor(List.class);
			
			// Get the list of properties from the config processor
			List<Property> propertiesWriter = ConfigProcessor.getByCategory(FileWriterProvider.getConfigCategory());
			
			// Create a new instance of any class with the list of properties
			fileWriter = (FileWriterProvider) fileWriterConstructor.newInstance(propertiesWriter);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		Converter cvt = new Converter(fileReader, fileWriter);

		cvt.convert(inputFile, outputFile);

//		for (Property pr : parseConfig) {
//			System.out.println(pr.toString());
//		}
	}

}
