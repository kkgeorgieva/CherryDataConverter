package com.dxc.file.writer;

import java.util.ArrayList;

//For every file type there will be a corresponding writer
//that will be in charge of processing the specific file type
public interface FileWriter {
	
	// method to be implemented in the corresponding writer
	void write(ArrayList<String> input, String fileName);

}
