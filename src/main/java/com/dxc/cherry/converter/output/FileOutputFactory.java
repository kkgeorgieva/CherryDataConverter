package com.dxc.cherry.converter.output;

import com.dxc.cherry.converter.input.InputReader;
import com.dxc.cherry.converter.input.InputReaderFactory;
import com.dxc.cherry.converter.input.InputReaderInterface;

public class FileOutputFactory implements OutputWriterFactory {
	
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
