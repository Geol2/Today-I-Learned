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

### v-for

### v-show

### v-on

### v-model

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

