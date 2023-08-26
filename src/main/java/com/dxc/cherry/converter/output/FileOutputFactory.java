package com.dxc.cherry.converter.output;

public final class FileOutputFactory implements OutputWriterFactory {
	
	private OutputWriterInterface writer;
	
	@Override
	public OutputWriterFactory createFactory(String[] args) {
		writer = new OutputWriter(args[0]);
		return this;
	}
	
	public OutputWriterInterface getWriter() {
		return writer;
	}

}
