# Variable - var, let, const

- var : 호이스팅 및 함수 스코프 단위
- const, let : 블록 스코프

## 호이스팅

- `var`는 호이스팅을 일으키며 해당 함수나 속한 범위에서 최상단으로 올려버리는 현상이 있다.
  - `var` 대신 `let`을 사용하도록 한다.
- `const`, `let`은 블록 스코프 단위이라 선언 전에 출력하게 된다면 에러를 내뱉게 된다.

```javascript
// var의 블록 스코프 및 호이스팅 예제 코드
if (true) {
	console.log(var_testing);
	var var_testing;
}

if (typeof var_testing == "undefined") {
	console.log("typeof testing undefined");
}

if (var_testing == undefined) {
	console.log("Set testing undefined!");
}

// undefined
// typeof testing undefined
// Set testing undefined!
```

```javascript
let f_let_testing = 0;

function testing() {
	console.log(f_let_testing);
	let f_let_testing = 1;
	console.log(f_let_testing);
	let f_let_testing = 2;
	console.log(f_let_testing);
}

testing();

// error!
```

```javascript
// 함수 스코프 및 호이스팅 예제 코드
function testing(number) {
	if (number > 0) {
		var strValid = "숫자는 다음과 같습니다.";
		console.log(strValid);
	} else {
		var notStrValid = "유효하지 않습니다.";
		console.log(notStrValid);
		console.log(number);
	}
	console.log(notStrValid);
}

testing(1);

// 숫자는 다음과 같습니다.
// undefined
```

```javascript
// 블록 스코프 및 호이스팅 예제 코드
if (true) {
	let let_testing;
}

if (typeof let_testing == "undefined") {
	console.log("Set let_testing undefined");
}

if (let_testing == undefined) {
	console.log("set let_testing undefined");
}

// Set let_testing undefined
// error!
```

- 호이스팅 관련된 문제가 모노리틱 아키텍처에서 이상한 값으로 넘어옴을 발견함.
  - 모노리틱 아키텍처(MONO) : 서버 안에서 각 컴포넌트가 모두 하나로 포장된 구조. 작은 프로젝트에서 유리함과 편리함.
  - 마이크로 서비스 아키텍처(MSA) : 각 서비스가 독립된 도메인 구조.

```php
<form method='post' name='actionForm' action='./test.php'>
    // blah blah
    <buttion type="submit" id="submit"> 전송 </submit>
</form>

<script type="text/javascript">
    // blah blah

    function fSubmit() {
        // blah blah
        var form = document.actionForm;
        form.submit();
    }
</script>
```

```php
    $post['blah'];
    echo $post;
    // undefined
    // server : uh..?
```

# HTMLElement

<img src="/assets/images/HTMLElement.png">

- 모든 종류의 HTML 요소, 부모인 `Element`를 상속함

## Element

- `Document` 안의 객체를 상속하는 범용적인 클래스, 부모인 `EventTarget`와 `Node`를 상속함.

## Node

- 여러 가지 DOM 타입들이 상속하는 인터페이스, 부모인 `EventTarget`을 상속함.

## EventTarget

- DOM에 발생된 것들을 알리기 위해 전달되는 것
- `Document`, `Element`, `window`가 흔한 대상이고 `XMLHttpRequest`, `AudioNode`, `AudioContext` 등도 이벤트 대상

### 생성자

- `EventTarget()`
- 새로운 EventTarget 객체 인스턴스를 생성합니다.

### 메서드

