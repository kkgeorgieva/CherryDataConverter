package com.dxc.file.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

/**
 * ConfigProcessor is a class, that handles processing a configuration file's data
 * and separates it into property key-value-pairs.
 */

public class ConfigProcessor {
	/**
	 * A list containing the generated properties.
	 */
	public static List<Property> propertyList;
	/**
	 * Method that parses the configuration file, recognizes sections and it's related information
	 * and generates properties based on it.
	 * @param configFile file containing information needed for converting from one file format to another
	 */
	public static void parseConfig(String configFile) {
        List<Property> properties = new ArrayList<>();

        try (FileInputStream inputStream = new FileInputStream(configFile)) {
            Yaml yaml = new Yaml();
            Map<String, Object> configMap = yaml.load(inputStream);

            for (Map.Entry<String, Object> entry : configMap.entrySet()) {
                String category = entry.getKey();
                Map<String, Object> section = (Map<String, Object>) entry.getValue();

                for (Map.Entry<String, Object> propertyEntry : section.entrySet()) {
                    String key = propertyEntry.getKey();
                    String value = propertyEntry.getValue().toString();
                    properties.add(new Property(category, key, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        propertyList = properties;
    }
	/**
	 * Method that filters properties by given category and returns them.
	 * @param categoryString category to filter by
	 * @return a list of filtered properties
	 */
	public static List<Property> getByCategory(String categoryString) {
		return propertyList.stream().filter(property -> property.getCategory().equals(categoryString)).toList();
	}

}
