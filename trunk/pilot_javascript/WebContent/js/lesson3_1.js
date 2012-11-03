/**
 * javascript函数的定义
 */

// 定义方式1
/**
 * function 函数名字(参数1, 参数2, ... 参数N) 
 * { 
 * 		// 代码 
 * 		// return值（没有返回值可以不写，这里和java不一样不需要写void等） 
 * }
 */
 
 // 定义方式2
 /**
  * var fn = function () {
  * 	// 代码
  * 	// 返回值
  * }
  */
  
  // 方式一和方式二的区别在于，在调用函数时，方式1的调用函数语句可以写在方法体的前面和后面，但是方式二调用函数语句只能写在方法体的后面
  var fn = function (x, y) {
  	alert(x + y);
  }(1, 2); // 在函数后面直接加上()并传入参数就可以直接调用当前函数，java没这样的
  
  var add = function(x, y) {
  	alert("x=" + x + ";y=" + y);
  	return 1-2;
  };
  
  var value = add(1,2); // 如果方法add含有返回值，那么我们可以定义一个变量来接收这个返回值。否则就是undefined
  alert("return:" + value);
  
