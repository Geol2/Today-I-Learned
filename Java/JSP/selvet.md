# Sevlet

서블릿은 JSP 표준이 나오기 전에 만들어진 표준으로 자바 언어로 웹 애플리케이션을 개발할 수 있도록 하기 위해 만들어졌다

1. 서블릿 규약에 따라 자바 코드를 작성한다
2. 자바 코드를 컴파일해서 클래스 파일을 생성한다
3. \WEB-INF\classes 폴더에 클래스 파일을 패키지에 알맞게 위치시킨다
4. web.xml 파일에 서블릿 클래스를 설정한다
5. 톰캣 등의 컨테이너를 실행한다
6. 웹 브러우저에서 확인한다

## 1.1 Sevlet 구현

서블릿 클래스 구현 시, HttpServlet 클래스를 상속받은 클래스를 작성해야한다

HttoServlet 클래스를 상속받았다면, Http 메소드에 따라 GET, POST 별로 `doGet()`, `doPost()` 을 재정의해서 사용하면 된다

`HttpServletRequest`, `HttpServletResponse`는 JSP의 기본객체를 나타낸다

`PrintWriter`는 전송할 응답 데이터를 전달해준다

```java
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.servlet.ServletException;
import javax.servlet.servlet.http.HttpServlet;
import javax.servlet.servlet.http.HttpServletRequest;
import javax.servlet.servlet.http.HttpServletResponse;

public class NowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();
        out.pritln("<html>");
        out.pritln("<head> <title>현재시간</title> </head>");
        out.pritln("<body>");
        out.pritln("현재 시간은");
        out.pritln(new Date());
        out.pritln("입니다.");
        out.pritln("</body></html>");
    }
}
```

## 1.2 web.xml로 매핑하기

WEB-INF 폴더의 web.xml 파일에 서블릿 클래스를 등록해야 한다

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmls="http://xmlns.jcp.org/xml/ns/javaee"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                        http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
    version="3.1">

    <sevlet>
        <sevlet-name>now</sevlet-name>
        <sevlet-class>example.NowServlet</sevlet-class>
    </sevlet>

    <sevlet-mapping>
        <servlet-name>now</servlet-name>
        <url-pattern>/now</url-pattern>
    </servlet-mapping>

</web-app>
```

서블릿을 등록하려면 두 가지를 설정해야한다

1. 서블릿으로 사용할 클래스
2. 서블릿과 URL 간의 매핑

`sevlet` 태그를 이용해서 서블릿 클래스로 등록한다, `<sevlet-name>`은 해당 서블릿을 참조할 때 사용할 이름을 입력하고 `<sevlet-class>`는 서블릿으로 사용할 클래스의 완전한 이름을 입력한다

`<sevlet-mapping>`태그를 이용해서 해당 서블릿이 어떤 URL을 처리할지에 대한 매핑 정보를 등록한다, `<sevlet-name>`은 매핑할 서블릿 이름을 지정하고, `<servlet-name>`은 매핑할 URL 패턴을 지정한다

## 1.3 어노테이션으로 매핑하기

서블릿 3.0 부터는 web.xml에 등록하지 않아도 `@WebServlet`을 사용하면 된다

```java
@WebServlet(urlPatterns = "/now")
public class NowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();
        out.pritln("<html>");
        out.pritln("<head> <title>현재시간</title> </head>");
        out.pritln("<body>");
        out.pritln("현재 시간은");
        out.pritln(new Date());
        out.pritln("입니다.");
        out.pritln("</body></html>");
    }
}
```

## 1.5 서블릿 로딩과 초기화

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/JSP/images/servlet-init-loading.png" />

초기화 작업은 웹 컨테이너를 처음 구동하는 시점에서 하는 것이 좋고 이를 위한 설정은 `<load-on-startup>` 태그의 내용을 넣어 해주거나 `@WebServlet(urlPatterns = "/now", loadOnStartUp = 1)` 로 로딩값을 어노테이션에 넣어주기도 한다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/JSP/images/servlet-init.png" />

```java
public class DBCPInit extends HttpServlet {
    @Override
    public void init() throws ServletException {
        loadJDBCDriver();
        initConectionPool();
    }

    private void loadJDBCDriver() {
        // ...
    }

    private void initConnectionPool() {
        // ...
        String jdbcUrl = "jdbc:mysql://localhost:3306/ ...";
        // ...
    }
}
```

해당 코드의 단점으론 URL 정보를 변경하려면 직접 수정해야해서 `<init-pram>` 태그를 사용해서 코드에서 변경하지 않도록 한다

`<init-param>` 내부
  - `<param-name>` : 초기화 파라미터 이름 지정
  - `<param-value` : 초기화 파라미터 값 지정

초기화 파라미터에 접근하려면 `getInitParameter()` 메서드를 사용해서 접근할 수 있다

### 매핑 규칙

| URL 패턴   | 매핑 서블릿 |
|------------|-------------|
| /foo/bar/* | servlet1    |
| /baz/*     | servlet2    |
| /catalog   | servlet3    |
| *.bop      | servlet4    |

예시에 따른 경로

|     요청 경로        | 일치 URL 패턴 | 요청 처리 서블릿 |
|----------------------|-------------|-----------------|
| /foo/bar/index.html  | /foo/bar/*  | servlet1        |
| /foo/bar/index.bop   | /foo/bar/*  | servlet1        |
| /baz                 | /baz/*      | servlet2        |
| /baz/index.html      | /baz/*      | servlet2        |
| /catalog             | /catalog    | servlet3        |
| /catalog/racecar.bop | /*.bop      | servlet4        |
| /index.bop           | /*.bop      | servlet4        |