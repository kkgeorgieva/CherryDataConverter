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
import com.dxc.cherry.converter.TheBestConverter;
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

public class ConverterTest {

	@Test
	public void theBestConverterTest() {
		File configFile = new File(this.getClass().getResource("configTemplate.yaml").getFile());
		try {
			List<Property> properties = ConfigProcessor.parseConfig(configFile.getAbsolutePath());
			TheBestConverter converter = new TheBestConverter(properties,
					this.getClass().getResource("Test.csv").getPath(),
					this.getClass().getResource("Output.csv").getPath());
			converter.Convert("CSV", "FW");

			InputReaderInterface reader;
			try {
				reader = new FileInputFactory()
						.createFactory(new String[] { this.getClass().getResource("Output.csv").getPath() })
						.getReader();
				Decoder decoder = new CSVDecoder(null, reader);
				assertEquals(
						"Stoyan              23                                                SI                                                ",
						decoder.getUnit());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
