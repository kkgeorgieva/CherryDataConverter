package com.dxc.cherry.converter.input;

public final class FileInputFactory implements InputReaderFactory {

	private InputReaderInterface reader;
	
	@Override
	public InputReaderFactory createFactory(String[] args) {
		reader = new InputReader(args[0]);
		return this;
	}
	
	public InputReaderInterface getReader() {
		return reader;
	}

}
