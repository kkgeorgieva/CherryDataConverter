package com.dxc.cherry.converter.output;

import java.util.ArrayList;
/**
 * Interface for writer classes, which are in charge of writing information into a specific file.
 */
public interface OutputWriterInterface {
	
	boolean write(String input);
	public String getConfigCategory();
	public void closeResource();
}
