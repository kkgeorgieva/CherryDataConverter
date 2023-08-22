package com.dxc.main.app;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.converter.Converter;
import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.CSVDecoder;
import com.dxc.file.reader.Decoder;
import com.dxc.file.reader.FileReaderInterface;
import com.dxc.file.reader.InputReader;
import com.dxc.file.writer.CSVEncoder;
import com.dxc.file.writer.Encoder;
import com.dxc.file.writer.FileWriterInterface;
import com.dxc.file.writer.OutputWriter;

public class ConverterApplication {
	
	private static Logger logger = LogManager.getLogger(Converter.class);
	
	/**
	 * Starting point of the program.
	 * Creates instances of the needed reader and writer for the given file formats.
	 * Creates an instance of the converter, which executes the conversion process.
	 * @param args input file path, output file path, configuration file path, input file format and
	 * output file format separated by spaces.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		if (args.length < 5) {
			System.out.println("ERROR! Make sure you have included all the parameters in the following order:"
					+ " inputFilePath, outputFilePath, configFilePath, inputFileType, outputFileType");
			logger.error("Missing parameters from the application call.");
			
		}

//		 Implementation for case with Properties Class
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

		
		FileReaderInterface reader = new InputReader(inputFile);
		FileWriterInterface writer = new OutputWriter(outputFile);
		
		
		Decoder decoder = null;
		Encoder encoder = null;
		Class<?> decoderClass;
		Class<?> encoderClass;
		try {
			decoderClass = Class.forName("com.dxc.file.reader." + inputFileType + "Decoder");
			// Get a generic Constructor object for any constructor
			Constructor<?> decoderConstructor = decoderClass.getConstructor(List.class, FileReaderInterface.class);
			
			
			// Get the list of properties from the config processor
			List<Property> properties = ConfigProcessor.getByCategory(reader.getConfigCategory());
			
			// Create a new instance of any class with the list of properties
			decoder = (Decoder) decoderConstructor.newInstance(properties, reader);
			
			logger.info("Created an instance of " + "com.dxc.file.reader." + inputFileType + "Decoder");
			
			encoderClass = Class.forName("com.dxc.file.writer." + outputFileType + "Encoder");
			// Get a generic Constructor object for any constructor
			Constructor<?> encoderConstructor = encoderClass.getConstructor(List.class, FileWriterInterface.class);
			
			// Get the list of properties from the config processor
			List<Property> propertiesWriter = ConfigProcessor.getByCategory(writer.getConfigCategory());
			
			// Create a new instance of any class with the list of properties
			encoder = (Encoder) encoderConstructor.newInstance(propertiesWriter, writer);
			
			logger.info("Created an instance of " + "com.dxc.file.writer." + outputFileType + "FileWriter");
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error(e.getMessage());
		}
		
		Converter cvt = new Converter(decoder, encoder);

		cvt.convert();
	}

}
