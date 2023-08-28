package com.dxc.cherry.converter.output;

import java.io.IOException;

/**
 * A class that is in charge of processing information given to it and encodes it as a unit
 */
public interface Encoder {
	public void encodeUnit(String unit) throws IOException;
}
