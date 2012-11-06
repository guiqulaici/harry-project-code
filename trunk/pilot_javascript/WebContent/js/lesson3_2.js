/**
 * javascript函数的回调
 */
var add = function(a, b, fn) { // 就算我们将方法fn作为参数传入另一个方法add，不管传入的fn是否含有参数，我们都不必写进去;java里面是不能把方法体作为参数传入的，java只能把变量，对象，集合等作为参数
	// js不像java，定义一个变量，作为类的全局变量，用this代表当前类的这个全局变量；
	// js定义全局变量就是用this.变量名来定义的，这里x就是类add的全局变量后面有详细提到
	this.x = a || 0; //这里的意思是如果a参数传入的是空，那么就把0赋值给当前定义的全局变量x,这个全局变量跟java有点不一样，不能直接alert(x),它必须用new add().x来访问
	this.y = b || 0;
	document.write(x + ";" + y);
	fn(x+y);
}; 

add("","",function(v) {
	alert(v);
}); //直接加上(),并且传参（如果需要传入参数的话）就是调用函数了

// 切记: js里面如果你真的需要定义一个字符串，那么你最好用“”引起来，因为，只有数字不引起来才会自动转换成字符串，但是其他的是不会的，比如abc，你不引起来，js会认为abc是你定义的一个变量