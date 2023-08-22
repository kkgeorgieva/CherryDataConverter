package com.dxc.file.writer;

import java.util.ArrayList;
/**
 * Interface for writer classes, which are in charge of writing information into a specific file.
 */
public interface FileWriterInterface {
	
	boolean write(String input);
	public String getConfigCategory();

}
