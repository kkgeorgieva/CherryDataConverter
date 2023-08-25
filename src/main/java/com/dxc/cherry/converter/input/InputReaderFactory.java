package com.dxc.cherry.converter.input;

public interface InputReaderFactory {
	public InputReaderFactory createFactory(String[] args);
	public InputReaderInterface getReader();
}
