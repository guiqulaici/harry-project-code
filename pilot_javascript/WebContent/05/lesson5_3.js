/**
 * 掺元类，聚合
 */
(function() {
	function Person(name) {
		this.name = name;
		
		this.toPersonString = function() {
			var a = []; //定义一个空数组
			for (var index in this) { // 这里的this代表调用此方法的对象
				a.push(index + "--->" + this[index] + "<br/>");
			}
			
			return a;
		};
	}
	
	function Teacher() {this.a = "a"; var b = "b";};
	Teacher.age = "26";
	Teacher["sex"] = "男";
	
	function handler(sendClass, endClass) {
		for(var index in sendClass) {
			if (!endClass[index]) {
				endClass[index] = sendClass[index];
			}
		}
	}
	
	var t = new Teacher();
	
	handler(new Person("KK"), t);
	document.write(t.toPersonString());
	alert(t.name);
	
	// document.write(Person.toPersonString());报错的，{}内的方法和属性，是不要实例化才能使用的
})();
