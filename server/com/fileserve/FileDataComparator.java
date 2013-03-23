package com.fileserve;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

public class FileDataComparator {
	/**
	 * 
	 * @param a
	 * @param b
	 * @return true if a & b contain the same data.
	 */
	public static boolean same(File a, File b) {
		FileInputStream fis_a = null;
		FileInputStream fis_b = null;

		boolean res = true;

		try {
			fis_a = new FileInputStream(a);
			fis_b = new FileInputStream(b);

			int ab = 0;
			int bb = 0;

			while( (ab = fis_a.read()) != -1 || (bb = fis_b.read()) != -1 ) {
				if(ab != bb) {
					res = false;
					break;
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(fis_a);
			IOUtils.closeQuietly(fis_b);
		}
		return res;
	}

	/**
	 * 
	 * @param a
	 * @param b
	 * @return true if a & b do not contain the same data.
	 */
	public static boolean different(File a, File b) {
		return !same(a, b);
	}
}
