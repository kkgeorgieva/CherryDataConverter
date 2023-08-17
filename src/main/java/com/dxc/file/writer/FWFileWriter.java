package com.dxc.file.writer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.dxc.file.config.Property;
/**
 * A file writer class designated for the fixed-width file format.
 */
public class FWFileWriter extends FileWriterProvider {

	private List<Integer> widths = new ArrayList<>();
	/**
	 * Constructor, which creates a new instance.
	 * @param config List of configurations, designated for a reader class.
	 */
	public FWFileWriter(List<Property> config) {
		super(config);
		getColumnWidths();
	}
	/**
	 * A method that writes data to a file and formats it in a specific way.
	 * @param input data from the input file.
	 * @param fileName file name.
	 */
	@Override
	public void write(ArrayList<String> input, String fileName) {
		PrintWriter fileWriter = null;
		try {
			fileWriter = new PrintWriter(new FileWriter(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (String line : input) {
			String[] data = line.split(",");

			for (int i = 0; i < data.length - 1; i++) {
				fileWriter.printf("%-" + widths.get(i) + "s", data[i] );
				fileWriter.append(',');

			}
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
