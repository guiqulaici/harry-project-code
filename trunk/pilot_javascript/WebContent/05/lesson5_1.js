/**
 * javascript的传参继承（父类是有参数的），我们在将子类的原型赋予父类的new对象时，是没有传入参数的，因此我们需要利用call方法回调父类，并给父类传递参数
 */
(function() {
	function Person(name) {
		this.name = name;

		this.getName = function(name, value) {
			return name + value;
		};
	}

	// 给person对象天getKey的方法和下面key属性，这样的定义，我们不需要new
	// Person（）就可以直接用Person.getKey()和Person.key来直接使用
	/*
	 * 我们在定义类（类其实也是对象）和对象的时候我们可以用object[xxx]和object.xx的方式来添加属性和方法，
	 * 前提是必须有这个object对象，且这种（仅限这种）添加的属性和方法，是对整个js有效的，它并不需要实例化new Object就可以使用
	 * 并且它的使用方式，只能是Object的对象名+"."+xxx或者只能是Object的对象名[xxx]
	 */
	Person.getKey = function() {
		return "我是Person的getKey方法";
	};

	Person.key = "key";

	Person.prototype.getPerson = function() {
		return "我是Person的原型方法";
	};

	function Teacher(name, book) {
		Person.call(this, name); // 这里的this代表当前Person对象
		this.getBook = function() {
			return (name + "'s book is " + book);
		};
	}

	// 继承:
	Teacher.prototype = new Person(); // new一个Person的对象，作为Teacher的原型，但是，Person对象是需要传递参数name的，此时却没有，因此我们才在Teacher对象中添加Person.call的回调，来初始化Person中的name

	Teacher.prototype.getMsg = function() {
		return ("我是teacher的原型方法");
	}; // 子类的原型方法一定要写在原型继承之后，否则会被覆盖，因为js是后者覆盖前者的

	var tdomain = new Teacher("Harry", "English");

	document.write(tdomain.name + "<br/>");
	document.write(tdomain.getName("aaa", "bb") + "<br/>");
	// document.write(tdomain.getKey() + "<br/>"); //
	// 无效的getKey和key属性，是不可以被继承的，因为他们直接就是Person.getKey()和Person.key来使用的，不需要实例化Person
	// document.write(tdomain.key + "<br/>");
	document.write(tdomain.getPerson() + "<br/>");
	document.write(tdomain.getBook() + "<br/>");
	document.write(tdomain.getMsg() + "<br/>");
})();
