package com.harry.test;

/**
 * <pre>
 * 一、应用范围 
 *     一般用在登录限制、查找IP所在城市等需求中,windows的ping命令也支持整数形式的IP。 
 * 
 * 二、关键技术点 
 *     将IP地址转化成整数的方法如下： 
 *     1、通过String的indexOf方法找出IP字符串中的点"."的位置。 
 *     2、根据点的位置，使用String的substring方法把IP字符串分成4段。 
 *     3、使用Long的parseLong方法把子段转化成一个3位整数。 
 *     4、通过左移位操作（<<）给每一段的数字加权，第一段的权为2的24次方，第二段的权为2的16次方，第三段的权为2的8次方，最后一段的权为1 
 *     
 *     将整数形式的IP地址转化成字符串的方法如下： 
 *     1、将整数值进行右移位操作（>>>），右移24位，右移时高位补0，得到的数字即为第一段IP。 
 *     2、通过与操作符（&）将整数值的高8位设为0，再右移16位，得到的数字即为第二段IP。 
 *     3、通过与操作符吧整数值的高16位设为0，再右移8位，得到的数字即为第三段IP。 
 *     4、通过与操作符吧整数值的高24位设为0，得到的数字即为第四段IP
 * 
 * </pre>
 * 
 * @author Harry
 * 
 */
public class IP2Long {

	// 将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
	public static long ipToLong(String strIp) {
		long[] ip = new long[4];
		// 先找到IP地址字符串中.的位置
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	// 将十进制整数形式转换成127.0.0.1形式的ip地址
	public static String longToIP(long longIp) {
		StringBuffer sb = new StringBuffer("");
		// 直接右移24位
		sb.append(String.valueOf((longIp >>> 24)));
		sb.append(".");
		// 将高8位置0，然后右移16位
		sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
		sb.append(".");
		// 将高16位置0，然后右移8位
		sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
		sb.append(".");
		// 将高24位置0
		sb.append(String.valueOf((longIp & 0x000000FF)));
		return sb.toString();
	}

	/** */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ipStr = "211.136.115.11";
		long longIp = IP2Long.ipToLong(ipStr);
		System.out.println("192.168.0.1 的整数形式为：" + longIp);
		System.out.println("整数" + longIp + "转化成字符串IP地址："
				+ IP2Long.longToIP(longIp));
		// ip地址转化成二进制形式输出
		System.out
				.println("192.168.0.1 的二进制形式为：" + Long.toBinaryString(longIp));

		System.out.println(211l * 256 * 256 * 256 + 136l * 256 * 256 + 115l
				* 256 + 11l);
	}
}