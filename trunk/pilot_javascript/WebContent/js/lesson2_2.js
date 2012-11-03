/**
 * js中的一些特殊的函数
 */

// 异常:如果不写alert(i)是不会把异常alert出来的，因此我认为，当报异常，其实是将异常传递给e的
try {
	var i = 2 / 0;
	// alert(i);
} catch (e) {
	throw Error(e);
}

// 定时 setTimeout("需要执行的代码", 定时的时间毫秒为单位);
var i = 0;
var t;

function timedCount() {
	i++;
	// 将i的值赋值给count;
	document.getElementById("count").value = i;
	t = setTimeout("timedCount()", 1000);
}


function stop(){
	// clearTimeout是js内置的停止定时的方法
	clearTimeout(t);
}