package com.dxc.cherry.converter.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.dxc.cherry.converter.ConverterApplication;

class ConverterApplicationTest {

	@Test
	void lessThan5ArgsTest() {

		// passing in 4 arguments
		String[] args = new String[] { "inputFile", "outputFile", "configFile", "inputFileType" };
		try {
			ConverterApplication.lessThan5Args(args);
		} catch (IllegalArgumentException e) {
			assertEquals(
					"ERROR! Make sure you have included all the parameters in the following order:"
							+ " inputFilePath, outputFilePath, configFilePath, inputFileType, outputFileType",
					e.getMessage());
		}
	}
	
	

}
