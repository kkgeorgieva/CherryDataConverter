package com.dxc.cherry.converter;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.cherry.converter.config.ConfigProcessor;
import com.dxc.cherry.converter.config.Property;
import com.dxc.cherry.converter.input.CSVDecoder;
import com.dxc.cherry.converter.input.Decoder;
import com.dxc.cherry.converter.input.InputFactoryInterface;
import com.dxc.cherry.converter.input.InputReader;
import com.dxc.cherry.converter.input.InputReaderInterface;
import com.dxc.cherry.converter.output.CSVEncoder;
import com.dxc.cherry.converter.output.Encoder;
import com.dxc.cherry.converter.output.OutputFactoryInterface;
import com.dxc.cherry.converter.output.OutputWriter;
import com.dxc.cherry.converter.output.OutputWriterInterface;

public class ConverterApplication {

	private static Logger logger = LogManager.getLogger(Converter.class);

	/**
	 * Starting point of the program. Creates instances of the needed reader and
	 * writer for the given file formats. Creates an instance of the converter,
	 * which executes the conversion process.
	 * 
	 * @param args input file path, output file path, configuration file path, input
	 *             file format and output file format separated by spaces.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		
		String inputFile = "";
		String outputFile = "";
		String configFile = "";
		String inputFileType = "";
		String outputFileType = "";
		try {
			
			if (args.length < 5) {
				throw new IllegalArgumentException("ERROR! Make sure you have included all the parameters in the following order:"
						+ " inputFilePath, outputFilePath, configFilePath, inputFileType, outputFileType");
			}

			inputFile = args[0];
			outputFile = args[1];
			configFile = args[2];
			inputFileType = args[3];
			outputFileType = args[4];
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			System.out.println(e.getMessage());
		}

		ConfigProcessor.parseConfig(configFile);

		InputReaderInterface reader = new InputReader(inputFile);
		OutputWriterInterface writer = new OutputWriter(outputFile);

		Decoder decoder = null;
		Encoder encoder = null;
		Class<?> inputFactoryClass;
		Class<?> outputFactoryClass;
		try {
			inputFactoryClass = Class.forName("com.dxc.file.reader." + inputFileType + "InputFactory");
			// Get a generic Constructor object for any constructor
			Constructor<?> inputFactoryConstructor = inputFactoryClass.getConstructor();

			// Get the list of properties from the config processor
			List<Property> properties = ConfigProcessor.getByCategory(reader.getConfigCategory());

			// Create a new instance of any class with the list of properties
			decoder = ((InputFactoryInterface) inputFactoryConstructor.newInstance()).createFactory(properties, reader).getDecoder();

			logger.info("Created an instance of " + "com.dxc.file.reader." + inputFileType + "Decoder");

			outputFactoryClass = Class.forName("com.dxc.file.writer." + outputFileType + "OutputFactory");
			// Get a generic Constructor object for any constructor
			Constructor<?> outputFactoryConstructor = outputFactoryClass.getConstructor();

			// Get the list of properties from the config processor
			List<Property> propertiesWriter = ConfigProcessor.getByCategory(writer.getConfigCategory());

			// Create a new instance of any class with the list of properties
			encoder = ((OutputFactoryInterface) outputFactoryConstructor.newInstance()).createFactory(propertiesWriter, writer).getEncoder();

			logger.info("Created an instance of " + "com.dxc.file.writer." + outputFileType + "FileWriter");
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error(e.getStackTrace());
			System.out.println(e.getMessage());
		}

		Converter cvt = new Converter(decoder, encoder);


		cvt.convert();
		
		reader.closeResource();
		writer.closeResource();
	}

}
