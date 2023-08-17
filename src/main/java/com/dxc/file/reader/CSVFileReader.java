package com.dxc.file.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.file.config.Property;
import com.dxc.file.writer.CSVFileWriter;
/**
 * A file reader class designated for the .csv format.
 */
public class CSVFileReader extends FileReaderProvider {
	
	private static Logger logger = LogManager.getLogger(CSVFileReader.class);
	
	private BufferedReader br = null;

	/**
	 * Constructor, which creates a new instance.
	 * @param config List of configurations, designated for a reader class.
	 */
	public CSVFileReader(List<Property> config) {
		super(config);
	}
	/**
	 * A method that reads data from a file.
	 * @param filePath file path.
	 * @return list of file's data; every entry is a new line from the file.
	 */
	@Override
	public ArrayList<String> readFile(String filePath) {
        try {
            br = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
        }

        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
        	logger.error(e.getMessage());
            e.printStackTrace();
        }
        
        logger.info("Successfully read file and parsed into ArrayList of Strings");
        return data;
       
	}
}
