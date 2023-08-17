package com.dxc.system;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.FileReaderProvider;
import com.dxc.file.writer.FileWriterProvider;
/**
 * Converter is a class that contains the supported file formats, instances of reader and writer
 * for the needed formats for conversion.
 * Its' task is to execute the whole converting process and throw exceptions when this is not possible.
 */
public class Converter {
	
	public FileReaderProvider fileReader;
	public FileWriterProvider fileWriter;
	/**
	 * Creates an instance of the converter
	 * @param fileReader Reader class for the input file
	 * @param fileWriter Writer class for the output file
	 */
	public Converter(FileReaderProvider fileReader, FileWriterProvider fileWriter) {
		this.fileReader = fileReader;
		this.fileWriter = fileWriter;
	}
	/**
	 * Method that executes the whole process of converting from one file format to another.
	 * If the output file does not exists, it creates a new file in the user's system and writes to it.
	 * @param inputFilePath path of the input file
	 * @param outputFilePath path of the output file
	 */
	public boolean convert(String inputFilePath, String outputFilePath) {
		ArrayList<String> readFile = fileReader.readFile(inputFilePath);

		return fileWriter.write(readFile, outputFilePath);
	}
	/**
	 * Starting point of the program.
	 * Creates instances of the needed reader and writer for the given file formats.
	 * Creates an instance of the converter, which executes the conversion process.
	 * @param args input file path, output file path, configuration file path, input file format and
	 * output file format separated by spaces.
	 */
	
	

}

