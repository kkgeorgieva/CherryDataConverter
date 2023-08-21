package com.dxc.file.reader;
import java.util.ArrayList;
import java.util.List;

import com.dxc.file.config.Property;

public class CSVDecoder implements Decoder{
	
	private FileReaderInterface fileReader;
		
	public CSVDecoder (List<Property> config, FileReaderInterface fileReader) {
		this.fileReader = fileReader;
	}

	@Override
	public String getNewLine() {
		return fileReader.readLine();
	}

	@Override
	public String getUnit() {
		String output = "";
		String currentLine = getNewLine();
		if (currentLine != null) {
			output += currentLine;
		}
		while (currentLine != null && currentLine.charAt(currentLine.length() - 1) == ',') {
			currentLine = getNewLine();
			output += currentLine;
		} 
		return output;
	}

}
