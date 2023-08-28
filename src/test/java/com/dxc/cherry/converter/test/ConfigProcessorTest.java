package com.dxc.cherry.converter.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dxc.cherry.converter.config.ConfigProcessor;
import com.dxc.cherry.converter.config.ConfigProcessorException;
import com.dxc.cherry.converter.config.Property;

class ConfigProcessorTest {
	

    @Test
    void testParseConfig() throws ConfigProcessorException {
    	File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
    	
        List<Property> properties = ConfigProcessor.parseConfig(configFile.getAbsolutePath());
        
       assertNotNull(properties); // properties is not null
       assertEquals(3, properties.size()); 

        // Test specific properties
        Property outputFileProperty = properties.get(1); // Assuming the output file property is at index 1
        assertEquals("output.csv", outputFileProperty.value());

        List<Property> columnWidthsProperty = ConfigProcessor.getByCategory("output.columnWidths");
        assertNotNull(columnWidthsProperty);
       // assertEquals(3, columnWidthsProperty.size()); 
        
    }
    @Test
    void testParseConfigIOException() {
        String nonExistentFile = "nonexistent.yml"; // A file that doesn't exist
        
       IOException exception =  assertThrows(IOException.class, () -> {
            ConfigProcessor.parseConfig(nonExistentFile);
        });
        assertNotNull(exception.getMessage());
        
         
    }

}
