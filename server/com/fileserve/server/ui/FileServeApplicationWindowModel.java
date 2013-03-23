package com.fileserve.server.ui;

import java.io.File;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;

import com.esotericsoftware.kryonet.Connection;
import com.fileserve.server.FileServeEventListener;
import com.fileserve.server.FileServeServer;
import com.fileserve.server.ServerPreferences;

public class FileServeApplicationWindowModel implements FileServeEventListener {

	FileServeApplicationWindow applicationWindow;
	DirectoryDialog directoryDialog;

	FileServeServer server = new FileServeServer();

	public void bind(FileServeApplicationWindow window) {
		this.applicationWindow = window;
		this.initDefaults();
		this.addEvents();
	}

	public void addEvents() {
		if(this.applicationWindow == null) return;
		System.out.println("Adding events...");
		this.addServerStartEvent();
		this.addDirectoryChooseEvent();
	}

	public void initDefaults() {
		int port = ServerPreferences.server.getInt("tcpPort", 1235);
		String startDirectory = ServerPreferences.server.get("topDir", System.getProperty("user.home"));

		System.out.println("PORT: " + port);

		this.applicationWindow.getServerTCPPortField().setText(Integer.toString(port));
		this.applicationWindow.getHighestDirectoryField().setText(startDirectory);
	}

	private void addServerStartEvent() {

		final Button toggleButton = this.applicationWindow.getServerToggleButton();
		final FileServeServer _server = this.server;
		final FileServeApplicationWindow window = this.applicationWindow;
		final FileServeApplicationWindowModel m = this;

		toggleButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!_server.isRunning()) {

					int port = Integer.parseInt(window.getServerTCPPortField().getText());
					String topDir = window.getHighestDirectoryField().getText();

					ServerPreferences.server.putInt("tcpPort", port);
					ServerPreferences.server.put("topDir", topDir);

					_server.start();
					toggleButton.setText("Stop Server");
					m.setServerStatus("Running");
					window.getHighestDirectoryField().setEnabled(false);
					window.getServerTCPPortField().setEnabled(false);
				} else {
					_server.stop();
					toggleButton.setText("Start Server");
					m.setServerStatus("Stopped");
					window.getHighestDirectoryField().setEnabled(true);
					window.getServerTCPPortField().setEnabled(true);
				}
			}
		});
	}

	void setServerStatus(String string) {
		this.applicationWindow.getServerStatusLabel().setText("Server Status: " + string);
	}

	private void addDirectoryChooseEvent() {
		final FileServeApplicationWindowModel windowModel = this;

		this.applicationWindow.getDirectorySelectionButton().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(windowModel.getWindow().getHighestDirectoryField().getText().trim().length() > 0) {
					windowModel.getDirectoryDialog().setFilterPath(
							windowModel.getWindow().getHighestDirectoryField().getText());
				}
				String dir = windowModel.getDirectoryDialog().open();
				windowModel.getWindow().getHighestDirectoryField().setText(dir);
			}
		});
	}

	public DirectoryDialog getDirectoryDialog() {
		if(this.directoryDialog == null) {
			this.directoryDialog = new DirectoryDialog(this.applicationWindow.getShell());
		}
		return this.directoryDialog;
	}

	public FileServeApplicationWindow getWindow() {
		return this.applicationWindow;
	}


	// File Serve Listening methods.

	@Override
	public void connected(Connection connection) {

	}

	@Override
	public void disconnected(Connection connection) {

	}

	@Override
	public void fileTransferStart(File file, Connection connection) {

	}

	@Override
	public void fileTransferEnd(File file, Connection connection) {

	}
}
