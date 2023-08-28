package com.dxc.cherry.converter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dxc.cherry.converter.config.Property;
import com.dxc.cherry.converter.input.CSVInputFactory;
import com.dxc.cherry.converter.input.Decoder;
import com.dxc.cherry.converter.input.DecoderFactory;
import com.dxc.cherry.converter.input.FileInputFactory;
import com.dxc.cherry.converter.input.InputReaderFactory;
import com.dxc.cherry.converter.output.CSVOutputFactory;
import com.dxc.cherry.converter.output.Encoder;
import com.dxc.cherry.converter.output.EncoderFactory;
import com.dxc.cherry.converter.output.FWOutputFactory;
import com.dxc.cherry.converter.output.FileOutputFactory;
import com.dxc.cherry.converter.output.OutputWriterFactory;

public final class TheBestConverter { 
	
	
	private Map<String, DecoderFactory> decoderFactories = new HashMap<>();
	private Map<String, EncoderFactory> encoderFactories = new HashMap<>();
	private Map<String, InputReaderFactory> inputFactories = new HashMap<>();
	private Map<String, OutputWriterFactory> outputFactories = new HashMap<>();
	
	public TheBestConverter(List<Property> config, String inputFile, String outputFile) throws IOException {
		addInputFactory("File", new FileInputFactory().createFactory(new String[] {inputFile}));
		addDecoderFactory("CSV", new CSVInputFactory().createFactory(config, inputFactories.get("File").getReader()));
		
		addOutputFactory("File", new FileOutputFactory().createFactory(new String[] {outputFile}));
		addEncoderFactory("FW", new FWOutputFactory().createFactory(config, outputFactories.get("File").getWriter()));
	}
	public void addDecoderFactory(String type, DecoderFactory factory) {
		decoderFactories.put(type, factory);
	}
	public void addEncoderFactory(String type, EncoderFactory factory) {
		encoderFactories.put(type, factory);
	}
	
	public void addInputFactory(String type, InputReaderFactory factory) {
		inputFactories.put(type, factory);
	}
	public void addOutputFactory(String type, OutputWriterFactory factory) {
		outputFactories.put(type, factory);
	}
	
	
	public InputReaderFactory getInputReaderFactory(String type) {
		return inputFactories.get(type);
	}
	public OutputWriterFactory getOutputWriterFactory(String type) {
		return outputFactories.get(type);
	}
	public DecoderFactory getDecoderFactory(String type) {
		return decoderFactories.get(type);
	}
	public EncoderFactory getEncoderFactory(String type) {
		return encoderFactories.get(type);
	}

	//should accept other parameters
	public void Convert(String inputType,String outputType) throws IOException {
		//creates new custom converter, based on the different decoder and encoder types
		Decoder decoder = getDecoderFactory(inputType).getDecoder();
		Encoder encoder = getEncoderFactory(outputType).getEncoder();
		new Converter(decoder, encoder).convert();
	}
}
