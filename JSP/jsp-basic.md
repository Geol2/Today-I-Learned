# JSP에서 HTML 문서를 생성하는 기본 코드 구조

```jsp
<%@ page contentType = "text/htmll charset=utf-8" %>
<html>
    <head>
        <title> 제목 </title>
    </head>
    <body>
        <% 
            String bookTitle = "JSP 프로그래밍";
            String author = "최범균";
        %>
        <b> <%= bookTitle %> </b> (<%= author %>)
    </body>
</html>
```

JSP 페이지에 대한 설정 정보와 HTML 코드와 JSP 스크립트가 있다.

# JSP 페이지의 구성 요소

1. 디렉티브 : JSP 페이지에 대한 설정 정보를 지정할 때 사용함

```jsp
<%@ 디렉티브이름 속성1="값1" 속성2="값2" ... %>
```

| 디렉티브 |                    설명                      |
|---------|----------------------------------------------|
| page    | JSP 페이지에 대한 정보를 저장한다.              |
| taglib  | JSP 펭지에서 사용할 태그 라이브러리를 지정한다.  |
| include | JSP 페이지의 특정 영역에 다른 문서를 포함시킨다. |

### page

- contentType :  문서의 MIME 타입과 캐릭터 인코딩을 지정
- import : 사용할 자바 클래스를 지정
- session : 세션여부를 지정한다, 기본값은 true
- buffer : 출력 버퍼 크기를 지정한다
- autoFlush : 출력 버퍼가 다찼을 때, 자동으로 버퍼의 데이터를 출력 스트림을 보내고 비울지의 여부이다, 기본이며 최소는 8kb이다
- info : 페이지에 대한 설명을 입력
- errorPage : 실행도중 에러가 발생할 때 보여줄 페이지를 지정
- isErrorPage : 현재 페이지가 에러가 발생될 때 보여주는 페이지인지의 여부를 지정, true가 기본값이며 에러페이지가 된다
- pageEncoding : 캐릭터 인코딩을 지정한다
- isELIgored : false가 기본이며 표현 언어를 해석하지 않고 문자열로 처리한다
- defferredSyntaxAllowedAsLiteral: false가 기본이며 #{ 문자가 문자열 값으로 사용되는 것을 허용할지 여부
- trimDirectiveWhitespaces : 출력 결과에서 템플릿 텍스트의 공백 문자를 제거할지의 여부를 지정

2. 스크립트 요소

JSP 문서의 내용을 동적으로 생성하기 위해 사용되는 것

- 표현식 : 값 출력, `<%= ... %>` 으로 나타낸다.
- 시크립트릿 : 자바 코드를 실행, `<% ... %>` 으로 나타낸다.
- 선언부 : 자바 메소드를 만듬, `<%! ... %>` 으로 나타낸다.

3. 기본 객체

request, response, session, application, page 등 다수 기본 객체가 존재

### request

- `getRemoteAddr()` : 웹 서버에 연결한 클라이언트의 IP 주소를 구한다.
- `getContentLength()` : 클라이언트가 전송한 요청 정보의 길이를 구한다, 길이를 모르면 -1이다.
- `getCharacterEncoding()` : 클라이언트가 요청 정보를 전송할 때 사용한 캐릭터의 인코딩을 구한다.
- `getContentType()` : 클라이언트가 요청 정보를 전송할 때 사용한 컨텐트 타입을 구한다.
- `getProtocol()` : 클라이언트가 요청한 프로토콜을 구함
- `getMethod()` : 웹 브라우저가 정보를 전송할 때, 사용한 방식을 구함
- `getRequestURI()` : 웹 브라우저가 요청한 URL에서 경로를 구함
- `getContextPath()` : 웹 애플리케이션의 컨텍스트 경로
- `getServerName()` : 연결할 때 사용한 서버 이름을 구함
- `getServerPort()` : 서버가 실행중인 포트를 구함

### response

- `addDateHeader(String name, long date)` : name 헤더에 date를 추가
- `addHeader(String name, String value)` : name 헤더에 value 값을 추가
- `addIntHEader(Stringname, int value)` : name 헤더에 정수형 value 값을 추가
- `setDateHeader(String name, long date)` : name 헤더의 값을 date로 지정
- `setHeader(String name, String value)` : name 헤더 값을 value로 지정
- `setIntHeader(String name, int value)` : name 헤더 값을 정수형 value로 지정
- `containsHeader(String name)` : 이름이 name인 헤더를 포함하고 있을 때 true를 리턴

리다이렉트를 이용해서 페이지를 이동할 수 있다.

`response.sendRedirect("이동할 페이지")`

4. 표현 언어

```jsp
<% 
    int a = Integer.parseInt(request.getParameter("a"));
    int b = Integer.parseInt(reqeust.getParameter("b"));
%>
a * b = <%= a * b %>
```

더 간결하게 표헌이 된다.

```jsp
a * b= ${param.a * param.b}
```

5. 표준 액션 태그와 태그 라이브러리

```jsp
<%@ page contentType = "text/html; charset=utf-8" %>
<html>
    ....
    <jsp:include page="header.jsp" flush="true" />
    ....
</html>
```

`<jsp:include page="header.jsp" flush="true" />` 는 액션 태그로 여러 종류가 있다.

커스텀 태그는 개발자가 개발해주어야 한다.

# JSP 주석

`<%-- --%>`을 이용해서 주석처리를 한다.

`<%-- <%-- 주석 --%> --%>` 일 경우, 마지막 `--%>`는 출력된다.