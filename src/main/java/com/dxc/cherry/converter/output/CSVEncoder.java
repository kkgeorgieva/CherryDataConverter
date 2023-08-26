package com.dxc.cherry.converter.output;

import java.util.List;

import com.dxc.cherry.converter.config.Property;

/**
 * A class in charge of the process of encoding information for a CSV  file.
 */

public final class CSVEncoder implements Encoder {
	
	private OutputWriterInterface fileWriter;
	
	/**
	 * A constructor for the CSVEncoder class.
	 * @param config A list of properties derived from the configuration file.
	 * @fileWriter Instance of the FileWriter interface.
	 */
	
	public CSVEncoder(List<Property> config, OutputWriterInterface fileWriter) {
		this.fileWriter = fileWriter;
	}
	/**
	 * A method that processes a unit of information given to it.
	 * @unit A string of information passed to the method as a unit.
	 * @return Returns the already processed String of information.
	 */
	@Override
	public void encodeUnit(String unit) {
		
		StringBuilder output = new StringBuilder();
		
		String[] data = unit.split(",");

		for (int i = 0; i < data.length - 1; i++) {
			output.append(data[i]);
			output.append(',');

		}
		output.append(data[data.length - 1]);
		output.append("\n");
		
		fileWriter.write(output.toString());
	
	}
}
