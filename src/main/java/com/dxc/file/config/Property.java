package com.dxc.file.config;

/**
 * Property is an object which contains key-value-pair of information and a category.
 */
public class Property {
	
	private String category;
	private String key;
	private String value;
	
	/**
	 * Constructor that creates an instance of a property.
	 * @param category represents a section in the configuration file
	 * @param key 
	 * @param value
	 */
	public Property(String category, String key, String value) {
		this.category = category;
		this.key = key;
		this.value = value;
	}
	/**
	 * Getter method for category.
	 * @return instance's category
	 */
	public String getCategory() {
		return this.category;
	}
	/**
	 * Getter method for key.
	 * @return instance's key.
	 */
	public String getKey() {
		return this.key;
	}
	/**
	 * Getter method for value.
	 * @return instance's value.
	 */
	public String getValue() {
		return this.value;
	}
	/**
	 * Method that returns a formatted string.
	 * @return formatted string containing the instance's information.
	 */
	public String toString() {
		return new String(category + "." + key + "=" + value);
	}
}
