package com.dxc.system;

import com.dxc.file.config.ConfigProcessor;
import com.dxc.file.config.Property;
import com.dxc.file.reader.CSVFileReader;
import com.dxc.file.reader.FileReaderInterface;
import com.dxc.file.writer.CSVFileWriter;
import com.dxc.file.writer.FileWriterInterface;

public class Converter {
	// The converter will store the supported file types
	//Throws exception when the requested file type is not supported by the converter
	
	public FileReaderInterface fileReader;
	public FileWriterInterface fileWriter;
	
	public Converter(FileReaderInterface fileReader, FileWriterInterface fileWriter) {
		this.fileReader = fileReader;
		this.fileWriter = fileWriter;
	}
	
	public static void main(String[] args) {
		Converter cvt = new Converter(new CSVFileReader(), new CSVFileWriter());
		
		cvt.fileWriter.write(cvt.fileReader.readFile("C:\\ws\\Test.csv"), "C:\\ws\\ahahah.csv");
		
		ConfigProcessor cf = new ConfigProcessor("C:\\ws\\app.config");
		
		for (Property pr : cf.getPropsList()) {
			System.out.println(pr.toString());
		}
	}

}
