package com.dxc.system;

import java.io.Reader;
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

		ConfigProcessor.parseConfig(configFile);

		// Read the input file type and output file type, and create appropriate reader
		// and writer.

		CSVFileReader reader = new CSVFileReader(ConfigProcessor.getByCategory(FileReaderProvider.getConfigCategory()));
		FWFileWriter writer = new FWFileWriter(ConfigProcessor.getByCategory(FileWriterProvider.getConfigCategory()));

		Converter cvt = new Converter(reader, writer);

		cvt.convert(inputFile, outputFile);

//		for (Property pr : parseConfig) {
//			System.out.println(pr.toString());
//		}
	}

}
