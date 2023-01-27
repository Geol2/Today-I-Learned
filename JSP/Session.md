# Session

세션은 오직 서버에만 생성된다

세션은 웹 컨테이너에 정보를 보관할 때 사용한다

로그인한 사용자의 정보를 유지하기 위한 목적으로 세션을 사용한다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/JSP/images/session.png" />

## 1.1 세션 생성하기

### JSP에서 세션을 생성

page 디렉티브의 session 속성을 "true"로 지정하면 된다

이후 세션은 session 기본 객체를 통해서 세션을 사용할 수 있다

```jsp
<%@ page contentType= ... %>
<%@ page session = "true" %>

<%  
    ...
    session.setAttribute("userinfo", userInfo);
    ...
%>
```

### request.getSession() 을 이용한 세션 생성

```jsp
<%@ page session = "false" %>

<%
    HttpSession httpSession = request.getSession();
    List list = (List)httpSession.getAttribute("list");
    list.add(productId);
%>
```

`request.getSession()` 메서드는 session이 존재하면 해당 session을 리턴하고 없으면 새로운 session을 생성하여 리턴한다

`request.getSession(false)`로 설정하면, 세션이 존재하는 경우에만 session 객체를 리턴하고 없으면 null을 반환한다

## 1.2 session 기본 객체

|      메서드            | 리턴타입 | 설명 |
|-----------------------|----------|----------------------|
| getId()               | String   | 세션의 고유 ID를 구한다 |
| getCreationTime()     | long     | 세션이 생성된 시간을 구한다. 시간은 1970년 1월 1일 이후 흘러간 시간을 의미하며, 단위는 1/1000초이다. |
| getLastAccessedTime() | long     | 웹 브라우저가 가장 마지막에 세션에 접근한 시간을 구한다. 1970년 1월 1일 이후 흘러간 시간을 의미 |

각 세션을 구분하기 위해 SESSION ID 라는 고유 ID를 할당한다

웹 서버는 웹 브라우저에 세션 ID를 전송한다

웹 브라우저는 웹 서버에 연결할 떄마다 매번 세션 ID를 보내서 웹 서버가 어떤 세션을 사용할지 판단할 수 있게 한다

웹 서버는 세션ID를 이용해서 웹 브라우저를 위한 세션을 찾기 때문에, 웹 서버와 웹 브라우저는 세션 ID를 공유할 수 있도록 쿠키라는 것을 사용한다.

JSESSIONID 쿠키가 세션 ID를 공유할 떄 사용하는 쿠키이다

## 1.3 기본 객체의 속성 사용

한 번 생성된 세션은 지정한 유효 시간 동안 유지되므로 애플리케이션이 실행하는 동안 지속적으로 사용해야 하는 데이터의 저장소로 적절하다

session 기본 객체는 웹 브라우저의 여러 요청을 처리하는 JSP 페이지 사이에서 공유된다

세션값을 저장할 땐, 다음과 같은 메서드를 사용한다

```jsp
<% 
    session.setAttribute("MEMBERID", "멤버아이디");
    session.setAttribute("NAME", "이름");
%>
```

세션값을 불러올 땐, 아래의 메소드를 사용한다

```jsp
<% 
    session.getAttribute("MEMBERID");
%>
```

## 1.4 세션 종료

세션을 종료하면 기존 session 객체는 사라지고, 새로운 세션을 요청하여 새 session 기본 객체를 생성한다

```jsp
<% 
    session.invalidate();
%>
```

## 1.5 세션 유효 시간

세션의 최근 접근 시간을 불러올 때는 아래와 같은 메소드를 사용한다

```jsp
<% 
    session.getLastAccessedTime();
%>
```

### web.xml을 이용한 세션 유효 시간 설정

```jsp
<session-config>
    <session-timeout>50</session-timeout>
</session-config>
```

50분으로 세션의 유효시간을 지정하고 있다.

### session 객체를 이용한 세션 유효 시간 설정

```jsp
<% 
    session.setMaxInactiveInterval(60 * 60);
%>
```

## 세션을 사용한 로그인 상태 유지

1. 로그인 성공 시, session 기본 객체의 특정 속성에 데이터를 기록
2. 이후 session 기본 객체에 특정 속성이 존재하면 로그인 한 것으로 간주
3. 로그아웃 시, `session.invalidate()` 호출하여 세션을 종료시킴

예제에선 로그인 성공 시, session 객체에 MEMBERID라는 속성을 통해서 로그인 상태를 유지한다

## 연관된 정보를 클래스로 묶기

아래와 같은 `setAttribute()` 가 많아짐에 따라 유지보수가 불편해지는 단점이 있다

```jsp
<% 
    session.setAttribute("MEMBERID", "멤버아이디");
    session.setAttribute("NAME", "이름");
    // ...
%>
```

클래스로 묶어서 객체를 만들어서 저장해보자

```java
public class MemberInfo {
    private String id;
    private String name;
    private String email;
    private boolean male;
    private int age;

    //...
}
```

다음과 같이 개별로 저장하지 않고 한 개의 속성을 이용하면 용이하다

```jsp
<% 
    MemberInfo memberInfo = new MemberInfo(id, name, ...);
    session.setAttrubute("memeberInfo", memberInfo);

    // ...

    MemberInfo member = (MemberInfo)session.getAttribute("memberInfo");
%>

<%= member.getEmail().toLowerCase() %>
```

해당 email 속성이 필요가 없어 삭제되었다고 해도, 호출된 함수에 대해 런타임 에러보다는 컴파일 에러를 표시하므로 추적하기도 쉽다