package com.fileserve.server;

import java.util.prefs.Preferences;

public class ServerPreferences {
	public static Preferences server = Preferences.userRoot().node("server");
}
