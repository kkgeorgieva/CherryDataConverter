package com.dxc.file.writer;

import java.util.List;

import com.dxc.file.config.Property;
/**
 * FileWriterProvider is a base abstract class that must be extended by all writer classes.
 */
public abstract class FileWriterProvider implements FileWriterInterface {
	
	protected List<Property> config;
	protected static String configCategory = "output";
	/**
	 * Constructor, which creates a new instance.
	 * @param config List of configurations, designated for a writer class.
	 */
	public FileWriterProvider(List<Property> config) {
		this.config = config;
	}
	/**
	 * Getter method for configuration section;
	 * @return configuration section
	 */
	public static String getConfigCategory() {
		return configCategory;
	}
}
