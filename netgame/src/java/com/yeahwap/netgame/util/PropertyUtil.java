/*
 * Created on 2004-7-29
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.yeahwap.netgame.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author roya
 * @since jdk1.4
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class PropertyUtil {
	private static Properties property = null;

	private static String propertyFile = "/config.properties";

	static {
		if (property == null) {
			reload();
		}
	}

	synchronized public static void reload() {
		InputStream inStream = null;
		if (property != null) {
			property.clear();
			property = null;
		}
		property = new Properties();
		try {
			inStream = PropertyUtil.class.getResourceAsStream(propertyFile);
			property.load(inStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	 * get value from property file
	 * 
	 * @param key
	 * @return String
	 */
	synchronized public static String getProperty(String key) {
		return property.getProperty(key);
	}

	public static void main(String[] args) {
		System.out.println(getProperty("cache.address"));
		reload();
		System.out.println(getProperty("xxx"));
		System.out.println(getProperty("xxx"));
	}
}