package com.dxc.cherry.converter.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dxc.cherry.converter.Converter;
import com.dxc.cherry.converter.ConverterApplication;
import com.dxc.cherry.converter.config.ConfigProcessor;
import com.dxc.cherry.converter.config.Property;
import com.dxc.cherry.converter.input.CSVDecoder;
import com.dxc.cherry.converter.input.CSVInputFactory;
import com.dxc.cherry.converter.input.Decoder;
import com.dxc.cherry.converter.input.FileInputFactory;
import com.dxc.cherry.converter.input.InputReader;
import com.dxc.cherry.converter.input.InputReaderInterface;
import com.dxc.cherry.converter.output.CSVEncoder;
import com.dxc.cherry.converter.output.Encoder;
import com.dxc.cherry.converter.output.FWEncoder;
import com.dxc.cherry.converter.output.OutputWriter;

public class InputPackageTest {

	@Test
	public void inputReaderTest() {
		InputReader reader;
		try {
			reader = new InputReader(this.getClass().getResource("Test.csv").getPath());
			assertEquals("Stoyan,23,SI", reader.readLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void inputReaderExceptionTest() {
		assertThrows(FileNotFoundException.class, () -> {InputReader reader = new InputReader("");});
		assertThrows(IOException.class, () -> {
			InputReader reader = new InputReader(this.getClass().getResource("Test.csv").getPath());
			reader.closeResource();
			reader.readLine();
		});
		assertDoesNotThrow(() -> {
			InputReader reader = new InputReader(this.getClass().getResource("Test.csv").getPath());
			reader.closeResource();
		});
	}
	
	@Test
	public void inputReaderGetConfigCategoryTest() {
		try {
			InputReader reader = new InputReader(this.getClass().getResource("Test.csv").getPath());
			assertEquals("input", reader.getConfigCategory());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void fileInputFactoryTest() {
		InputReaderInterface reader;
		try {
			reader = new FileInputFactory().createFactory(new String[] {this.getClass().getResource("Test.csv").getPath()}).getReader();
			assertEquals("Stoyan,23,SI", reader.readLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void csvInputFactoryTest() {
		InputReaderInterface reader;
		CSVInputFactory factory = new CSVInputFactory();
		try {
			reader = new FileInputFactory().createFactory(new String[] {this.getClass().getResource("Test.csv").getPath()}).getReader();
			factory.createFactory(null, reader);
			assertInstanceOf(CSVDecoder.class, factory.getDecoder());
			assertEquals("CSV", factory.getFormat());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void csvDecoderTest() {
		assertThrows(InvalidParameterException.class, () -> {var decoder = new CSVDecoder(null, null);});
		
		InputReaderInterface reader;
		CSVInputFactory factory = new CSVInputFactory();
		try {
			reader = new FileInputFactory().createFactory(new String[] {this.getClass().getResource("Test.csv").getPath()}).getReader();
			Decoder decoder = new CSVDecoder(null, reader);
			assertEquals("Stoyan,23,SI", decoder.getUnit());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
