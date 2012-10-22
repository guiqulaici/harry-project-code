package com.yeahwap.netgame;

import com.yeahwap.netgame.util.PropertyUtil;

/**
 * Create on 2012-10-11
 * 
 * @author Harry
 *         <ul>
 *         <li>Title: Constants.java</li>
 *         <li>description: TODO</li>
 *         <ul>
 */
public class Constants {
	public static final String CACHE_ADDRESS = PropertyUtil.getProperty("cache.address");
	public static final String METHOD = PropertyUtil.getProperty("method");
	public static final String HEADER_VALID = PropertyUtil.getProperty("header.valid");
	public static final int LOG_BATCH_SIZE = Integer.parseInt(PropertyUtil.getProperty("log.batch.size"));
	public static final int LOG_TIMEOUT = Integer.parseInt(PropertyUtil.getProperty("log.batch.timeout"));
	
	
}
