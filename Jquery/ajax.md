# Ajax

- 1번.

```javascript
$.ajax({
	url: "/Auth/loginProc",
	// headers: {'X-Requested-with' : 'XMLHttpRequest'},
	data: loginData,
	type: "post",
	dataType: "json",
	success: function (res) {
		if (res.code === 200) {
			console.log(res);
			alert("성공");
			location.href = "/";
		} else {
			console.log(res);
			alert("실패");
			location.href = "/";
		}
	},
	error: function (res) {
		console.log(res);
		alert("실패");
	},
});
```

2번.

```javascript
$.ajax({
	url: "/Auth/loginProc",
	// headers: {'X-Requested-with' : 'XMLHttpRequest'},
	data: loginData,
	method: "post",
	type: "json",
	success: function (res) {
		if (res.code === 200) {
			console.log(res);
			alert("성공");
		} else {
			console.log(res);
			alert("실패");
		}
	},
	error: function (res) {
		console.log(res);
		alert("실패");
	},
});
```

- 1번과 2번의 차이를 조사해볼 필요가 있다.
