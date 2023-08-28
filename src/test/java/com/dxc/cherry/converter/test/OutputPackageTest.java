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
import com.dxc.cherry.converter.config.ConfigProcessorException;
import com.dxc.cherry.converter.config.Property;
import com.dxc.cherry.converter.input.CSVDecoder;
import com.dxc.cherry.converter.input.CSVInputFactory;
import com.dxc.cherry.converter.input.Decoder;
import com.dxc.cherry.converter.input.FileInputFactory;
import com.dxc.cherry.converter.input.InputReader;
import com.dxc.cherry.converter.input.InputReaderInterface;
import com.dxc.cherry.converter.output.CSVEncoder;
import com.dxc.cherry.converter.output.CSVOutputFactory;
import com.dxc.cherry.converter.output.Encoder;
import com.dxc.cherry.converter.output.FWEncoder;
import com.dxc.cherry.converter.output.FWOutputFactory;
import com.dxc.cherry.converter.output.FileOutputFactory;
import com.dxc.cherry.converter.output.OutputWriter;
import com.dxc.cherry.converter.output.OutputWriterInterface;

public class OutputPackageTest {

	@Test
	public void outputWriterTest() {
		OutputWriter writer;
		InputReader reader;
		try {
			writer = new OutputWriter(this.getClass().getResource("outputWriterTest.txt").getPath());
			reader = new InputReader(this.getClass().getResource("outputWriterTest.txt").getPath());
			writer.write("testing the writer");
			assertEquals("testing the writer", reader.readLine());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void outputWriterExceptionTest() {
		assertThrows(IOException.class, () -> {
			var writer = new OutputWriter("");
		});
	}

	@Test
	public void outputWriterGetConfigCategoryTest() {
		OutputWriter writer;
		try {
			writer = new OutputWriter(this.getClass().getResource("Output.csv").getPath());
			assertEquals("output", writer.getConfigCategory());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void fileOutputFactoryTest() {
		try {
			assertInstanceOf(OutputWriter.class, new FileOutputFactory()
					.createFactory(new String[] { this.getClass().getResource("Output.csv").getPath() }).getWriter());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void csvOutputFactoryTest() {
		OutputWriterInterface writer;
		CSVOutputFactory factory = new CSVOutputFactory();
		try {
			writer = new FileOutputFactory()
					.createFactory(new String[] { this.getClass().getResource("Output.csv").getPath() }).getWriter();
			factory.createFactory(null, writer);
			assertInstanceOf(CSVEncoder.class, factory.getEncoder());
			assertEquals("CSV", factory.getFormat());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void csvEncoderTest() {
		assertThrows(InvalidParameterException.class, () -> {
			var encoder = new CSVEncoder(null, null);
		});

		InputReaderInterface reader;
		try {
			OutputWriterInterface writer;
			writer = new FileOutputFactory()
					.createFactory(new String[] { this.getClass().getResource("csvEncoderTest.txt").getPath() })
					.getWriter();
			CSVEncoder encoder = new CSVEncoder(null, writer);
			encoder.encodeUnit("Stoyan,23,SI");

			reader = new FileInputFactory()
					.createFactory(new String[] { this.getClass().getResource("csvEncoderTest.txt").getPath() })
					.getReader();
			Decoder decoder = new CSVDecoder(null, reader);

			assertEquals("Stoyan,23,SI", decoder.getUnit());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void fwOutputFactoryTest() {
		File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());

		try {
			List<Property> properties = ConfigProcessor.parseConfig(configFile.getAbsolutePath());

			OutputWriterInterface writer;
			FWOutputFactory factory = new FWOutputFactory();
			writer = new FileOutputFactory()
					.createFactory(new String[] { this.getClass().getResource("Output.csv").getPath() }).getWriter();
			factory.createFactory(properties, writer);
			assertInstanceOf(FWEncoder.class, factory.getEncoder());
			assertEquals("FW", factory.getFormat());
		} catch (ConfigProcessorException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void fwEncoderTest() {
		assertThrows(InvalidParameterException.class, () -> {
			var encoder = new FWEncoder(null, null);
		});

		File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());

		InputReaderInterface reader;
		try {
			List<Property> properties = ConfigProcessor.parseConfig(configFile.getAbsolutePath());
			OutputWriterInterface writer;
			writer = new FileOutputFactory()
					.createFactory(new String[] { this.getClass().getResource("fwEncoderTest.txt").getPath() })
					.getWriter();
			FWEncoder encoder = new FWEncoder(properties, writer);
			encoder.encodeUnit("Stoyan,23,SI");

			reader = new FileInputFactory()
					.createFactory(new String[] { this.getClass().getResource("fwEncoderTest.txt").getPath() })
					.getReader();
			Decoder decoder = new CSVDecoder(null, reader);

			assertEquals(
					"Stoyan              23                                                SI                                                ",
					decoder.getUnit());

		} catch (IOException | ConfigProcessorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
