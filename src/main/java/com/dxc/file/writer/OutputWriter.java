package com.dxc.file.writer;

import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.converter.Converter;
import com.dxc.file.config.Property;

/**
 * A file writer class designated for the .csv format.
 */
public class OutputWriter implements FileWriterInterface {

	private static Logger logger = LogManager.getLogger(OutputWriter.class);
	FileWriter fileWriter;

	/**
	 * Constructor, which creates a new instance.
	 * 
	 * @param config List of configurations, designated for a reader class.
	 * @throws IOException
	 */
	public OutputWriter(String fileName) throws IOException {
		fileWriter = new FileWriter(fileName);
	}

	/**
	 * A method that writes data to a file and formats it in a specific way.
	 * 
	 * @param input    data from the input file.
	 * @param fileName file name.
	 */
	@Override
	public boolean write(String input) {
		try {
			fileWriter.write(input);
			fileWriter.flush();

			logger.info("Successfully written to a file.");
			return true;

		} catch (IOException e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	@Override
	public String getConfigCategory() {
		return "output";
	}
}
