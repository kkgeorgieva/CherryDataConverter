package com.dxc.file.writer;

import java.io.IOException;
import java.util.ArrayList;

public class CSVFileWriter implements FileWriter {
	
	public CSVFileWriter(ArrayList<String> input, String fileName) {
			
	}

	@Override
	public void write(ArrayList<String> input, String fileName) {
		// Receives an arrayList with Strings 
		// Receives fileName, which indicates the output file's name
				
		// Iterates through the list and stores the data into a ".csv" file
		// Adds separators along the way 
		try {
			
		java.io.FileWriter fileWriter = new java.io.FileWriter(fileName);
		
		// iteration through the arayList
		for(String line: input) {
			String [] data = line.split(",");
			
			//writes each value followed by a comma, except for the last one
			for(int i = 0; i< data.length-1; i++) {
				fileWriter.append(data[i]);
				fileWriter.append(',');
				
			}
			// adds the last element of the row and appends a new line
			fileWriter.append(data[data.length-1]);
			fileWriter.append("\n");
		}
		fileWriter.flush();
		fileWriter.close();
		
		
		
		} catch (IOException e) {
			System.out.println("Error occured while creating new CSV file.");
		}
		
		
	}
}
