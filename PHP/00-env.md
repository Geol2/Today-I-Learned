## $_SERVER

### $_SERVER['REQUEST_METHOD']

요청된 방식 GET, POST, PATCH, DELETE 등을 알려주는 변수

### $_SERVER['PATH_INFO']

URI에 대해 해당되지만, .php 다음에 오는 URI만 해당되거나, REST에서만 적용된다

`http://www.example.com/php/path_info.php/some/stuff?foo=bar` 접속 시, `$_SERVER['PATH_INFO']`에는 `/some/stuff`가 포함

### 맥북 설치 경로 확인

brew 설치

- `/usr/local/lib/php/pecl/{날짜}/`

