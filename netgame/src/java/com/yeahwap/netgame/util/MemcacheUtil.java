package com.yeahwap.netgame.util;

import java.io.Serializable;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import org.apache.log4j.Logger;

import com.yeahwap.netgame.Constants;


/**
 * @author bill
 * 
 */
public class MemcacheUtil {

	private static final Logger log = Logger.getLogger(MemcacheUtil.class);
	private static final int DEFAULT_GET_TIMEOUT_SECOND = 1;
	private static final String DEFAULT_ADDRESS = getAddress();

	private static MemcachedClient mc = null;

	static {
		try {
			mc = new MemcachedClient(AddrUtil.getAddresses(DEFAULT_ADDRESS));
		} catch (Exception e) {
			log.error("memcache initial error !!", e);
		}
	}

	private static String getAddress() {
		String ret = Constants.CACHE_ADDRESS;
		if (null != ret) {
			String[] addresses = ret.split(",");
			ret = "";
			for (String add : addresses) {
				ret += add.trim() + " ";
			}
		}
		return ret.trim();
	}

	public static Future<Boolean> set(String key, int exp, Object o) {
		return mc.set(key, exp, o);
	}

	public static Object get(String key) {
		return get(key, DEFAULT_GET_TIMEOUT_SECOND);
	}

	public static Object get(String key, int timeout) {
		if (key.equals("")) {
			return null;
		}
		Object myObj = null;
		Future<Object> f = null;
		try {
			f = mc.asyncGet(key);
			myObj = f.get(timeout, TimeUnit.SECONDS);
		} catch (Exception e) {
			if (null != f) {
				f.cancel(false);
			}
		}
		return myObj;
	}

	public static Future<Map<String, Object>> getBulk(Collection<String> keys) {
		return mc.asyncGetBulk(keys);
	}

	public static Future<Map<String, Object>> getBulk(String... keys) {
		return mc.asyncGetBulk(keys);
	}

	public static long decr(String key, int by) {
		return mc.decr(key, by);
	}

	public static long decr(String key, int by, int def) {
		return mc.decr(key, by, def);
	}

	public static long incr(String key, int by) {
		long ret = -1;
		try {
			ret = mc.incr(key, by);
		} catch (Exception e) {
		}
		return ret;
	}
	public static long incr(String key,int by,int def,int exp) {
		long ret = -1;
		try {
			ret = mc.incr(key, by, def, exp);
		} catch (Exception e) {
		}
		return ret;
	}

	public static long incr(String key, int by, int def) {
		long ret = -1;
		try {
			ret = mc.incr(key, by, def);
		} catch (Exception e) {
		}
		return ret;
	}

	public static Future<Boolean> delete(String key) {
		return mc.delete(key);
	}

	public static Future<Boolean> delete(String key, int when) {
		return mc.delete(key, when);
	}

	public static Future<Boolean> flush() {
		return mc.flush();
	}

	public static Future<Boolean> flush(int delay) {
		return mc.flush(delay);
	}

	public static Future<Boolean> replace(String key, int exp, Object o) {
		return mc.replace(key, exp, o);
	}

	public static Map<SocketAddress, Map<String, String>> getStats() {
		return mc.getStats();
	}

	static class best implements Serializable {
		int id;
		String name;
		String remark;

		@Override
		public String toString() {
			return id + ":" + name + ":" + remark;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// flush();
		// List<best> a = new ArrayList<best>();
		// best b = new best();
		// b.id =1;
		// b.name = "name";
		// b.remark = "adfadsf";
		// a.add(b);
		// set("bill", 3600, a);
		// System.out.println(decr("bill", 1));
		// System.out.println(incr("bill", 1000));
		System.out.println(get("bill"));
		// replace("bill", 3600, "merry");
		// System.out.println(get("bill"));
		// delete("bill");
		// System.out.println(get("bill"));
		// mc.shutdown();
		System.out.println("[" + getAddress() + "]");
	}

}
