package com.dxc.cherry.converter.input;

import java.util.List;

import com.dxc.cherry.converter.config.Property;
import com.dxc.cherry.converter.output.Encoder;
import com.dxc.cherry.converter.output.OutputWriterInterface;

public interface DecoderFactory {
	public DecoderFactory createFactory(List<Property> config, InputReaderInterface reader);
	public Decoder getDecoder(); //getInstance();
	
	//getFortmat() -> to understand what type of input is being used
	// getConfigSection()? mai go imame ama nz kude
}
