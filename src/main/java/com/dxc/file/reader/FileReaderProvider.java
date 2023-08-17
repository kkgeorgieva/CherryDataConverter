package com.dxc.file.reader;

import java.util.List;

/**
 * FileReaderProvider is a base abstract class, that must be inherited from all reader classes.
 * It contains information about the properties from the "input" section of the configuration file.
 */

import com.dxc.file.config.Property;
/**
 * FileReaderProvider is a base abstract class that must be extended by all reader classes.
 */
public abstract class FileReaderProvider implements FileReaderInterface {
	
	protected List<Property> config;
	protected static String configCategory = "input";
	/**
	 * Constructor, which creates a new instance.
	 * @param config List of configurations, designated for a reader class.
	 */
	public FileReaderProvider(List<Property> config) {
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
