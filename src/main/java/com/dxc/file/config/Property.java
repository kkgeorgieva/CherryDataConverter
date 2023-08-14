package com.dxc.file.config;

public class Property {
	
	//category - represents the tag, that separates the config file into sections
	//key
	//value
	
	private String category;
	private String key;
	private String value;
	
	public Property(String category, String key, String value) {
		this.category = category;
		this.key = key;
		this.value = value;
	}
	
	public String getCategory() {
		return this.category;
	}
	public String getKey() {
		return this.key;
	}
	public String getValue() {
		return this.value;
	}
	

}
