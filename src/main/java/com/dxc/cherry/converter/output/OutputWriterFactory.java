package com.dxc.cherry.converter.output;

public interface OutputWriterFactory {
	public OutputWriterFactory createFactory(String[] args);
	public OutputWriterInterface getWriter();
}

