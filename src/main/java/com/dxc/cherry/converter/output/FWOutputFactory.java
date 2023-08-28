package com.dxc.cherry.converter.output;

import java.util.List;

import com.dxc.cherry.converter.config.Property;

public final class FWOutputFactory implements EncoderFactory {

	private List<Property> config;
	private OutputWriterInterface writer;
	
	@Override
	public EncoderFactory createFactory(List<Property> config, OutputWriterInterface writer) {
		this.config = config;
		this.writer = writer;
		return this;
	}

	@Override
	public Encoder getEncoder() {
		return new FWEncoder(config, writer);
	}

	@Override
	public String getFormat() {
		return "FW";
	}

}
