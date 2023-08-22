package com.dxc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.dxc.converter.Converter;
import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.InputReader;
import com.dxc.file.reader.CSVDecoder;
import com.dxc.file.reader.Decoder;
import com.dxc.file.writer.OutputWriter;
import com.dxc.main.app.ConverterApplication;
import com.dxc.file.writer.CSVEncoder;
import com.dxc.file.writer.Encoder;
import com.dxc.file.writer.FWEncoder;

public class ConverterTest {

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
		InputReader reader = new InputReader(inputFile.getAbsolutePath());

		try {
			OutputWriter writer = new OutputWriter("Output.csv");

			List<Property> propsDecoder = ConfigProcessor.getByCategory(reader.getConfigCategory());
			List<Property> propsEncoder = ConfigProcessor.getByCategory(writer.getConfigCategory());

			Decoder decoder = new CSVDecoder(propsDecoder, reader);
			Encoder encoder = new CSVEncoder(propsEncoder, writer);

			String unit = encoder.encodeUnit(decoder.getUnit());
			assertTrue(writer.write(unit));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void fwFileWriterTest() {
		File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
		File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
		File inputFile = new File(this.getClass().getResource("Test.csv").getFile());

		ConfigProcessor.parseConfig(configFile.getAbsolutePath());
		InputReader reader = new InputReader(inputFile.getAbsolutePath());

		try {
			OutputWriter writer = new OutputWriter("Output.csv");

			List<Property> propsDecoder = ConfigProcessor.getByCategory(reader.getConfigCategory());
			List<Property> propsEncoder = ConfigProcessor.getByCategory(writer.getConfigCategory());

			Decoder decoder = new CSVDecoder(propsDecoder, reader);
			Encoder encoder = new FWEncoder(propsEncoder, writer);

			String unit = encoder.encodeUnit(decoder.getUnit());
			assertTrue(writer.write(unit));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	void csvFileReaderTest() {
		File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
		File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
		File inputFile = new File(this.getClass().getResource("Test.csv").getFile());

		ConfigProcessor.parseConfig(configFile.getAbsolutePath());
		InputReader reader = new InputReader(inputFile.getAbsolutePath());

		assertEquals(12, reader.readLine().length());

	}

	@Test
	void converterTest() {
		File outputFile = new File(this.getClass().getResource("Output.csv").getFile());
		File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
		File inputFile = new File(this.getClass().getResource("Test.csv").getFile());

		ConfigProcessor.parseConfig(configFile.getAbsolutePath());
		InputReader reader = new InputReader(inputFile.getAbsolutePath());

		try {
			OutputWriter writer = new OutputWriter("Output.csv");

			List<Property> propsDecoder = ConfigProcessor.getByCategory(reader.getConfigCategory());
			List<Property> propsEncoder = ConfigProcessor.getByCategory(writer.getConfigCategory());

			Decoder decoder = new CSVDecoder(propsDecoder, reader);
			Encoder encoder = new CSVEncoder(propsEncoder, writer);

			Converter cvt = new Converter(decoder, encoder);

			assertTrue(cvt.convert());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	void mainAppTest() {
		File inputFile = new File(this.getClass().getResource("Test.csv").getFile());
		File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
		String inputFileType = "CSV";
		String outputFileType = "FW";

		try {
		ConverterApplication.main(new String[] { inputFile.getAbsolutePath(), "C:\\Windows\\Temp\\mainAppTest.csv",
				configFile.getAbsolutePath(), inputFileType, outputFileType });
		} catch (Exception e) {
			System.out.println("ERROR! Make sure you have included all the parameters in the following order:"
					+ " inputFilePath, outputFilePath, configFilePath, inputFileType, outputFileType");
		}

		File outputFile = new File("C:\\Windows\\Temp\\mainAppTest.csv");
		assertTrue(outputFile.exists());
	}
}
