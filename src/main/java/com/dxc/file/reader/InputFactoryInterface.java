package com.dxc.file.reader;

import java.util.List;

import com.dxc.file.config.Property;
import com.dxc.file.writer.Encoder;
import com.dxc.file.writer.OutputWriterInterface;

public interface InputFactoryInterface {
	public InputFactoryInterface createFactory(List<Property> config, InputReaderInterface reader);
	public Decoder getDecoder();
}
