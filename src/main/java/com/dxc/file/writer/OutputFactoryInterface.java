package com.dxc.file.writer;

import java.util.List;

import com.dxc.file.config.Property;
import com.dxc.file.writer.Encoder;
import com.dxc.file.writer.OutputWriterInterface;

public interface OutputFactoryInterface {
	public OutputFactoryInterface createFactory(List<Property> config, OutputWriterInterface writer);
	public Encoder getEncoder();
}
