package com.dxc.system;

import com.dxc.file.reader.CSVFileReader;
import com.dxc.file.reader.FileReaderInterface;
import com.dxc.file.writer.FileWriter;

public class Converter {
	// The converter will store the supported file types
	//Throws exception when the requested file type is not supported by the converter
	
	private FileReaderInterface fileReader;
	private FileWriter fileWriter;
	
	public Converter(FileReaderInterface fileReader, FileWriter fileWriter) {
		this.fileReader = fileReader;
		this.fileWriter = fileWriter;
	}
	
	public void convert(String filePath) {
		fileReader.readFile(filePath);
	}
	
	public static void main(String[] args) {
		Converter cvt = new Converter(new CSVFileReader(), null);
		
		cvt.convert("C:\\ws\\Test.csv");
		
	}

}
