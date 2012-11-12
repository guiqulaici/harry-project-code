/**
 * 给javascript编写一个通用的继承类
 */
(function() {
	function Person(name) {
		this.getName = function() {
			return ("person.getname!!" + name);

		};
	}

	Person.prototype.getAge = function(age) {
		return ("person.prototype.getage" + age);
	};

	function extend1(subClass, superClass) {
		subClass.prototype = new superClass();
	}

	function Teacher(name, book) {
		Person.call(this, name);
		this.getBook = function() {
			return name + " has a " + book + " book!!";
		};
	}

	extend1(Teacher, Person);

	var t = new Teacher("Harry", "Englist");

	document.write(t.getName() + "<br/>");
	document.write(t.getAge(200) + "<br/>");
	document.write(t.getBook() + "<br/>");

	// ==========================================分割线=========================================
	// 上面这段程序，我觉得，并不通用，因为我发现子类里面还出现了父类的名字,修改如下
	document.write("==========================================分割线=========================================<br/>");
	function extend2(subClass, superClass) {
		subClass.prototype = new superClass();
		// 为了继承函数的通用性，我把父类的原型，直接赋值给子类定义的一个属性,那样我们在调用extend2方法之后，
		// 会定义一个subClass.superClass的属性值为父类的原型，这个subClass.superClass属性，其实不需要实例化subClass就可以使用，有点类似java中的static，
		// 但是这种属性的使用只能是subClass.superClass,这样就可以用这个属性来得到父类的原型，调用父类的call方法
		subClass.superClass = superClass.prototype;
	}

	function Autor(name, book) {
		Autor.superClass.constructor.call(this,name);
		this.getBook = function() {
			return name + " has a " + book + " book!!";
		};
	}

	extend2(Autor, Person);
	var a = new Autor("Lujingli","Chinese");
	document.write(a.getName() + "<br/>");
	document.write(a.getAge(500) + "<br/>");
	document.write(a.getBook() + "<br/>");
})();
