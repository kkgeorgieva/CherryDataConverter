package com.dxc.cherry.converter.input;

import java.io.IOException;

/**
 * Interface for reader classes, which are in charge of processing a specific file format.
 */
public interface InputReaderInterface {
	public String readLine() throws IOException;
	public String getConfigCategory();
	public void closeResource() throws IOException;
}
