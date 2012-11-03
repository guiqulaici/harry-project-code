/**
 * js的if，switch这些条件语句和java是一样的，这里我们着重提一下for，和while的用法
 */

// while的用法与java相比较，也是一样的
var arr = new Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
var length = arr.length;

while (length > 0) {
	document.write(arr[length-1] + "<br/>");
	--length;
}

// for的用法与java想比较，也是一样
document.write("===============for语法===================<br/>");
for (var i = 0; i < arr.length; i++) {
	document.write(arr[i] + "<br/>");
}

// for的in用法.循环的var i其实是下标,循环一个对象也是，其实是他们的key值
// java里面是没有for ... in的，java里面是for(object o : objects)拿到的o是一个真实object对象而不是这里的下标
document.write("=================for...in语法===================<br/>");
var arr2 = new Array();
arr2[0] = "Harry";
//arr2[1] = "YeLei"; //如果我们只定义了index0和index2，缺少index1，那么index1打印的就是;undefined
arr2[2] = "LuJingli";

for(var i in arr2) {
	document.write(arr2[i] + ";" + arr2[1] + "<br/>"); 
}

var o = {name:"Harry", age:26};
for(var v in o) {
	document.write(o[v] + "<br/>");
}













