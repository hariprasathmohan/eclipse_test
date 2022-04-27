/**
 * 
 */
function load(id,path) {
	console.log('div id : ${id} , filename : ${path}', id,path);
	var element = document.getElementById(id);
	var ajax = new XMLHttpRequest();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(ajax.responseText);
			element.innerHTML = ajax.responseText;
			element.style.display='inline';
		}
	}
	ajax.open('GET', path,true);
	ajax.send();
}
function loadTransactions(id,accountNumber,action) {
	var path="AccountStatement.jsp?accountNumber="+accountNumber+"&type="+action;
	console.log('div id : ${id} , filename : ${path}', id,path,accountNumber);
	var element = document.getElementById(id);
	window.location=path;
	/*var ajax = new XMLHttpRequest();
	ajax.open('GET', path);
	ajax.send();
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			console.log(ajax.responseText);
			element.innerHTML = ajax.responseText;
			element.style.display='inline';
		}
	}*/
}
function showBalance(accountNumber) {
	var url = 'GetBalance.jsp?accountNumber=' + accountNumber;
	var ajax = new XMLHttpRequest();
	ajax.open('GET', url);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			var bal = document.getElementById("balance");
			console.log(ajax.responseText);
			bal.innerHTML = ajax.responseText;
			bal.style.display='inline';
		}
	}
	ajax.send();
}
