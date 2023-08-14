package com.dxc.logger;
// logs info, debug, warning, error, fatal messages into separate files

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProgramLogger {
	
	static Logger logger = LogManager.getLogger(ProgramLogger.class);
	
	public static void log(String message) {
		logger.info(message);
	}
}
