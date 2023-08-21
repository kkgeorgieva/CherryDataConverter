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
	public void encodeUnit(String unit) {
		String output = "";
		
		String[] data = unit.split(",");

		for (int i = 0; i < data.length - 1; i++) {
			output += data[i];
			output += ',';

		}
		output += data[data.length - 1];
		output += "\n";
		
		fileWriter.write(output);
	}
}
