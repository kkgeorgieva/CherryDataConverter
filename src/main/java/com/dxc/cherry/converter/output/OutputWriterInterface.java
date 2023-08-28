package com.dxc.cherry.converter.output;

import java.io.IOException;

/**
 * Interface for writer classes, which are in charge of writing information into a specific file.
 */
public interface OutputWriterInterface {
	
	public void write(String input) throws IOException;
	public String getConfigCategory();
}
