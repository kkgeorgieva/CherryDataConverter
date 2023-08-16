package com.dxc.file.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.yaml.snakeyaml.Yaml;

public class ConfigProcessor {
	
	public static List<Property> propertyList;
	
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
	
	public static List<Property> getByCategory(String categoryString) {
		return propertyList.stream().filter(property -> property.getCategory().equals(categoryString)).toList();
	}

}
