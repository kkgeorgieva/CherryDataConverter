package com.dxc.file.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dxc.file.config.Property;
/**
 * A file reader class designated for the .csv format.
 */
public class CSVFileReader extends FileReaderProvider {
	
	private BufferedReader br = null;
	private ArrayList<String> data = new ArrayList<>();
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
            e.printStackTrace();
        }

        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                data.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
	}
}
