package com.dxc.cherry.converter.output;

/**
 * Interface for writer classes, which are in charge of writing information into a specific file.
 */
public interface OutputWriterInterface {
	
	public void write(String input);
	public String getConfigCategory();
}
