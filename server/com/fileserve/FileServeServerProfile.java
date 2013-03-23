package com.fileserve;

import com.esotericsoftware.minlog.Log;
import com.fileserve.server.ui.FileServeApplicationWindow;
import com.fileserve.server.ui.FileServeApplicationWindowModel;

public class FileServeServerProfile {
	public static void main(String[] args) {
		Log.set(Log.LEVEL_INFO);
		final FileServeApplicationWindow applicationWindow = new FileServeApplicationWindow();
		final FileServeApplicationWindowModel windowModel = new FileServeApplicationWindowModel();
		applicationWindow.open(new Runnable() {
			@Override
			public void run() {
				windowModel.bind(applicationWindow);
			}
		});
	}
}
