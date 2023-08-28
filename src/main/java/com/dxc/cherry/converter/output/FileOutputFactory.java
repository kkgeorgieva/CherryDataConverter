package com.dxc.cherry.converter.output;

import java.io.IOException;

public final class FileOutputFactory implements OutputWriterFactory {
	
	private OutputWriterInterface writer;
	
	@Override
	public OutputWriterFactory createFactory(String[] args) throws IOException {
		writer = new OutputWriter(args[0]);
		return this;
	}
	
	public OutputWriterInterface getWriter() {
		return writer;
	}

}
