package com.dxc.cherry.converter.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

/**
 * ConfigProcessor is a class, that handles processing a configuration file's
 * data and separates it into property key-value-pairs.
 */

public class ConfigProcessor implements AutoCloseable {
	/**
	 * A list containing the generated properties.
	 */
	public static List<Property> propertyList;
	private static Logger logger = LogManager.getLogger(ConfigProcessor.class);

	/**
	 * Method that parses the configuration file, recognizes sections and it's
	 * related information and generates properties based on it.
	 * 
	 * @param configFile file containing information needed for converting from one
	 *                   file format to another
	 */
	public static boolean parseConfig(String configFile) {
		List<Property> properties = new ArrayList<>();

		try (FileInputStream inputStream = new FileInputStream(configFile)) {
			Yaml yaml = new Yaml();
			Map<String, Object> configMap = yaml.load(inputStream);
			logger.info("Loaded the config file into the Config Processor");

			extractPropertiesFromConfigMap(properties, configMap);
			propertyList = properties;
			return true;

		} catch (IOException e) {
			logger.error(e.getStackTrace());
			System.out.println(e.getMessage());
			return false;
		}
	}

	private static void extractPropertiesFromConfigMap(List<Property> properties, Map<String, Object> configMap) {
		// the whole thing could throw a config extraction exception
		for (Map.Entry<String, Object> entry : configMap.entrySet()) {
			String category = entry.getKey();
			Map<String, Object> section = (Map<String, Object>) entry.getValue();
			
			addPropertiesFromSection(properties, category, section);
		}
	}

	private static void addPropertiesFromSection(List<Property> properties, String category,
			Map<String, Object> section) {
		for (Map.Entry<String, Object> propertyEntry : section.entrySet()) {
			String key = propertyEntry.getKey();
			String value = propertyEntry.getValue().toString();
			//add exception that marks an error while trying to add properties
			properties.add(new Property(category, key, value));
			logger.info("Created a new property from the config file");
		}
	}
	/**
	 * Method that filters properties by given category and returns them.
	 * 
	 * @param categoryString category to filter by
	 * @return a list of filtered properties
	 */
	public static List<Property> getByCategory(String categoryString) {
		return propertyList.stream().filter(property -> property.category().equals(categoryString)).toList();
	}

	@Override
	public void close() throws Exception {
		logger.info("Closing the FileStream in ConfigProcessor");
		
	}

}
