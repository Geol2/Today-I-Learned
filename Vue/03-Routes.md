# 라우터

효율적인 URI 자원을 위해서 라우팅 기능을 사용할 수 있다

- `<router-link to="URL 값">` : 페이지 이동 태그
- `<router-view>` : URL에 따라 컴포넌트를 뿌려주는 영역

## 네스티드 라우트

라우터로 페이지를 이동할 때, 최소 2개 이상의 컴포넌트를 화면에 나타낼 수 있다

`domain.com/users/posts`, `domain.com/users/profile`

## $mount() API

el 속성과 동일하게 인스턴스를 화면에 붙이는 역할

```javascript
let Main = {template: '<div>main</div>'};
let Login = {template: '<div>login</div>'};

let routes = [
    {path: '/main', component: Main},
    {path: '/login', component: Login}
];

let router = new VueRouter({
    //mode: 'history',
    routes
});

let app = new Vue({
    router
}).$mount('#app');
```

# Axios

- `axios.get('URL 주소').then().catch()` : 해당 URL 주소에 대해 HTTP GET 요청을 보낸다

- `axios.post('URL 주소').then().catch()` : 해당 URL 주소에 대해 HTTP POST 요청을 보낸다

- `axios({옵션 속성})` : HTTP 요청에 대한 자세한 속성들을 직접 정의하여 보낼 수 있다

각 요청들에 대해 정상적인 요청을 받아오면 then() 안에 정의한 로직이 실행되고 오류가 있다면 catch()에 정의한 로직이 수행된다

```html
<!DOCTYPE html>
<body>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vue Axios Sample</title>
    </head>

    <body>
        <div id="app">
            <button v-on:click="getData">프레임워크 목록 가져오기</button>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/vue@2.5.2/dist/vue.js"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        <script>
            new Vue({
                el: '#app',
                methods: {
                    getData: function() {
                        axios.get("https://raw.githubusercontent.com/joshua1988/doit-vuejs/master/data/demo.json")
                        .then(function(response) {
                            console.log(response);
                        });
                    }
                }
            });
        </script>
    </body>
</body>
```