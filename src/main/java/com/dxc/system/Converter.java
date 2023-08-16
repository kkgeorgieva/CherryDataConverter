package com.dxc.system;
import java.util.ArrayList;
import java.util.List;

import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.FileReaderProvider;
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
		ArrayList<String> readFile = fileReader.readFile(outputFilePath);

		fileWriter.write(readFile, outputFilePath);
	}

	public static void main(String[] args) {

		// Implementation for case with Properties Class
		//String inputFile = args[0];
		//String outputFile = args[1];
		//String configFile = args[2];

		List<Property> parseConfig = ConfigProcessor.parseConfig("configTemplate.yaml");

		// Read the input file type and output file type, and create appropriate reader
		// and writer.

		// Converter cvt = new Converter(new CSVFileReader(cf.getPropsList()), new
		// CSVFileWriter(cf.getPropsList()));

		// cvt.convert(inputFile, outputFile);

		for (Property pr : parseConfig) {
			System.out.println(pr.toString());
		}
	}

}
