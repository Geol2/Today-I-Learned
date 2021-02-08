# Create HTTP Request Message
- URL ( Uniform Resource Locator ) 
- FTP ( File Trasfer Protocol ) : 파일 업로드 및 다운로드할 때 사용하는 프로토콜. FTP를 사용하여 파일 전송을 하는 프로그램.

책에선 다음과 같은 설명을 해주고 있다.
```
http: + // + 웹 서버명 + / + 디렉토리 명 + / + ... + 파일명
```
<br>
현재는 RESTful API 라는 것들이 쓰여지면서 웹 서버명 다음에 디렉토리명이나 파일명으로 작성하는 방법으로 하지 않고 리소스 명이나 자원을 표현하는데 중심을 둔다.

```
http://웹 서버명/members/show/1
```

[참고]
| 메소드 | 기본 의미 | RESTAPI CRUD 의미 |
|:---:|:---:|:---:|
| GET | URL로 지정한 정보를 요청 | 리소스를 조회 |
| POST | 클라이언트에서 서버로 데이터를 송신 <br> 폼에 입력한 데이터를 송신하는 경우 사용 | 리소스를 생성 |
| PUT | URL로 지정한 서버의 파일을 치환 | 리소스를 수정함 |
| DELETE | URL로 지정한 허버의 파일을 삭제 | 리소스를 삭제함 |

<br>
[Request Message content]<br>

- [F12 개발자도구] > [Network] > [파일] > [Header] 를 통해 메세지를 확인할 수 있다.
<br>

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

