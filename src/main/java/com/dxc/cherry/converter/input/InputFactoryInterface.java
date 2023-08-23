package com.dxc.cherry.converter.input;

import java.util.List;

import com.dxc.cherry.converter.config.Property;
import com.dxc.cherry.converter.output.Encoder;
import com.dxc.cherry.converter.output.OutputWriterInterface;

public interface InputFactoryInterface {
	public InputFactoryInterface createFactory(List<Property> config, InputReaderInterface reader);
	public Decoder getDecoder();
}
