package com.dxc.cherry.converter.input;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import com.dxc.cherry.converter.config.Property;

public final class CSVDecoder implements Decoder{
	
	private InputReaderInterface fileReader;
	
	/**
	 *  A constructor for the CSV Decoder
	 * @param config List of properties derived from the configuration file
	 * @param fileReader Filer reader
	 */
		
	public CSVDecoder (List<Property> config, InputReaderInterface fileReader) {
		if (fileReader == null) {
			throw new InvalidParameterException("Provide a non null fileReader to the decoder!");
		}
		this.fileReader = fileReader;
	}

	
	private String getNewLine() throws IOException {
		String line = null;
		if ((line = fileReader.readLine()) == null) {
			fileReader.closeResource();
		}
		return line;
	}
	/**
	 *@return Returns a unit of information
	 * @throws IOException 
	 */
	@Override
	public String getUnit() throws IOException {
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
