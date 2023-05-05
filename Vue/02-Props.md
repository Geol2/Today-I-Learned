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

## 하위에서 상위 컴포넌트로 이벤트 전달하기

이벤트를 발생시켜서 하위에서 상위 컴포넌트로 데이터를 전달한다

```html
this.$emit('이벤트명');
```

```html
<child-component v-on:이벤트명="상위 컴포넌트의 메서드명"></child-component>
```

```html
<div id="app">
    <child-component v-on:show-log="printText"></child-component>
</div>

<script>
    Vue.component('child-component', {
        template: '<button v=on:click="showLog"></button>', // 1. 버튼 추가
        method: {
            showLog: function() {
                this.$emit('show-log'); // 2. 이벤트 발생 로직
            }
        }
    });

    var app = new Vue({
        el: "#app",
        data: {
            message: "Hello Vue! passed from Parent Component"
        },
        methods: {
            printText: function() {
                console.log("received an event"); // 3. 출력 
            }
        }
    })
</script>
```