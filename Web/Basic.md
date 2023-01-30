# Internet

전 세계 컴퓨터들을 하나로 연결하는 거대한 컴퓨터 통신망을 의미

# Web

인터넷에 연결된 사용자들이 서로의 정보를 공유할 수 있는 공간을 의미

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Web/images/http.png" />

# URL

```
https://www.google.com/search?q=was+tomcat&oq=was+tomcat
```

|         URL                 |   구성요소   |
|-----------------------------|-------------|
| https                       | 프로토콜     |
| www.google.com              | 서버 이름    |
| search                      | 경로         |
| q=was+tomcat&oq=was+tomcat  | 쿼리 문자열  |

- 프로토콜 : 웹 브라우저가 서버와 내용을 주고받을 때 사용할 규칙 이름
- 서버 이름 : 아이피 주소나 도메인 주소로 나온다
- 경로 : 웹 페이지의 상세 주소에 해당
- 쿼리 문자열 : 서버에 보내는 추가 데이터가 된다

# 웹 서버와 웹 브라우저

웹서버?

- HTTP 기반으로 동작하며 보통 정적 리소스를 제공

```
https://www.google.com/search?q=was+tomcat&oq=was+tomcat
```

해당 URL을 입력하면 어떤 식으로 작동될까?

1. 웹 브라우저에서 google의 아이피 주소를 요청하면 DNS에서 아이피 주소를 브라우저에게 응답한다.
2. 응답받은 웹 브라우저는 https://www.google.com/search?q=was+tomcat&oq=was+tomcat 웹서버에 요청하고 웹서버는 응답한다.

한 개의 컴퓨터는 여러 포트가 존재해서 여러 프로그램을 실행할 수 있도록 할 수 있다.

# HTTP (HyperText Transfer Protocol)

웹 브라우저와 웹 서버가 HTML 뿐만 아닌 이미지, 동영상, XML 문서 등 다양한 데이터를 주고받을 때 사용하는 일종의 규칙

## 특징

TCP/IP를 이용하는 응용 계층에서 사용되는 프로토콜이다

Http는 연결 상태를 유지하지 않는 비연결성인 특징이 있다 (stateless = 무상태성)

요청/응답 방식으로 동작한다

```
https://www.google.com/search?q=was+tomcat&oq=was+tomcat
```

요청 데이터와 응답 데이터로 구분된다.

## 요청 데이터

- 첫 줄 : HTTP method 요청 방식과 요청하느 자원의 경로를 지정
- 요청 헤더 : 서버가 응답을 생성하는데 참조할 수 있는 정보를 전송
- 요청 바디 : 정보를 전송해야 할 때 사용한다.

## 응답 데이터

- 첫 줄 : 요청에 대한 응답코드를 전송
- 응답 헤더 : 응답에 대한 정보를 전송한다.
- 응답 바디 : 웹 브라우저가 요청한 자원의 내용을 담는다.

# 정적 자원과 동적 자원

정적 자원은 동일한 결과가 나타난다고 해서 정적 자원 및 정적 페이지라고 불린다.

동적 자원은 시간이나 특정 조건에 따라 응답 데이터가 달라지는 것을 동적 자원 및 동적 페이지라고 한다.

# HTTPS (HyperText Transfer Protocol Over Secure Socket Layer)

HTTP에서 통신의 인증과 암호화를 위해 만들어졌다

SSL, TLS 프로토콜을 통해 세션 데이터를 암호화하고 기본 포트는 443

-----

DNS (Domain Name Server) : 도메인 이름을 IP주소를 변환할 때 사용되는 것

클라이언트 : 요청을 하는 쪽

서버 : 요청을 받아 알맞은 기능이나 데이터를 제공하는 것

포트 : 각 서버 프로그램은 클라이언트가 연결할 때, 구분할 수 있도록 사용되는 것