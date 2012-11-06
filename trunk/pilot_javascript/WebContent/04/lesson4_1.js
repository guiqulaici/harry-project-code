/**
 * javascript类的定义和方法的定义是一样的。因此，你可以认为，javascript的方法和类是一回事
 */
// 定义一个class 定义了一个Shape的类
// 1、私有和公有的全局变量
function Shape() {
	var height = 100; // 这里我们定义个这个Shape类的全局变量，由于它是用var定义的，因此这个全局变量只能被这个Shape访问，其他作用域就无法访问，可以理解成这个变量是private的全局变量，即作用域仅限Shape
	var width = 100;
	this.num = 120; // javascript中的this和java中的this是不一样的，java的this其实说的就是当前类本身（具体可以查看java基础），而js则是定义了num是一个js文件作用域的变量，即除了Shape可以访问，其他地方也可以方法，类似java中全局变量为public的
}

// 初始化类
var aShape = new Shape();
alert(aShape.num);// 用类名，咱们可以访问到num，而访问不到height和width

// ---------------------------------分割线---------------------------------------
// 2、私有函数和公有函数
function Shape2() {
	// 私有函数（前面章节咱们说过，函数定义有2种方式，这是第二种，貌似第一种定义方式不能定义成私有的 ）
	var draw = function() {
		alert("我是私有函数");
	};
	
	this.draw2 = function() {
		alert("我是公有函数");
	};
}

var aShap2 = new Shape2();
aShap2.draw2();

// -------------------------------------分割线-----------------------------------
// 3、构造函数，js是没有构造函数的，但是我们可以模拟一个构造函数,为什么说模仿，因为js不是面向对象的语言，它是解释型的语言
function Shape3(a, b) {
	var x = 0;
	var y = 0;
	
	var init = function() {
		x = a;
		y = b;
	};
	
	init();
	
	
	// 模拟get方法, 这里定义了一个public的function，把x的值返回
	this.getX = function() {
		return x;
	};
}

// 模仿了面向对象的构造函数
var aShap3 = new Shape3(1, 2);


// -----------------------------------分割线--------------------------------------
// 4、静态变量和函数
// java里面静态的变量和方法是作用在变量和方法上的，比如在类里面添加static关键字，但是
// javascript里面静态属性时作用在类上的，如下
function Person() {
	// 定义一个Person类
	this.name = "Harry ye";
}

// 给Person类，利用原型模式给他添加一个getCount方法
Person.getCount = function(x, y) {
	alert(x,y);
};

// 调用person的getConut方法
Person.getCount(1, 2);

// 利用原型模式，给Person添加getName的方法
Person.getName = function() {
	// getName相当于Person类的一个静态方法，但是这个静态方法里面访问了Person类
	// 里非静态的属性，因此，我们需要初始化这个对象，这个和java是一样的
	alert(new Person().name);
};