- `EventTarget.addEventListener()`
- EventTarget에 특정 이벤트 유형의 처리기를 등록합니다.
- [addEventListner Doc](https://developer.mozilla.org/ko/docs/Web/API/EventTarget/addEventListener)

```javascript
const title = document.getElementById("title");

function handlerMouseEnter() {
	title.style.color = "blue";
}

function handlerMouseLeave() {
	title.style.color = "blue";
}

function handlerTitleClick() {
	title.style.color = "blue";
}

function onLoginSubmit() {
  console.log(1);
}

title.addEventListener("click", handlerTitleClick);
title.addEventListener("mouseenter", handlerMouseEnter);
title.addEventListener("mouseleave", handlerMouseLeave);
title.addEventListener("submit", onLoginSubmit);

// 동일 표현
title.onclick = handlerTitleClick;
title.onmouseenter = handlerMouseEnter;
title.mouseleave = handlerMouseLeave;
```

# Interval

- `setinterval(func, millisec)`
- 밀리세컨드마다 기능이 실행되는 함수.

## Syntax

```javascript
setInterval(func, [milliseconds, arg1, arg2, ...);
setInterval(code, milliseconds);
setInterval(function[, milliseconds]);
```

- 최대 타이머는 2,147,483,647ms(약 24.8일)보다 큰 값을 저장하면 오버플로우가 발생.
- `arg1`, `arg2`, `arg3`는 IE9 이하는 지원하지 않는다.

```javascript
(function loop() {
	setTimeout(function () {
		// Your logic here
		loop();
	}, delay);
})();
```

## return

```javascript
let id = setinterval(code, 3000);
```

- `id`는 타이머의 아이디를 반환한다.
- `clearinterval(변수)`를 사용하여 종료할 수 있다.

---

# Timeout

- `setTimeout(func, millisec)`
- 밀리세컨드에 함수나 지정한 코드를 실행하는 타이머 역할을 하는 함수

## Syntax

```javascript
setTimeout(function[, delay, arg1, arg2, ...]);
setTimeout(function[, delay]);
setTimeout(code[, delay]);
```

- 최대 딜레이는 32비트 부호 정수 값으로 저장. 2,147,483,647ms(약 24.8일)보다 큰 값을 저장하면 오버플로우가 발생해서 즉시 타임아웃이 만료된다.

```javascript
setTimeout(() => {
	console.log("첫 번째 메시지");
}, 5000);
setTimeout(() => {
	console.log("두 번째 메시지");
}, 3000);
setTimeout(() => {
	console.log("세 번째 메시지");
}, 1000);

// 세 번째 메시지
// 두 번째 메시지
// 첫 번째 메시지
```

### WorseCase

- 다음과 같이 사용하지 말라고 권고하고 있다

```javascript
setTimeout("console.log('Hello World!');", 500);

setTimeout(function () {
	console.log("Hello World!");
}, 500);
```

### BestCase

- 이렇게 사용하자

```javascript
function hello() {
	console.log("Hello World!");
}

setTimeout(hello, 500);
```

## return

```javascript
let id = setTimeout(code, 1000);
```

- `id`는 타임아웃의 아이디를 반환한다.
- `clearTimeout(변수)`를 사용하여 종료할 수 있다.

----- 

# localStorage

- `localStorage`는 `sessionStorage`와 비슷하지만, 모든 탭과 창에서 공유되고 브라우저나 OS가 재시작하더라도 데이터가 파기되지 않습니다.

- `HTTP`와 `HTTPS`와는 다른 `localStorage`에 저장이 되는 차이가 있습니다.

## 문법
```javascript
myStorage = window.localStorage;
```

## 예제

```javascript
localStorage.setItem('myCat', 'Tom');

const cat = localStorage.getItem('myCat');

localStorage.removeItem('myCat');

localStorage.clear();
```

--------

# sessionStorage
- 현재 떠 있는 탭 내에서만 유지됩니다.
- 하나의 탭에서 여러 `iframe`이 있는 경우엔 공유됩니다.
- 페이지를 새로 고침할 때 `sessionStorage`에 저장된 데이터는 사라지지 않는데 탭을 닫고 새로 열 때는 사라진다.

## 문법
```javascript
myStorage = window.sessionStorage;
```

## 예제

```javascript
sessionStorage.setItem('test', 1);

sessionStorage.getItem('test');
```

|       localStorage        |                   sessionStorage                 |
|:-------------------------:|:------------------------------------------------:|
| 같은 탭, 창에서 공유        |         같은 브라우저 탭, iframe 공유             |
| 브라우저를 껐다 켜도 남음   | 새로고침해도 남음, 탭이나 브라우저를 종료하면 사라짐 |

- API
  - `setItem(key, value)` : 키, 값 쌍을 보관
  - `getItem(key)`        : 키에 해당하는 값을 받아옴
  - `removeItem(key)`     : 키와 해당 값을 삭제함
  - `clear()`             : 모든 것을 삭제함
  - `key(index)`          : `인덱스` 에 해당하는 키를 받아옴
  - `length`              : 저장된 항목의 개수를 얻음
  - `Object.keys`         : 사용해 키 전체를 얻을 수 있음

-----

# Fetch API

- `Fetch API`가 등장하기 이전 `XMLHttpRequest`에서 제공하고 있었다.
- `fetch()`로부터 반환되는 Promise 객체는 HTTP error 상태를 HTTP Status Code가 404 500을 반환하더라도 `reject`하지 않는다. 하지만, ok 상태가 false인 `resolve`가 반환된다. 네트워크 장애나 요청이나 완료되지 못한 상태에는 `reject`가 반환된다.
- 보통 `fetch`는 쿠키를 보내거나 받지 않습니다. 사용자 세션을 유지 관리해야할 경우 인증되지 않는 요청이 발생한다. 쿠키를 전송하기 위해선 자격증명 옵션을 반드시 설정한다.

```javascript
fetch('http://example.com/movies.json')
.then(function(response) {
    return response.json();
})
.then(function(myJson) {
    console.log(JSON.stringify(myJson));
});
```

```javascript
// Example POST method implementation:

postData('http://example.com/answer', {answer: 42})
  .then(data => console.log(JSON.stringify(data))) // JSON-string from `response.json()` call
  .catch(error => console.error(error));

function postData(url = '', data = {}) {
    // Default options are marked with *
    return fetch(url, {
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, cors, *same-origin
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, *same-origin, omit
        headers: {
            'Content-Type': 'application/json',
            // 'Content-Type': 'application/x-www-form-urlencoded',
        },
        redirect: 'follow', // manual, *follow, error
        referrer: 'no-referrer', // no-referrer, *client
        body: JSON.stringify(data), // body data type must match "Content-Type" header
    })
    .then(response => response.json()); // parses JSON response into native JavaScript objects
}
```

- 자격증명이 포함된 리퀘스트 요청

```javascript
// cross-origin
fetch('https://example.com', {
  credentials: 'include'
})

// same-origin
fetch('https://example.com', {
  credentials: 'same-origin'
})
```

## Response 객체

- `Response.status`는 HTTP Status의 정수치, 기본값 `200`
- `Response.statusText`는 HTTP Status 코드의 메서드와 일치하는 문자열, 기본값은 `OK`
- `Response.ok`는 HTTP Status 코드가 200에서 299 중 하나임을 체크하는 값, `Boolean`을 반환 

-----------------

# Promise()

- `Promise` 객체는 비동기 작업에 대한 완료 또는 실패, 그 결과 값을 나타낸다.

## `Promise` 3가지의 상태

- *대기(pending)* : 이행하지도, 거부하지도 않은 초기 상태.
- *이행(fulfilled)* : 연산이 성공적으로 연료됨.
- *거부(reject)* : 연산이 실패함.

<img src="/assets/images/JavaScript/Promise(1).png">

```javascript
// ... bolabola
let log = document.getElementById('log');
//... bolabola

// 새 프로미스 생성 - 프로미스의 생성 순서를 전달하겠다는 약속을 함 (3초 기다린 후)
    var p1 = new Promise(
        function(resolve, reject) {
            log.insertAdjacentHTML('beforeend', thisPromiseCount +
                ') 프로미스 시작 (<small>비동기적 코드 시작</small>)<br/>');
            window.setTimeout(
                function() {
                    // 프로미스 이행 !
                    resolve(thisPromiseCount);
                }, Math.random() * 2000 + 1000);
        }
    );

    // 프로미스를 이행했을 때 할 일은 then() 호출로 정의하고,
    // 거부됐을 때 할 일은 catch() 호출로 정의
    p1.then(
        // 이행 값 기록
        function(val) {
            log.insertAdjacentHTML('beforeend', val +
                ') 프로미스 이행 (<small>비동기적 코드 종료</small>)<br/>');
        })
    .catch(
        // 거부 이유 기록
        function(reason) {
            console.log('여기서 거부된 프로미스(' + reason + ')를 처리하세요.');
        });

    log.insertAdjacentHTML('beforeend', thisPromiseCount +
        ') 프로미스 생성 (<small>동기적 코드 종료</small>)<br/>');
```

## 체이닝

- 두 개 이상의 비동기 작업을 순차적으로 실행해야하는 상황이 생긴다.
- 이전 단계의 비동기 작업이 성공하고 나서 그 결과값을 이용하여 다음 비동기 작업을 실행하는 대표적인 예를 들 수 있다. 이런 경우, `promise chain`을 이용하여 해결한다.

```javascript
const promise = doSomething();
const promise2 = promise.then(successCallback, failureCallback);
```

```javascript
const promise2 = doSomething().then(successCallback, failureCallback);
```

- 기존 비동기 작업에 대한 콜백지옥

```javascript
doSomething(function(result) {
  doSomethingElse(result, function(newResult) {
    doThirdThing(newResult, function(finalResult) {
      console.log('Got the final result: ' + finalResult);
    }, failureCallback);
  }, failureCallback);
}, failureCallback);
```

- 모던 방식으로 `promise`에 체이닝을 적용한다면

```javascript
doSomething().then(function(result) {
  return doSomethingElse(result);
})
.then(function(newResult) {
  return doThirdThing(newResult);
})
.then(function(finalResult) {
  console.log('Got the final result: ' + finalResult);
})
.catch(failureCallback);
``` 

- `catch(failureCallback)`은 `then(null, failureCallback)`의 축약

```javascript
doSomething()
.then(result => doSomethingElse(result))
.then(newResult => doThirdThing(newResult))
.then(finalResult => {
  console.log(`Got the final result: ${finalResult}`);
})
.catch(failureCallback);
```

### catch 후, 체이닝

```javascript
new Promise((resolve, reject) => {
    console.log('Initial');

    resolve();
})
.then(() => {
    throw new Error('Something failed');

    console.log('Do this');
})
.catch(() => {
    console.log('Do that');
})
.then(() => {
    console.log('Do this, whatever happened before');
});

// Initial
// Do that
// Do this, whatever happened before
```

### ECMAScript2017 에서 `async/await` 구문을 사용한 예제

```javascript
async function foo() {
  try {
    const result = await doSomething();
    const newResult = await doSomethingElse(result);
    const finalResult = await doThirdThing(newResult);
    console.log(`Got the final result: ${finalResult}`);
  } catch(error) {
    failureCallback(error);
  }
}
```


### form 데이터를 전송
```javascript
function foo() {
    let data = document.getElementById("form");
    let form = new FormData(data);
    let res = fetch('/url/post', {
        method: 'post',
        body: form,
    });
    res.then(
        res => {
            if(res.ok == true) {
                console.log("ok!");
            }
        }
    )
}
```

----

# async

제이쿼리에서 사용되는 `async`가 있는데,
기본값은 `async : true`, 비동기로 처리된다는 의미<br>
비동기의 의미는 요청 결과는 동시에 일어나지 않는다는 의미

동기로 작동하면 작업처리 단위를 동시에 처리하겠다는 의미를 지닌다.

비동기와 동기를 아래처럼 그림을 나타낼 수 있다.

<img src="/assets/images/JavaScript/sync(1).png" width="450"/>



```html
<div id="loading" style="display:none;">
    <img src="/images/spinner.gif">
</div>
```

```javascript

function apiAsyncFalse() {
    ShowLoading();
    let data;

    // (1)
    $.ajax({
        url : '/post/list',
        data : data,
        async: false,
        type: 'post',
        success: function(data, textStatus, jqXHR) {
            // ... (2)
            data = data;
            HideLoading();
            document.form.submit();

        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("error!");
        }
    });
    // (3)
    console.log(data);
}

```

보통에서 비동기처리인 상태일 때, (1)->(3)->(2) 순서로 비순차적으로 작동된다.<br>
하지만, 동기처리인 상태로 진행된다면 (1)->(2)->(3) 순서로 한 작업단위처럼(순차적) 실행이 된다.

마지막 줄의 console.log 안에 `data` 가 `async` 에 따라 다르게 작동되는 것을 볼 수 있다.

`async : true` 라면 `data` 는 나타나지 않게 되며, false 라고 한다면 `data` 는 서버에서 받은 데이터가 콘솔에 나타나게 된다.<br>
비동기 상태일 때, `data` 에 데이터가 있는지 보장할 수 없으며 구현 시, 유의해야할 가능성이 있다.

