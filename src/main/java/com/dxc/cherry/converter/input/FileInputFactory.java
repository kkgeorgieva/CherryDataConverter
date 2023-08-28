package com.dxc.cherry.converter.input;

import java.io.FileNotFoundException;

public final class FileInputFactory implements InputReaderFactory {

	private InputReaderInterface reader;
	
	@Override
	public InputReaderFactory createFactory(String[] args) throws FileNotFoundException {
		reader = new InputReader(args[0]);
		return this;
	}
	
	public InputReaderInterface getReader() {
		return reader;
	}

}
