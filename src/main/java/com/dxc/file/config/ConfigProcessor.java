package com.dxc.file.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConfigProcessor {
	// Processes the config file and creates different properties that define the user's requirements
	// The properties will be then created as key value pairs.
	// Reads only categories key and value
	// Ignores comments initialized with #
	
	private String fileName;
	private List<Property> props;
	
	public ConfigProcessor(String fileName) {
		this.fileName = fileName;
		this.props = new ArrayList<>();
		this.getProps();
	}
	
	private void getProps() {
		Properties prop = new Properties();
		
		try (FileInputStream fis = new FileInputStream(fileName)) {
			prop.load(fis);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		
		
		
		
		prop.entrySet().forEach(e -> {
			String category = e.getKey().toString().split(".")[0];
			String key = e.getKey().toString();
			String value = e.getValue().toString();
			
			this.props.add(new Property(category, key, value));
		});;
		
	}
	

}
