package com.dxc.cherry.converter.input;

import java.util.List;

import com.dxc.cherry.converter.config.Property;

public class CSVInputFactory implements DecoderFactory {

	private List<Property> config;
	private InputReaderInterface reader;
	
	@Override
	public DecoderFactory createFactory(List<Property> config, InputReaderInterface reader) {
		this.config = config;
		this.reader = reader;
		return this;
	}

	@Override
	public Decoder getDecoder() {
		return new CSVDecoder(config, reader);
	}

	@Override
	public String getFormat() {
		return "CSV";
	}

}
