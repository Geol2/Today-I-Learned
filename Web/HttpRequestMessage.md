# Create HTTP Request Message
- URL ( Uniform Resource Locator ) 
- FTP ( File Trasfer Protocol ) : 파일 업로드 및 다운로드할 때 사용하는 프로토콜. FTP를 사용하여 파일 전송을 하는 프로그램.

```
http: + // + 웹 서버명 + / + 디렉토리 명 + / + ... + 파일명
```

현재는 RESTful API 라는 것들이 쓰여지면서 웹 서버명 다음에 디렉토리명이나 파일명으로 작성하는 방법으로 하지 않고 리소스 명이나 자원을 표현하는데 중심을 둔다.

```
http://웹 서버명/members/show/1
```

[참고]
| 메소드 | 기본 의미 | RESTAPI CRUD 의미 |
|:---:|:---:|:---:|
| GET | URL로 지정한 정보를 요청 | 리소스를 조회 |
| POST | 클라이언트에서 서버로 데이터를 송신 폼에 입력한 데이터를 송신하는 경우 사용 | 리소스를 생성 |
| PUT | URL로 지정한 서버의 파일을 치환 | 리소스를 전체 수정함  |
| PATCH |  | 리소스를 일부 수정함  |
| DELETE | URL로 지정한 서버의 파일을 삭제 | 리소스를 삭제함 |

## REST
- 해당 규약을 통해서 표준화된 동작방식과 형식을 가질 수 있고 안정적이며 효율적인 분산 시스템을 구성할 수 있다

### PUT
- 모든 리소스를 업데이트한다
- 이름, 성별, 나이를 가지는 리소스가 존재한다고 했을 때, PUT 요청으로 이름만 업데이트한다면, 성별과 나이는 null값으로 변경된다
```
PUT /post/1
{
    name : test,
    gender : male,
    age : 20,
}
```

### PATCH
- 일부 리소스를 업데이트한다
- 이름, 성별, 나이를 가지는 리소스가 존재한다고 했을 때, PATCH 요청으로 이름만 업데이트 한다면 성별과 나이는 그대로 있고 이름만 업데이트 된다
- 기존 리소스를 가지고 변하는 부분만 업데이트를 하기 때문에 멱등성이 존재한다
```
PATCH /post/1
{
    age : 20
}
```

### POST
- 서버로 데이터를 전송할 때 보내는 메세지이다
- 항상 서버에 새로운 요청을 하기 때문에 멱등성이 존재하지 않는다
- 리소스 전달 방식은 HTTP body를 사용한다
```
POST /post
```

### GET
- 서버에서 데이터를 가져올 때 보내는 메세지이다
- 멱등성을 가질 수 있도록 요청하는 메시지를 보낸다
- 리소스 전달 방식은 쿼리스트링으로 이용한다
- 불필요한 요청을 제한하기 위해 캐시될 수 있고 히스토리가 남는다
```
GET /post/1
1. id 값이 1인 게시글을 가져온다
2. 게시글의 데이터를 반환한다
```

규약일 뿐이지만, 꼭 지켜야 하는 것은 아니지만 해당 규약을 통해서 어떻게 동작하는지 명확해지는 장점이 있다

[Request Message content]

- [F12 개발자도구] > [Network] > [파일] > [Header] 를 통해 메세지를 확인할 수 있다.


[일부 응답 메시지 내용]
```
- authority: www.youtube.com
- method: POST
- path: /youtubei/v1/log_event?alt=json&
- scheme: https
- accept: */*
- accept-encoding: gzip, deflate, br
- accept-language: ko-KR,ko; ...
- authorization: ..?
- content-length: 698
- content-type: application/json
```
직접 자신의 컴퓨터에서 확인이 가능하다.

1. 브라우저가 서버로 리소스를 조회하기 위해 리퀘스트 메시지를 전송.
2. 서버가 리소스의 내용을 브라우저에게 응답 메시지를 전송.
3. 그림 파일을 읽기 위해 브라우저가 서버에게 리퀘스트 메시지를 전송.
4. 서버가 리소스의 내용을 브라우저에게 응답 메시지 전송.
5. ...

----
- 멱등성 : 연산을 여러 번 적용해도 결과는 항상 달라지지 않음을 의미하고 멱등성을 가진다고 한다
