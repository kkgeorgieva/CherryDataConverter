package com.dxc.file.writer;

import java.util.ArrayList;
/**
 * Interface for writer classes, which are in charge of writing and formatting a specific file format.
 */
public interface FileWriterInterface {
	/**
	 * A method that writes data to a file and formats it in a specific way.
	 * @param input data from the input file.
	 * @param fileName file name.
	 */
	boolean write(ArrayList<String> input, String fileName);

}
