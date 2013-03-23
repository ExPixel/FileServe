package com.fileserve.util;


public class FileServeLog {

	static FileServeLogger logger = new DefaultFileServeLogger();


	public static void info(String info) {
		logger.log(info, FileServeLogLevel.Info);
	}

	public static void debug(String debug) {
		logger.log(debug, FileServeLogLevel.Debug);
	}

	public static void warning(String warning) {
		logger.log(warning, FileServeLogLevel.Warning);
	}

	public static void error(String error) {
		logger.log(error, FileServeLogLevel.Error);
	}

	public static void fatal(String fatal) {
		logger.log(fatal, FileServeLogLevel.Fatal);
	}


	public static void infof(String info, Object...args) {
		info(String.format(info, args));
	}

	public static void debugf(String info, Object...args) {
		debug(String.format(info, args));
	}

	public static void warningf(String info, Object...args) {
		warning(String.format(info, args));
	}

	public static void errorf(String info, Object...args) {
		error(String.format(info, args));
	}

	public static void fatalf(String info, Object...args) {
		fatal(String.format(info, args));
	}

}
