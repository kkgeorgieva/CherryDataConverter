package com.dxc.file.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dxc.file.config.Property;

public class CSVFileReader extends FileReaderProvider {

	public CSVFileReader(List<Property> config) {
		super(config);
	}

	BufferedReader br = null;
	ArrayList<String> data = new ArrayList<>();

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

        // Close the BufferedReader object
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return data;
	}
}
