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
public final class InputReader implements InputReaderInterface {
	
	private static Logger logger = LogManager.getLogger(InputReader.class);
	
	private BufferedReader br = null;

	/**
	 * Constructor, which creates a new instance.
	 * @param filePath The file path of the input file.
	 * @throws FileNotFoundException 
	 */
	public InputReader(String filePath) throws FileNotFoundException {
		try {
			br = new BufferedReader(new FileReader(filePath));
		} catch (FileNotFoundException e) {
			logger.error(e);
			System.out.println(e.getMessage());
			throw new FileNotFoundException(e.getMessage());
		}
	}
	/**
	 * A method that reads data from a file.
	 * @return Returns a line from the input file
	 * @throws IOException 
	 */
	@Override
	public String readLine() throws IOException {

        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
        	logger.error(e);
			System.out.println(e.getMessage());
			throw new IOException(e.getMessage());
        }
        logger.info("Successfully read file and parsed into ArrayList of Strings");       
        return line;
	}
	@Override
	public String getConfigCategory() {
		return "input";
	}
	
	@Override
	public void closeResource() throws IOException {
		try {
			br.close();
		} catch (IOException e) {
			logger.error(e);
			System.out.println("Error closing the input reader!");
			throw new IOException(e.getMessage());
		}
	}
}
