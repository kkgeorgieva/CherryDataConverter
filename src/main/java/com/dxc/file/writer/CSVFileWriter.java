package com.dxc.file.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.file.config.Property;
import com.dxc.system.Converter;
/**
 * A file writer class designated for the .csv format.
 */
public class CSVFileWriter extends FileWriterProvider {
	
	private static Logger logger = LogManager.getLogger(CSVFileWriter.class);
	/**
	 * Constructor, which creates a new instance.
	 * @param config List of configurations, designated for a reader class.
	 */
	public CSVFileWriter(List<Property> config) {
		super(config);
	}
	/**
	 * A method that writes data to a file and formats it in a specific way.
	 * @param input data from the input file.
	 * @param fileName file name.
	 */
	@Override
	public boolean write(ArrayList<String> input, String fileName) {
		try {
			
		java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
		
		
		for(String line: input) {
			String [] data = line.split(",");
			
			for(int i = 0; i< data.length-1; i++) {
				fileWriter.append(data[i]);
				fileWriter.append(',');
				
			}
			fileWriter.append(data[data.length-1]);
			fileWriter.append("\n");
		}
		fileWriter.flush();
		fileWriter.close();
		
		logger.info("Successfully written file " + fileName);
		return true;
		
		
		} catch (IOException e) {
		
			logger.error(e.getMessage());
			return false;
		}
		
		
	}
}
