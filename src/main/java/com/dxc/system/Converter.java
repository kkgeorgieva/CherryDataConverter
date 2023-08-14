package com.dxc.system;

import java.util.ArrayList;

import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.CSVFileReader;
import com.dxc.file.reader.FileReaderInterface;
import com.dxc.file.writer.CSVFileWriter;
import com.dxc.file.writer.FileWriterInterface;

public class Converter {
	// The converter will store the supported file types
	//Throws exception when the requested file type is not supported by the converter
	
	public FileReaderInterface fileReader;
	public FileWriterInterface fileWriter;
	
	public Converter(FileReaderInterface fileReader, FileWriterInterface fileWriter) {
		this.fileReader = fileReader;
		this.fileWriter = fileWriter;
	}
	
	public void convert(String inputFilePath, String outputFilePath) {
		ArrayList<String> readFile = fileReader.readFile(outputFilePath);
		
		fileWriter.write(readFile, outputFilePath);
	}
	
	public static void main(String[] args) {
		String inputFile = args[0];
		String outputFile = args[1];
		String configFile = args[2];
		
		ConfigProcessor cf = new ConfigProcessor(configFile);
		
		//Read the input file type and output file type, and create appropriate reader and writer.

		Converter cvt = new Converter(new CSVFileReader(cf.getPropsList()), new CSVFileWriter(cf.getPropsList()));
		
		cvt.convert(inputFile, outputFile);
		
//		
//		for (Property pr : cf.getPropsList()) {
//			System.out.println(pr.toString());
//		}
	}

}
