package com.dxc.file.reader;

import java.util.ArrayList;

// For every file type there will be a corresponding reader
// that will be in charge of processing the specific file type
public interface FileReaderInterface {
	
	public ArrayList<String> readFile(String filePath);

}
