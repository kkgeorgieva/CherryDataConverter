package com.dxc.cherry.converter.output;

import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.cherry.converter.Converter;
import com.dxc.cherry.converter.config.Property;

/**
 * A file writer class designed to write information to an output file.
 */
public class OutputWriter implements OutputWriterInterface {

	private static Logger logger = LogManager.getLogger(OutputWriter.class);
	FileWriter fileWriter;

	private String filePath;
	/**
	 * A method for creating a new instance of the FileWriter.
	 * 
	 * @param fileName The name of the output file.
	 * @throws IOException
	 */
	public OutputWriter(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * A method that writes data to a file.
	 * 
	 * @param input  Already processed data from the input file.
	 */
	@Override
	public boolean write(String input) {
		try {
			fileWriter = new FileWriter(filePath);
			fileWriter.write(input);
			fileWriter.flush();

			logger.info("Successfully written to a file.");
			
			fileWriter.close();
			return true;

		} catch (IOException e) {
			logger.error(e.getStackTrace());
			System.out.println(e.getMessage());
			return false;
		}

	}

	@Override
	public String getConfigCategory() {
		return "output";
	}
}
