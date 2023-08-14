package com.dxc.file.reader;

import java.util.List;

import com.dxc.file.config.Property;

public abstract class FileReaderProvider implements FileReaderInterface {
	
	protected List<Property> config;
	
	public FileReaderProvider(List<Property> config) {
		this.config = config;
	}
}
