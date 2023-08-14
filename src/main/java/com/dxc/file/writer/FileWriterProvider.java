package com.dxc.file.writer;

import java.util.List;

import com.dxc.file.config.Property;

public abstract class FileWriterProvider implements FileWriterInterface {
	
	protected List<Property> config;
	
	public FileWriterProvider(List<Property> config) {
		this.config = config;
	}
}
