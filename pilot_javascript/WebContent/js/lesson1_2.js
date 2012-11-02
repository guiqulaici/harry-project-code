/**
 * javascript的数组
 * 定义: var arr = new Array();
 * 属性:
 * 	constructor 返回创建此对象的数组的函数引用
 * 	index
 * 	length 长度
 *  input
 * 方法:
 * 	concat 合并数组
 *  join 把数组按照一定的各式进行串联,相比toString更智能，toString只能以逗号来串联数组
 *  push 数组追加
 *  pop 删除数组返回最后一个元素
 * */
var arr = new Array();
arr.push(1);
arr.push(2);
arr.push("3");
arr.push("3");
arr.push("6");
arr.push("7");
document.write("arr.length = " + arr.length + "<br/>");
document.write("arr[" + arr.toString() + "]<br/>");

var arr2 = [1,2,3,4,5,6,6,7,8,9];
document.write(arr2.join(";") + "<br/>");
document.write(arr.concat(arr2).join("#") + "<br/>");

document.write("=============================================<br/>");
// 扩展js方法, 这是拿原型模式扩展了，后面将
Array.eacha = function(array, fn){
	for (var i = 0; i < array.length; i++) {
		fn(array[i]);
	}
};

Array.eacha(arr,function(v) {
	document.write(v + "<br/>");
});