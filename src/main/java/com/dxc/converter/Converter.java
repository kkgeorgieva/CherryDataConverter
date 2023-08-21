package com.dxc.converter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.Decoder;
import com.dxc.file.writer.Encoder;
/**
 * Converter is a class that contains the supported file formats, instances of reader and writer
 * for the needed formats for conversion.
 * Its' task is to execute the whole converting process and throw exceptions when this is not possible.
 */
public class Converter {
	
	public Decoder decoder;
	public Encoder encoder;
	/**
	 * Creates an instance of the converter
	 * @param fileReader Reader class for the input file
	 * @param fileWriter Writer class for the output file
	 */
	public Converter(Decoder decoder, Encoder encoder) {
		this.decoder = decoder;
		this.encoder = encoder;
	}
	/**
	 * Method that executes the whole process of converting from one file format to another.
	 * If the output file does not exists, it creates a new file in the user's system and writes to it.
	 * @param inputFilePath path of the input file
	 * @param outputFilePath path of the output file
	 */
	public void convert() {
		String currentUnit = decoder.getUnit();
		
		while (currentUnit != "") {
			encoder.encodeUnit(currentUnit);
			currentUnit = decoder.getUnit();
		}
		
//		return fileWriter.write(readFile, outputFilePath);
	}
}
