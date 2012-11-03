/**
 * 函数的递归,这是常用的递归用法
 */

var num = 0;

var count = function(start, end) {
	num = num + start;
	
	if (start < end) {
		count(start+1, end);
	}
	
	return num;
};

alert(count(1,100));


/**
 * eval的用法:把一个字符串解析成一个方法并且调用
 * 
 */

var str = "[1,2,3,4,5,6,7,8,9]";
var array = eval(str); // eval函数将str字符串转换成了数组
for (var i=0 in array) {
	document.write(array[i] +  "<br/>");
}