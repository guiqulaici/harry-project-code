(function(){
/**
 * 
 */
var num = 3.145672;

document.write(num.toFixed(4) + "<br/>");
document.write(num.toPrecision(4) + "<br/>");	
document.write(Math.floor(Math.random() * 11) + "<br/>");
document.write(Math.floor(2.122356) + "<br/>");
document.write("=====================分割线===================<br/>");

var strArray = ["1","2","3","4"];

for (var index = 0; index < strArray.length; index++) {
	document.write(strArray[index] + "<br/>");
}

for (var index in strArray) {
	document.write(strArray[index] + "<br/>");
}

var intArray = new Array(11,12,13,14);
for (var index in intArray) {
	document.write(intArray[index] + "<br/>");
}

intArray.push(15);
for (var index in intArray) {
	document.write(intArray[index] + "<br/>");
}


})();

