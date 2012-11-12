(function() {
	function Person(name) {
		this.name = name;
		var d = "d";
		
		this.toPersonString = function() {
			var a = []; //定义一个空数组
			for (var index in this) { // 这里的this代表调用此方法的对象,但是这个对象里面却没有var定义的私有属性和方法
				a.push(index + "--->" + this[index] + "<br/>");
				// a.push(d);
			}
			
			return a;
		};
	}
	
	document.write(new Person("abc").toPersonString());
})();
