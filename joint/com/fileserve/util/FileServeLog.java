package com.fileserve.util;

public class FileServeLog {
	public static void info(String info) {

	}

	public static void error(String error) {

	}

	public static void warning(String warning) {

	}

	public static void debug(String debug) {

	}

	public static void infof(String info, Object...args) {
		info(String.format(info, args));
	}

	public static void errorf(String info, Object...args) {
		error(String.format(info, args));
	}

	public static void warningf(String info, Object...args) {
		warning(String.format(info, args));
	}

	public static void debugf(String info, Object...args) {
		debug(String.format(info, args));
	}
}
