package com.dxc.file.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.file.config.Property;

/**
 * A file writer class designated for the fixed-width file format.
 */
public class FWEncoder implements Encoder {

	Logger logger = LogManager.getLogger(FWEncoder.class);

	private List<Integer> widths = new ArrayList<>();
	private List<Property> config;
	private FileWriterInterface fileWriter;

	/**
	 * Constructor, which creates a new instance.
	 * 
	 * @param config List of configurations, designated for a reader class.
	 */
	public FWEncoder(List<Property> config, FileWriterInterface fileWriter) {
		this.config = config;
		this.fileWriter = fileWriter;
		getColumnWidths();
	}

	/**
	 * A method that writes data to a file and formats it in a specific way.
	 * 
	 * @param input    data from the input file.
	 * @param fileName file name.
	 */
	public void encodeUnit(String unit) {
		StringBuilder output = new StringBuilder();

		String[] data = unit.split(",");

		for (int i = 0; i < data.length - 1; i++) {
			output.append(String.format("%-" + widths.get(i) + "s", data[i]));
			output.append(',');

		}
		output.append(String.format("%-" + widths.get(data.length - 1) + "s", data[data.length - 1]));
		output.append('\n');

		logger.info("Successfully built the output.");
		
		fileWriter.write(output.toString());
	}

	private void getColumnWidths() {
		String widths = config.stream().filter(property -> property.getKey().equals("columnWidths")).findFirst().get()
				.getValue();

		String[] arr = widths.replace("[", "").replace("]", "").split(",\\s+");

		for (String str : arr) {
			this.widths.add(Integer.parseInt(str));
			logger.info("Successfully parsed column widths.");
		}
	}
}
