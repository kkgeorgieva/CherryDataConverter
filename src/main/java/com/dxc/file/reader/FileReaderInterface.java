package com.dxc.file.reader;

import java.util.ArrayList;
/**
 * Interface for reader classes, which are in charge of processing a specific file format.
 */
public interface FileReaderInterface {
	/**
	 * A method that reads data from a file.
	 * @param filePath file path.
	 * @return list of file's data; every entry is a new line from the file.
	 */
	public String readLine();
	public String getConfigCategory();

}
