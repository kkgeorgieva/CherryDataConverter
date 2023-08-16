package com.dxc.file.reader;

import java.util.List;

import com.dxc.file.config.Property;

public abstract class FileReaderProvider implements FileReaderInterface {
	
	protected List<Property> config;
	protected static String configCategory = "input";
	
	public FileReaderProvider(List<Property> config) {
		this.config = config;
	}
	
	public static String getConfigCategory() {
		return configCategory;
	}
}
