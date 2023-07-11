# nginx

## PHP에서 유용한 것들

### 특정 폴더에서 .php 없이 넘기기 (라우팅과는 개념이 다른 것 같음)

```nginx
# ...
location /share/ {
    rewrite ^/share/(.*)$ /share/index.php?key=$1 last;
}
# ...
```

### 특정 라우팅에서 public 내의 .php 파일 위치로 넘기기

라라벨
```nginx
# ...
location / {
    try_files $uri $uri/ /index.php?$query_string;
}
# ...
```