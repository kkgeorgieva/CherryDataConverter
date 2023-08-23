package com.dxc.cherry.converter.input;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.cherry.converter.config.Property;
/**
 * A file reader class designated for the .csv format.
 */
public class InputReader implements InputReaderInterface {
	
	private static Logger logger = LogManager.getLogger(InputReader.class);
	
	private BufferedReader br = null;

	/**
	 * Constructor, which creates a new instance.
	 * @param filePath The file path of the input file.
	 */
	public InputReader(String filePath) {
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			logger.error(e.getStackTrace());
			System.out.println(e.getMessage());
		}
	}
	/**
	 * A method that reads data from a file.
	 * @return Returns a line from the input file
	 */
	@Override
	public String readLine() {

        String line = null;
        try {
            if ((line = br.readLine()) != null) {
            }
        } catch (IOException e) {
        	logger.error(e.getStackTrace());
			System.out.println(e.getMessage());
        }
        logger.info("Successfully read file and parsed into ArrayList of Strings");       
        return line;
	}
	@Override
	public String getConfigCategory() {
		return "input";
	}
}
