package com.fileserve.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.joda.time.DateTime;

public class DefaultFileServeLogger implements FileServeLogger {

	PrintStream printStream;


	public DefaultFileServeLogger() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(this.logFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.printStream = new PrintStream(fos, true);
	}

	public File logFile() {
		File log = new File("log.txt");
		if(!log.exists()) {
			if(log.getParentFile() != null)
				log.getParentFile().mkdirs();
			try {
				log.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return log;
	}

	@Override
	public void log(String text, FileServeLogLevel level) {
		if(this.printStream != null) {
			DateTime dt = new DateTime();
			String t = String.format( "[%d:%d:%d] %s: %s",
					dt.getHourOfDay(),
					dt.getMinuteOfDay(),
					dt.getSecondOfDay(),
					level.toString().toUpperCase(),
					text);
			this.printStream.println(t);
		}
	}

}
