package com.dxc.cherry.converter;

public class TheBestConverter {
	//map with decoder factories
	//map with encoder factories
	//map with input/reader factories
	//map with output/writer factories
	
	public TheBestConverter() {
		//accepts config data(property list?)
	}
	public void addDecoderFactory() {
		
	}
	public void addInputFactory() {
		
	}
	
	public void addEncoderFactory() {
		
	}
	public void addOutputFactory() {
		
	}

	//should accept other parameters
	public void Convert(String inputType,String outputType) {
		//creates new custom converter, based on the different decoder and encoder types
		new Converter(null, null);
	}
}
