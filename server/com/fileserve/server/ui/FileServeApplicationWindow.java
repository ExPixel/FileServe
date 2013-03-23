package com.fileserve.server.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import swing2swt.layout.BorderLayout;

public class FileServeApplicationWindow {

	protected Shell shlFileServe;
	protected Composite composite;
	protected Label lblServerTcpPort;
	protected Text serverTCPPortField;
	protected Button btnStartServer;
	protected Label lblServerStatus;
	protected Text text;
	protected Label lblHighestDirectory;
	protected Button button;
	protected Group grpConnectionInformation;
	protected ScrolledComposite scrolledComposite;
	protected Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FileServeApplicationWindow window = new FileServeApplicationWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void open() {
		this.open(null);
	}

	/**
	 * Open the window.
	 * @param afterCreation
	 */
	public void open(Runnable afterCreation) {
		Display display = Display.getDefault();
		this.createContents();
		afterCreation.run();
		this.shlFileServe.open();
		this.shlFileServe.layout();
		while (!this.shlFileServe.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {

		final FileServeApplicationWindow appWindow = this;

		this.shlFileServe = new Shell();
		this.shlFileServe.setSize(450, 300);
		this.shlFileServe.setText("File Serve - Server");
		this.shlFileServe.setLayout(new BorderLayout(0, 0));

		this.composite = new Composite(this.shlFileServe, SWT.NONE);
		this.composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		this.composite.setLayoutData(BorderLayout.CENTER);
		this.composite.setLayout(new FormLayout());

		this.lblServerTcpPort = new Label(this.composite, SWT.NONE);
		FormData fd_lblServerTcpPort = new FormData();
		fd_lblServerTcpPort.top = new FormAttachment(0, 10);
		fd_lblServerTcpPort.left = new FormAttachment(0, 10);
		this.lblServerTcpPort.setLayoutData(fd_lblServerTcpPort);
		this.lblServerTcpPort.setText("Server TCP Port:");

		this.serverTCPPortField = new Text(this.composite, SWT.BORDER);
		this.serverTCPPortField.setTextLimit(5);
		this.serverTCPPortField.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				e.doit = true;
				for(int i = 0; i < e.text.length(); i++) {

					char c = e.text.charAt(i);

					boolean b = false;

					if(c >= '0' && c <= '9') {b = true;}
					else if(c == SWT.BS) {b = true;}
					else if(c == SWT.DEL) {b = true;}

					if(b == false) {
						e.doit = false;
						break;
					}
				}
			}
		});
		FormData fd_serverTCPPortField = new FormData();
		fd_serverTCPPortField.top = new FormAttachment(0, 7);
		fd_serverTCPPortField.right = new FormAttachment(100, -54);
		fd_serverTCPPortField.left = new FormAttachment(this.lblServerTcpPort, 12);
		this.serverTCPPortField.setLayoutData(fd_serverTCPPortField);

		this.btnStartServer = new Button(this.composite, SWT.NONE);
		FormData fd_btnStartServer = new FormData();
		fd_btnStartServer.bottom = new FormAttachment(100, -10);
		fd_btnStartServer.right = new FormAttachment(100, -10);
		this.btnStartServer.setLayoutData(fd_btnStartServer);
		this.btnStartServer.setText("Start Server");

		this.lblServerStatus = new Label(this.composite, SWT.NONE);
		FormData fd_lblServerStatus = new FormData();
		fd_lblServerStatus.right = new FormAttachment(this.btnStartServer, -6);
		fd_lblServerStatus.top = new FormAttachment(this.btnStartServer, 5, SWT.TOP);
		fd_lblServerStatus.left = new FormAttachment(this.lblServerTcpPort, 0, SWT.LEFT);
		this.lblServerStatus.setLayoutData(fd_lblServerStatus);
		this.lblServerStatus.setText("Server Status: Stopped");

		this.text = new Text(this.composite, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(this.serverTCPPortField, 6);
		this.text.setLayoutData(fd_text);

		this.lblHighestDirectory = new Label(this.composite, SWT.NONE);
		fd_text.left = new FormAttachment(this.lblHighestDirectory, 6);
		FormData fd_lblHighestDirectory = new FormData();
		fd_lblHighestDirectory.top = new FormAttachment(this.lblServerTcpPort, 12);
		fd_lblHighestDirectory.left = new FormAttachment(this.lblServerTcpPort, 0, SWT.LEFT);
		this.lblHighestDirectory.setLayoutData(fd_lblHighestDirectory);
		this.lblHighestDirectory.setText("Highest Directory:");

		this.button = new Button(this.composite, SWT.NONE);

		fd_text.right = new FormAttachment(100, -54);
		FormData fd_button = new FormData();
		fd_button.left = new FormAttachment(this.text, 6);
		this.button.setLayoutData(fd_button);
		this.button.setText("...");

		this.grpConnectionInformation = new Group(this.composite, SWT.NONE);
		fd_button.bottom = new FormAttachment(this.grpConnectionInformation, -1);
		this.grpConnectionInformation.setText("Connection Information:");
		this.grpConnectionInformation.setLayout(new BorderLayout(0, 0));
		FormData fd_grpConnectionInformation = new FormData();
		fd_grpConnectionInformation.bottom = new FormAttachment(this.btnStartServer, -6);
		fd_grpConnectionInformation.right = new FormAttachment(this.btnStartServer, 0, SWT.RIGHT);
		fd_grpConnectionInformation.top = new FormAttachment(this.lblHighestDirectory, 6);
		fd_grpConnectionInformation.left = new FormAttachment(this.lblServerTcpPort, 0, SWT.LEFT);
		this.grpConnectionInformation.setLayoutData(fd_grpConnectionInformation);

		this.scrolledComposite = new ScrolledComposite(this.grpConnectionInformation, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		this.scrolledComposite.setLayoutData(BorderLayout.CENTER);
		this.scrolledComposite.setExpandHorizontal(true);
		this.scrolledComposite.setExpandVertical(true);

		this.table = new Table(this.scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		this.table.setLinesVisible(true);
		this.table.setHeaderVisible(true);
		this.scrolledComposite.setContent(this.table);
		this.scrolledComposite.setMinSize(this.table.computeSize(SWT.DEFAULT, SWT.DEFAULT));

	}
	public Button getDirectorySelectionButton() {
		return this.button;
	}
	public Text getHighestDirectoryField() {
		return this.text;
	}
	public Text getServerTCPPortField() {
		return this.serverTCPPortField;
	}
	public Button getServerToggleButton() {
		return this.btnStartServer;
	}
	public Label getServerStatusLabel() {
		return this.lblServerStatus;
	}
	public Shell getShell() {
		return this.shlFileServe;
	}
}
