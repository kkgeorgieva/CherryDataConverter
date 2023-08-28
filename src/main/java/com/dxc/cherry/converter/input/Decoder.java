package com.dxc.cherry.converter.input;

import java.io.IOException;

/**
 * An interface that is in charge of decoding the input file
 */
public interface Decoder {
	public String getUnit() throws IOException;
}
