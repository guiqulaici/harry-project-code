/**
 * javascript类的定义和方法的定义是一样的。因此，你可以认为，javascript的方法和类是一回事
 */
// 定义一个class
function Shape() {
	// 定义了一个Shape的类
	var height = 100; // 这里我们定义个这个Shape类的全局变量，由于它是用var定义的，因此这个全局变量只能被这个Shape访问，其他作用域就无法访问，可以理解成这个变量是private的
	var width = 100;
}