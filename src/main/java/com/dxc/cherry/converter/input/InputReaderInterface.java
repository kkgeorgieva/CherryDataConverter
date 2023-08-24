package com.dxc.cherry.converter.input;
/**
 * Interface for reader classes, which are in charge of processing a specific file format.
 */
public interface InputReaderInterface {
	public String readLine();
	public String getConfigCategory();
	public void closeResource();
}
