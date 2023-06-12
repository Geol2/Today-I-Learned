# 라우터

효율적인 URI 자원을 위해서 라우팅 기능을 사용할 수 있다

- `<router-link to="URL 값">` : 페이지 이동 태그
- `<router-view>` : URL에 따라 컴포넌트를 뿌려주는 영역

## 네스티드 라우트

라우터로 페이지를 이동할 때, 최소 2개 이상의 컴포넌트를 화면에 나타낼 수 있다

`domain.com/users/posts`, `domain.com/users/profile`

## $mount() API

el 속성과 동일하게 인스턴스를 화면에 붙이는 역할

