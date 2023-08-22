package com.dxc.file.reader;
/**
 * Interface for reader classes, which are in charge of processing a specific file format.
 */
public interface FileReaderInterface {
	public String readLine();
	public String getConfigCategory();

}
