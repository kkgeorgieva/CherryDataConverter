package com.dxc.file.reader;

import java.util.List;

import com.dxc.file.config.Property;

public class CSVInputFactory implements InputFactoryInterface {

	private List<Property> config;
	private InputReaderInterface reader;
	
	@Override
	public InputFactoryInterface createFactory(List<Property> config, InputReaderInterface reader) {
		this.config = config;
		this.reader = reader;
		return this;
	}

	@Override
	public Decoder getDecoder() {
		return new CSVDecoder(config, reader);
	}

}
