package com.fileserve.util;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

public class UIUtil {
	public static void toEDT(Runnable r, boolean wait) {
		if(wait) waitOnEDT(r);
		else toEDT(r);
	}

	public static void waitOnEDT(Runnable r) {
		if(SwingUtilities.isEventDispatchThread()) {
			r.run();
		} else {
			try {
				SwingUtilities.invokeAndWait(r);
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void toEDT(Runnable r) {
		if(SwingUtilities.isEventDispatchThread()) {
			//new Thread(r).start();
			r.run();
		} else {
			SwingUtilities.invokeLater(r);
		}
	}
}
