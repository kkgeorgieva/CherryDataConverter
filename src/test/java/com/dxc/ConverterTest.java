package com.dxc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.Test;

import com.dxc.converter.Converter;
import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.CSVFileReader;
import com.dxc.file.reader.FileReaderProvider;
import com.dxc.file.writer.CSVFileWriter;
import com.dxc.file.writer.FWFileWriter;
import com.dxc.file.writer.FileWriterProvider;
import com.dxc.main.app.ConverterApplication;

public class ConverterTest 
{

    @Test
    void configProcessorTest() {
    	File resourcesDirectory = new File("src/test/resources");
    	File inputFile = new File(this.getClass().getResource("Test.csv").getFile());
    	File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
    	File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
		String inputFileType = "CSV";
		String outputFileType = "FW";

		assertTrue(ConfigProcessor.parseConfig(configFile.getAbsolutePath()));
    }
    
    @Test
    void csvFileWriterTest() {
    	File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
    	File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
    	File inputFile = new File(this.getClass().getResource("Test.csv").getFile());
    	
    	ConfigProcessor.parseConfig(configFile.getAbsolutePath());
    	CSVFileReader reader = new CSVFileReader(ConfigProcessor.getByCategory(FileReaderProvider.getConfigCategory()));
    	
    	CSVFileWriter writer = new CSVFileWriter(ConfigProcessor.getByCategory(FileWriterProvider.getConfigCategory()));
    	
    	assertTrue(writer.write(reader.readFile(inputFile.getAbsolutePath()), outputFile.getAbsolutePath()));
    }
    
    @Test
    void fwFileWriterTest() {
    	File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
    	File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
    	File inputFile = new File(this.getClass().getResource("Test.csv").getFile());
    	
    	ConfigProcessor.parseConfig(configFile.getAbsolutePath());
    	CSVFileReader reader = new CSVFileReader(ConfigProcessor.getByCategory(FileReaderProvider.getConfigCategory()));
    	
    	FWFileWriter writer = new FWFileWriter(ConfigProcessor.getByCategory(FileWriterProvider.getConfigCategory()));
    	
    	assertTrue(writer.write(reader.readFile(inputFile.getAbsolutePath()), outputFile.getAbsolutePath()));
    }
    
    @Test
    void csvFileReaderTest() {
    	File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
    	File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
    	File inputFile = new File(this.getClass().getResource("Test.csv").getFile());
    	
    	ConfigProcessor.parseConfig(configFile.getAbsolutePath());
    	CSVFileReader reader = new CSVFileReader(ConfigProcessor.getByCategory(FileReaderProvider.getConfigCategory()));
    	
    	assertEquals("﻿Item Name,Item Price,Item Description", reader.readFile(inputFile.getAbsolutePath()).get(0));
    	
    }
    
    @Test
    void converterTest() {
    	File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
    	File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
    	File inputFile = new File(this.getClass().getResource("Test.csv").getFile());
    	
    	ConfigProcessor.parseConfig(configFile.getAbsolutePath());
    	CSVFileReader reader = new CSVFileReader(ConfigProcessor.getByCategory(FileReaderProvider.getConfigCategory()));
    	FWFileWriter writer = new FWFileWriter(ConfigProcessor.getByCategory(FileWriterProvider.getConfigCategory()));
    	
    	Converter cvt = new Converter(reader, writer);
    	
    	assertTrue(cvt.convert(inputFile.getAbsolutePath(), outputFile.getAbsolutePath()));
    	
    }
    
    @Test
    void mainAppTest() {
    	File inputFile = new File(this.getClass().getResource("Test.csv").getFile());
    	File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
		String inputFileType = "CSV";
		String outputFileType = "FW";
		
		
    	ConverterApplication.main(new String[] {inputFile.getAbsolutePath(), "C:\\Windows\\Temp\\mainAppTest.csv", configFile.getAbsolutePath(),
    								inputFileType, outputFileType});    	
    	
    	
		File outputFile = new File("C:\\Windows\\Temp\\mainAppTest.csv");
		assertTrue(outputFile.exists());
    }
}
