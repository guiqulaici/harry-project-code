/**
 * 数据类型和数据转换
 * 
 * 数据类型
 * 1.数字型，number
 * 2.布尔型，booble
 * 3.字符串型，string
 */

// ================================数字=================================
// 数字型转化成字符型
var num1 = 2.15;
var str1 = Number.toString(num1);
document.write(typeof str1 == "string"); // true
document.write("<br/>");

// 四舍五入
// toFixed(n)方法四舍五入，n表示保留几位小数
var num2 = 3.1415926;
document.writeln(num2.toFixed(4));//3.1416
document.write("<br/>");

// 返回数字前几位
var num3 = 3.1415926;
var num4 = num3.toPrecision(4);//3.142返回数字前4位
document.write(num4);
document.write("<br/>");

// 函数Math
// round实现四舍五入
document.write(Math.round(4.128)); // 4
document.write("<br/>");

// 随机数,默认返回0-1的一个随机数
document.write(Math.random());
document.write("<br/>");

// 0-10的随机数
document.write(Math.floor((Math.random()*11)));
document.write("<br/>");

 
// ====================================字符串=============================
// 转义跟java一样都是使用"\", 比如 \&就是加号 
// 字符串转换成数字
var str2 = "ABCDEFG";
var str3 = "3.14";
var num5 = Number(str3);
document.write(typeof num5=="number");// true
document.write("<br/>");
document.write(str2 - 2);// NaN :表示非数值
document.write("<br/>");
document.write(str3 - 1); // 2.14 如果是减法会自动将数值字符串转换成数值,即自动转型
document.write("<br/>");
document.write(str3 + 1); // 3.141 如果是加法，会当成字符串的拼接


// ===============================布尔型=====================================
// boolean (true | false)
var s = ""; // false
var o = {}; // 空对象，但是java里面{}表示空数组，这里有点不一样
var l = []; // 空数组
var n = null; // false
var f = false; // false
var u = undefined; // false
// javascript里面，尽管是空数组和对象，都认为是真，空字符，null，false和undefined认为是假.js里面可以用任意类型的对象作为if的条件来判断真假，但是java不行，java只能用运算符或者boolean类型作为if条件
// 比如:
if (s) {
	document.write("s is true! <br/>");
}

// ============================复合类型==================================
// 数组:有序集合(array),例子：var arr = new Array();
// 函数



// =============================特殊值===================================
// null:代表空，不是有效的对象比如：数组，数字，字符串
// undefined：代表没有定义



// ==============================内置特殊对象===========================
// Date  日期对象
// Error 错误对象
// ReExp 正则对象







