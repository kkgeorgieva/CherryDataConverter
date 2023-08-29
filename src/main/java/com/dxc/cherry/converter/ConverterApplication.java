package com.dxc.cherry.converter;

import java.io.IOException;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.cherry.converter.config.ConfigProcessor;


public class ConverterApplication {

	private static Logger logger = LogManager.getLogger(ConverterApplication.class);

	/**
	 * Starting point of the program. Creates instances of the needed reader and
	 * writer for the given file formats. Creates an instance of the converter,
	 * which executes the conversion process.
	 * 
	 * @param args input file path, output file path, configuration file path, input
	 *             file format and output file format separated by spaces.
	 * @throws IOException
	 */
	public static void main(String[] args) {

		try {
			String inputFile = "";
			String outputFile = "";
			String configFile = "";
			String inputFileType = "";
			String outputFileType = "";
			try {
				
				lessThan5Args(args);
				
				inputFile = args[0];
				outputFile = args[1];
				configFile = args[2];
				inputFileType = args[3];
				outputFileType = args[4];
			} catch (Exception e) {
				logger.error(e);
				System.out.println(e.getMessage());
				return;
			}
			
			var config = ConfigProcessor.parseConfig(configFile);
			
			TheBestConverter theBestConverter = new TheBestConverter(config, inputFile, outputFile);
			
			theBestConverter.Convert(inputFileType, outputFileType);
		} catch (Exception e) {
			logger.error(e.getStackTrace());
			System.out.println(e.getMessage());
			return;
		}
	}

	public static void lessThan5Args(String[] args) {
		if (args.length < 5) {
			throw new IllegalArgumentException("ERROR! Make sure you have included all the parameters in the following order:"
					+ " inputFilePath, outputFilePath, configFilePath, inputFileType, outputFileType");
		}
	}

}
