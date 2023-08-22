package com.dxc.converter;

import com.dxc.file.reader.Decoder;
import com.dxc.file.writer.Encoder;
import com.dxc.file.writer.FileWriterInterface;
import com.dxc.file.writer.OutputWriter;

/**
 * Converter is a class that contains the supported file formats, instances of
 * reader and writer for the needed formats for conversion. Its' task is to
 * execute the whole converting process and throw exceptions when this is not
 * possible.
 */
public class Converter {

	public Decoder decoder;
	public Encoder encoder;

	/**
	 * Creates an instance of the converter
	 * 
	 * @param fileReader Reader class for the input file
	 * @param fileWriter Writer class for the output file
	 */
	public Converter(Decoder decoder, Encoder encoder) {
		this.decoder = decoder;
		this.encoder = encoder;
	}

	/**
	 * Method that executes the whole process of converting from one file format to
	 * another. If the output file does not exists, it creates a new file in the
	 * user's system and writes to it.
	 * 
	 * @param inputFilePath  path of the input file
	 * @param outputFilePath path of the output file
	 */
	public boolean convert() {
		String currentUnit = decoder.getUnit();

		try {
			System.out.println("Converting started...");
			while (currentUnit != "") {
				encoder.encodeUnit(currentUnit);
				currentUnit = decoder.getUnit();
			}
			
		} catch (Exception e) {
			System.out.println("Converting failed!");
			return false;
		}

		System.out.println("Converting successful!");
		return true;

//		return fileWriter.write(readFile, outputFilePath);
	}
}
