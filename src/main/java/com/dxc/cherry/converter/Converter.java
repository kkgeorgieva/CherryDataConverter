package com.dxc.cherry.converter;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dxc.cherry.converter.input.Decoder;
import com.dxc.cherry.converter.output.Encoder;
import com.dxc.cherry.converter.output.OutputWriter;
import com.dxc.cherry.converter.output.OutputWriterInterface;

/**
 * Converter is a class that contains the supported file formats, instances of
 * reader and writer for the needed formats for conversion. Its' task is to
 * execute the whole converting process and throw exceptions when this is not
 * possible.
 */
public class Converter {
	
	private static Logger logger = LogManager.getLogger(Converter.class);

	public Decoder decoder;
	public Encoder encoder;

	/**
	 * Creates an instance of the converter
	 * 
	 * @param Decoder A formatter for the input file
	 * @param Encoder A formatter for the output file
	 */
	public Converter(Decoder decoder, Encoder encoder) {
		this.decoder = decoder;
		this.encoder = encoder;
	}

	/**
	 * Method that executes the whole process of converting from one file format to
	 * another. If the output file does not exists, it creates a new file in the
	 * user's system and writes to it.
	 * @throws IOException 
	 * 
	 
	 */
	public void convert() throws IOException {
		    try {
		        String currentUnit;

		        do {
		            currentUnit = decoder.getUnit();
		            if (currentUnit != null && !currentUnit.isEmpty()) {
		                encoder.encodeUnit(currentUnit);
		            }
		        } while (currentUnit != null && !currentUnit.isEmpty());
		    } catch (IOException e) {
		        logger.error(e);
		        System.out.println(e.getMessage());
		        throw new IOException(e.getMessage());
		    }

	}
}
