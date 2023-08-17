package com.dxc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;

import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.FileReaderProvider;
import com.dxc.file.writer.FileWriterProvider;
import com.dxc.system.Converter;

public class ConverterTest 
{

    @Test
    void shouldAnswerWithTrue() {
    	File resourcesDirectory = new File("src/test/resources");
    	File inputFile = new File(this.getClass().getResource("Test.csv").getFile());
    	File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
    	File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
		String inputFileType = "CSV";
		String outputFileType = "FW";

		ConfigProcessor.parseConfig(configFile.getAbsolutePath());

		
		FileReaderProvider fileReader = null;
		FileWriterProvider fileWriter = null;
		
		// Create a generic Class object for any class
		Class<?> fileReaderClass;
		Class<?> fileWriterClass;
		try {
			fileReaderClass = Class.forName("com.dxc.file.reader." + inputFileType + "FileReader");
			// Get a generic Constructor object for any constructor
			Constructor<?> fileReaderConstructor = fileReaderClass.getConstructor(List.class);
			
			// Get the list of properties from the config processor
			List<Property> properties = ConfigProcessor.getByCategory(FileReaderProvider.getConfigCategory());
			
			// Create a new instance of any class with the list of properties
			fileReader = (FileReaderProvider) fileReaderConstructor.newInstance(properties);
			
			
			fileWriterClass = Class.forName("com.dxc.file.writer." + outputFileType + "FileWriter");
			// Get a generic Constructor object for any constructor
			Constructor<?> fileWriterConstructor = fileWriterClass.getConstructor(List.class);
			
			// Get the list of properties from the config processor
			List<Property> propertiesWriter = ConfigProcessor.getByCategory(FileWriterProvider.getConfigCategory());
			
			// Create a new instance of any class with the list of properties
			fileWriter = (FileWriterProvider) fileWriterConstructor.newInstance(propertiesWriter);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}



		Converter cvt = new Converter(fileReader, fileWriter);

		assertTrue(cvt.convert(inputFile.getAbsolutePath(), outputFile.getAbsolutePath()));
		Assert.isNonEmpty(fileReader.data);
    }
}
