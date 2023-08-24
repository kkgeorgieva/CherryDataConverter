package com.dxc.cherry.converter.output;

import java.util.List;

import com.dxc.cherry.converter.config.Property;

public class FWOutputFactory implements OutputFactoryInterface {

	private List<Property> config;
	private OutputWriterInterface writer;
	
	@Override
	public OutputFactoryInterface createFactory(List<Property> config, OutputWriterInterface writer) {
		this.config = config;
		this.writer = writer;
		return this;
	}

	@Override
	public Encoder getEncoder() {
		return new FWEncoder(config, writer);
	}

}
