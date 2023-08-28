package com.dxc.cherry.converter.output;

import java.io.IOException;

public interface OutputWriterFactory {
	public OutputWriterFactory createFactory(String[] args) throws IOException;
	public OutputWriterInterface getWriter();
}

