package com.dxc.main.app;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.FileReaderProvider;
import com.dxc.file.writer.FileWriterProvider;
import com.dxc.system.Converter;

public class ConverterApplication {
	
	private static Logger logger = LogManager.getLogger(Converter.class);
	
	public static void main(String[] args) {
		

		// Implementation for case with Properties Class
//		String inputFile = args[0];
//		String outputFile = args[1];
//		String configFile = args[2];
//		String inputFileType = args[3];
//		String outputFileType = args[4];
		
		String inputFile = "C:\\ws\\Test.csv";
		String outputFile = "C:\\ws\\Output.csv";
		String configFile = "C:\\ws\\configTemplate.yaml";
		String inputFileType = "CSV";
		String outputFileType = "FW";

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
			
			logger.info("Created an instance of " + "com.dxc.file.reader." + inputFileType + "FileReader");
			
			fileWriterClass = Class.forName("com.dxc.file.writer." + outputFileType + "FileWriter");
			// Get a generic Constructor object for any constructor
			Constructor<?> fileWriterConstructor = fileWriterClass.getConstructor(List.class);
			
			// Get the list of properties from the config processor
			List<Property> propertiesWriter = ConfigProcessor.getByCategory(FileWriterProvider.getConfigCategory());
			
			// Create a new instance of any class with the list of properties
			fileWriter = (FileWriterProvider) fileWriterConstructor.newInstance(propertiesWriter);
			
			logger.info("Created an instance of " + "com.dxc.file.writer." + outputFileType + "FileWriter");
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error(e.getMessage());
		}



		Converter cvt = new Converter(fileReader, fileWriter);

		cvt.convert(inputFile, outputFile);
	}

}
