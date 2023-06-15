# template

## 데이터 바인딩

### {} - 콧수염 괄호

- 주로 사용되는 템플릿 문법
- 자바스크립트 표현식을 일부 사용할 수 있다
    1. 선언문과 분기 구문은 사용할 수 없지만, 삼항 연산자 사용은 가능하다
    2. 간단한 연산 결과만 표시하는 것이 좋다

## 디렉티브

v- 접두사를 가지는 모든 속성들을 의미

### v-if

- 지정한 뷰 데이터의 참, 거짓 여부에 따라 해당 HTML 태그를 표시하거나 하지 않는다

### v-for

- 지정한 뷰 데이터의 개수만큼 HTML 태그를 반복 출력

### v-show

- `v-if`와 유사하게 데이터의 진위에 따라 HTML 태그를 화면에 표시하거나 표시하지 않는다
- `v-if`는 해당 태그를 완전히 삭제하지만, `v-show`는 `display:none` 효과를 주고 남겨져있다

### v-on

- 화면 요소의 이벤트를 감지하여 처리할 때 사용한다

### v-model

- 폼에서 주로 사용되는 속성이다. 폼에 입력한 값을 뷰 인스턴스이 데이터와 즉시 동기화한다. 화면에 입력된 값을 저장하여 서버에 보내거나 watch와 같은 고급 속성을 이용하여 추가 로직을 만들 수 있다. `<input>`, `<select>`, `<textarea>` 태그에만 사용할 수 있다.

### v-bind

- HTML의 속성값에 뷰 데이터 값을 연결할 때 사용하는 데이터 연결 방식
- v-bind를 여러개 사용할 수 있다 : `<p v-bind:id="idA" v-bind:class="classA">아이디 바인딩</p>`
```html
...
<div id="app">
    <p v-bind:id="idA">아이디 바인딩</p>
    <p v-bind:class="classA">클래스 바인딩</p>
    <p v-bind:style="styleA">스파일 바인딩</p>
</div>

<script>
    new Vue({
        el: '#app',
        data: {
            idA: 10,
            classA: 'container',
            styleA: 'color: blue'
        }
    })
</script>
```

## 고급 연산

### computed

데이터 연산들을 정의하는 영역

```html

<div id="app">
    <p> {{ reversedMessage }} </p>
</div>

<script>
    new Vue({
        el: "#app",
        data: "Hello World!",
        computed: {
            reversedMessage: function() {
                return this.message.split("").reverse().join("");
            }
        }
    })
</script>
```

### computed VS methods

