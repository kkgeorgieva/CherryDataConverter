package com.dxc.cherry.converter.input;

import java.io.FileNotFoundException;

public interface InputReaderFactory {
	public InputReaderFactory createFactory(String[] args) throws FileNotFoundException;
	public InputReaderInterface getReader();
}
