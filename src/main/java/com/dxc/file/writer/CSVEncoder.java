package com.dxc.file.writer;
import java.io.IOException;
import java.util.List;

import com.dxc.file.config.Property;

public class CSVEncoder implements Encoder {
	
	private FileWriterInterface fileWriter;
	
	public CSVEncoder(List<Property> config, FileWriterInterface fileWriter) {
		this.fileWriter = fileWriter;
	}

	@Override
	public String encodeUnit(String unit) {
		StringBuilder output = new StringBuilder();
		
		String[] data = unit.split(",");

		for (int i = 0; i < data.length - 1; i++) {
			output.append(data[i]);
			output.append(',');

		}
		output.append(data[data.length - 1]);
		output.append("\n");
		
		fileWriter.write(output.toString());
		
		return output.toString();
	}
}
