package com.dxc.cherry.converter.input;
import java.util.ArrayList;
import java.util.List;

import com.dxc.cherry.converter.config.Property;

public class CSVDecoder implements Decoder{
	
	private InputReaderInterface fileReader;
	
	/**
	 *  A constructor for the CSV Decoder
	 * @param config List of properties derived from the configuration file
	 * @param fileReader Filer reader
	 */
		
	public CSVDecoder (List<Property> config, InputReaderInterface fileReader) {
		this.fileReader = fileReader;
	}

	@Override
	public String getNewLine() {
		return fileReader.readLine();
	}
	/**
	 *@return Returns a unit of information
	 */
	@Override
	public String getUnit() {
	    StringBuilder output = new StringBuilder();
	    String currentLine = getNewLine();
	    
	    if (currentLine != null) {
	        output.append(currentLine);
	    }
	    
	    while (currentLine != null && currentLine.charAt(currentLine.length() - 1) == ',') {
	        currentLine = getNewLine();
	        output.append(currentLine);
	    }
	    
	    return output.toString();
	}

}
