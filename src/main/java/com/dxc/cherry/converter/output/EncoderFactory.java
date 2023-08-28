package com.dxc.cherry.converter.output;

import java.util.List;

import com.dxc.cherry.converter.config.Property;
import com.dxc.cherry.converter.output.Encoder;
import com.dxc.cherry.converter.output.OutputWriterInterface;

public interface EncoderFactory {
	public EncoderFactory createFactory(List<Property> config, OutputWriterInterface writer);
	public Encoder getEncoder();
	public String getFormat();
}
