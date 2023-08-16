package com.dxc.file.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.dxc.file.config.Property;

public class FWFileWriter extends FileWriterProvider {

	private List<Integer> widths = new ArrayList<>();

	public FWFileWriter(List<Property> config) {
		super(config);
		getColumnWidths();
	}

	@Override
	public void write(ArrayList<String> input, String fileName) {
		// Receives an arrayList with Strings
		// Receives fileName, which indicates the output file's name

		// Iterates through the list and stores the data into a ".csv" file
		// Adds separators along the way

		PrintWriter fileWriter = null;
		try {
			fileWriter = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// iteration through the arayList
		for (String line : input) {
			String[] data = line.split(",");

			// writes each value followed by a comma, except for the last one
			for (int i = 0; i < data.length - 1; i++) {
				fileWriter.printf("%-" + widths.get(i) + "s", data[i] );
				fileWriter.append(',');

			}
			// adds the last element of the row and appends a new line
			fileWriter.printf("%-" + widths.get(data.length - 1) + "s", data[data.length - 1]);
			fileWriter.println();
		}
		fileWriter.flush();
		fileWriter.close();

	}

	private void getColumnWidths() {
		String widths = config.stream().filter(property -> property.getKey().equals("columnWidths")).findFirst().get().getValue();
		
		String[] arr = widths.replace("[", "").replace("]", "").split(",\\s+");
		
		for (String str : arr) {
		  this.widths.add(Integer.parseInt(str));
		}
	}
}
