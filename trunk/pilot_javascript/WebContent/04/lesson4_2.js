/**
 * javascript的原型模式
 * 什么是原型?
 * 答:所有的对象都有原型,一个javascript创建了一个对象（类，集合，等），它里面有一个prototype的属性，
 * 这个prototype持有这个对象的原型在内存中的地址，通过这个prototype就是访问这个对象的原型
 * 
 * javascript中什么是对象?
 * 答：一个对象就是任何的无序键值对的集合；
 * 比如:var s = {"key":"value", "key2":"value"} ，function(){}等数组，函数，类都是对象
 * 除了主数据类型(undefined, number, boolean, null, string )，其他的都称之为对象
 */
 
 /**
  * js中的原型是和函数function紧密联系，但是如果定义一个简单对象var o =  {} 它不是function定义的
  * 它有原型吗?
  * 
  * 答：必须的，但是它的访问原型对象的方式跟function定义的访问原型对象的方式是不一样的，并且不同浏览器也访问方式也不一样。后面讲解。
  */
  
/**
 * 重点：每一个通过new关键字或者简易写法(比如，var o = {})生成的对象，里面都有一个叫"__proto__"的属性，
 * 这个属性保存了创建它的构造函数的原型的引用,也就是说非函数定义生成的对象访问原型是通过"__proto__"来访问原型的
 * function定义生成的对象是通过prototype来访问原型对象的
 */  
 

// 1.function创建对象
function Person() {} // 它是一个空对象
// function创建的对象，通过proptype属性访问原型,也就是说Person.prototype返回的就是Person这个对象的原型
Person.prototype.name = "Harry Ye"; // 给Person的原型添加一个叫name的属性，那么以后这个Person对象就会继承这个name属性
// js里面有原型链的说法，原型链的下方会无条件集成原型链上方的所有属性和方法。
// 以Person为例:(Person) extands (Person.prototype)

Person.prototype.showName = function() {
	// 原型里面的this，即这里的this，代表调用本函数（showName）的类，即 Person
	// 这个this区别于在类里面使用this.name = "xxx"定义public的变量
	alert(this.name);
};

var p = new Person();
p.showName();


// 2.非fuction创建对象
var cat = {};
// var d = new cat();错误的，这种简易方式创建对象是不能用new来实例化对象的,因为简易创建对象已经把这个对象实例化了，就叫cat
// cat.prototype.name = "LuJingli"; 会报错,js会认为cat的prototype是没有定义的，
// 因此，这种创建对象，是没有办法通过pototype来访问到原型的
// 那么，js默认是隐藏以下方式来访问原型的：
// 方式1：受浏览器限制，一般不用。
// Object.getPrototypeof(cat).name = "LuJingli";
// 方式2：
cat.__proto__.sex = "女";
//测试
cat.age = 2; // 给Cat对象添加属性
cat["ID"] = "110"; // 个体cat队形添加属性
//alert(";" + cat.sex + ";" + cat["age"] + ";" + cat.ID);

 
/**
 * 利用原型来实现继承,粗略,js和java一样不支持多继承
 */

function Animal() {
	this.getName = function(name) {
		alert(name);
	};
}

Animal.prototype.getAge = function(age) {
	alert(age);
};

var a = {};
//  a继承Animal
a.__proto__ = Animal.prototype;
a.getAge(123321);
// a.getName("H");这里报错，说并没有getName这个方法，我们只是将Animal的原型指定给了a，它用的是Animal原型的构造函数，换成java的代码如下:
//public class B extands A {
//	public B() {
//		super(); // 疑问，java在继承的时候，子类到底用的是谁的构造函数
//	}
//	
//} 
// 如何解决这个问题呢?我们需要让a的原型构造函数，使用自己的构造函数
a.__proto__.constructor = a;
// a.getName("HL"); // 还是报错，为啥呢？因为咱们继承的是Animal的原型，而不是Animal


var b = {};
// b这个简易的空对象继承animal，并使用自身的构造函数
b.__proto__ = new Animal();
b.__proto__.constructor = b;
//测试
b.getName("HHHHHH");
b.getAge(9999);


/**
 * 利用原型来实现和java一样的串联继承
 */
var m = {}; //简易创建对象

m.name = "我是name"; // 简易对象添加属性方式1
m["age"] = "我是age"; // 简易对象添加属性方式2

m.showM = function() {
	alert("我是showM！！");
}; // 简易对象添加方法

m.__proto__.say = function () {
	alert("我是say方法");
}; // 简易对象原型添加方法


function n() {
	var name = "name"; // var的作用于是privae的，因此不会覆盖前面的
	this.age = "age"; // js会后定义的属性会覆盖前面的
	// function方式添加方法
	this.showN = function() {
		alert("我是ShowN");
	};
}

n.prototype = m;
n.prototype.constructor = n;

var k = {};
k.__proto__ = new n();
k.__proto__.constructor = k;

k.say();
k.showN();
k.showM();
alert(k["name"] + ";" + k.age);





























