package com.wh.wechat;

import org.apache.log4j.Logger;

/**
 * Created by Whiker on 2016/1/23.
 */
public class MyLogger {

	private static Logger logger = Logger.getLogger(MyLogger.class);

	private MyLogger() {
	}

	public static void logError(String msg) {
		logger.error(msg);
	}

	public static void logDebug(String msg) {
		logger.debug(msg);
	}

	public static void logInfo(String msg) {
		logger.info(msg);
	}
}
