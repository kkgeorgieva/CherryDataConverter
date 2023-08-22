package com.dxc.main.app;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.naming.directory.InvalidAttributesException;

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
	 * Starting point of the program. Creates instances of the needed reader and
	 * writer for the given file formats. Creates an instance of the converter,
	 * which executes the conversion process.
	 * 
	 * @param args input file path, output file path, configuration file path, input
	 *             file format and output file format separated by spaces.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		// Implementation for case with Properties Class
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
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error(e.getStackTrace());
			System.out.println(e.getMessage());
		}

		Converter cvt = new Converter(decoder, encoder);


		cvt.convert();
	}

}
