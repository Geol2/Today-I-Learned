# PHP CURL

- CURL은 Dniel Stenberg에 의해 작성되었으며 여러 타입이 다른 프로토콜들의 서버들과의 통신 및 연결을 해주는 라이브러리를 지원해주고 있다.
- HTTP 뿐만 아니라 HTTPS, POST, PUT, FTP 업로드 기능(부가로 추가), 폼 데이터 기반의 업로드, 프록시, 쿠키, 유저 비밀번호 인증 등 사용되고 있다.
- 예제

```php
// Cookie를 통해 POST 전송
$ch = curl_init();
$filename = "/tmp/smsCookieFile/cookie.txt";

curl_setopt($ch, CURLOPT_COOKIEJAR, $filename);
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data)); //POST data
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
curl_setopt($ch, CURLOPT_COOKIEFILE, $filename);
$response = curl_exec($ch);
curl_close($ch);
```

```php
// POST 전송
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_POST, true);
curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($data)); //POST data
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
$response = curl_exec($ch);
curl_close($ch);
```

```php
// GET 방식 전송
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 10);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
$response = curl_exec($ch);
curl_close($ch);
```
