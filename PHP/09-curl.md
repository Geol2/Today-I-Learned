# PHP CURL

- CURL은 Dniel Stenberg에 의해 작성되었으며 여러 타입이 다른 프로토콜들의 서버들과의 통신 및 연결을 해주는 라이브러리를 지원해주고 있다.
- HTTP 뿐만 아니라 HTTPS, POST, PUT, FTP 업로드 기능(부가로 추가), 폼 데이터 기반의 업로드, 프록시, 쿠키, 유저 비밀번호 인증 등 사용되고 있다.
- 예제

## POST (Cookie) 방식

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

## POST 방식

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

## GET 방식
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

PHP에서 curl을 통한 여러 클라이언트에서 응답 테스트를 해본다고 했을 때, 같은 브라우저 탭에서는 동기적으로 작동되는 듯하다

내장 서버를 이용하면 동기적으로 작동되서 느리게 되었고 별도 WAS 내에 작동시켜야 한다.

예를 들어서 `https://contents.co.kr/user` 에 5초 후에 유저의 정보들을 응답해준다고 했을 때, 탭을 여러개 띄운 후 해당 주소에 들어가보자. 그럼 5초, 10초, 15초 이런식으로 작동이 되니까 프로세스 자체가 해당 응답을 받을 때까지 기다리는 듯 했다.

다른 기기에서나 시크릿 탭을 열고 하면 엔터치는 시간을 뺀다고 했을 때 5초 가까이 작동이 된다.

WAS와 PHP와의 연관 관계에 대해 더 궁금해지는 계기가 되었다