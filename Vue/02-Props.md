# props

## 상위에서 하위 컴포넌트로 데이터 전달하기

```html
 <!-- 상위 컴포넌트 -->
<div id="app">
    <child-component v-bind:propsdata="message"> <!-- 4. 하위 컴포넌트  -->
</div>
<script>
Vue.component('child-component', { // 2.
    props: ['propsdata'], // 3.하위 컴포넌트의 props 속성 이름
    templete: '<p>{{ propsdata }}</p>' // 5. 하위 컴포넌트의 템플릿
});

new Vue({
    el: "#app",
    data: {
        message: "Hello Vue! passed from Parent Component" // 1. 상위 컴포넌트의 데이터 속성
    }
});
</script>
```