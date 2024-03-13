# FormData

`FormData`는 서버에 폼 전송을 위한 특수한 객체이다.

일반적으로 `let form = new FormData()`을 작성한 뒤, `console.log(form)` 로는 확인이 불가능하다.

아래 코드처럼 `entries` 키워드를 통해서 접근이 가능하다.

```javascript
formData.append("key1", "value1");
formData.append("key2", "value2");

// Display the key/value pairs
for (const pair of formData.entries()) {
  console.log(pair[0], pair[1]);
}

// key1 value1
// key2 value2
```

폼데이터를 전송할 때, 다음과 같이할 수 있다.

```javascript
// <form id="test" name="test"> 가 있다고 가정하자.

// 1.
let test = document.getElementById("test");
let form = new FormData(test);

// 2.
let test = document.forms.test; 
// 사실 test만 던져도 무관하다. 첨부파일도 던져지는지는 확인을 안해봄.
let form = new FormData(test);

axios({
  url: '/test',
  data: form
})
.then(response => {
  console.log(response);
})
```