package com.dxc.cherry.converter.config;

/**
 * Property is an object which contains key-value-pair of information and a category.
 */
public record Property(String category, String key, String value) {
	
	/**
	 * Method that returns a formatted string.
	 * @return formatted string containing the instance's information.
	 */
	public String toString() {
		return new String(category + "." + key + "=" + value);
	}
}
