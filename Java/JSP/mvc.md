# MVC

## 모델 1 구조

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/JSP/images/model-1.png" />

모델 1 구조는 웹 브라우저의 요청을 JSP를 이용한 단순한 모델

## 모델 2 구조

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/JSP/images/model-2.png" />

모델 2 구조는 웹 브라우저의 요청을 서블릿에서 알맞게 처리 후, 그 결과를 JSP를 이용한 모델

모델 2의 구조를 이용해서 MVC 패턴을 만들었다라고도 한다

### 모델 2 구조에서의 MVC 패턴

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/JSP/images/model-mvc.png" />

- 모델 : 비즈니스의 로직을 처리한다
- 뷰 : 비즈니스의 사용자가 볼 화면을 담당한다
- 컨트롤러 : 사용자의 입력처리와 흐름을 제어한다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/JSP/images/model-mvc-controller.png" />

서블릿은 화면에 출력할 메세지를 생성해서 JSP에 전달한다

JSP는 서블릿으로부터 전달 받은 메시지를 출력한다

```java
// Servlet
public class SimpleController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOExcetption {
        processRequest(requst, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOExcetption {

        // 요청 파악
        String type = request.getParameter("type");

        // 요청한 기능을 수행
        Object resultObject = null;
        if(type == null || type.equals("greeting")) {
            resultObject = "안녕하세요";
        } else if( type.equals("date")) {
            resultObject = new java.util.Date();
        } else {
            resultObject = "Invalid value";
        }

        // request나 session에 처리결과를 저장
        request.setAttribute("result", resultObject);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/simpleView.jsp");

        dispatcher.forward(request, response);
    }
}
```

```java
// JSP
결과 : ${result}
```

### MVC 패턴에서의 커맨드 패턴 기반의 코드

```
http://localhost:8080/controller?cmd=BoardList&...
```

서블릿 내부를 간편하게 나타낼 수 있는 장점이 있다

```java
public interface CommandHandler {
    public String process(HttpServletRequest req, HttpServletResponses res) throws Exception;
}

public class Handler implements CommandHandler {
    public String process(HttpServletRequest req, HttpServletResponses res) {
        // 명령어와 관련된 비즈니스 로직 처리
        // ...

        // 뷰 페이지에서 사용할 정보 저장
        request.setAttribute("somValue", value);
        // ...

        // 뷰 페이지의 URI 리턴
        return "/view/somView.jsp";
    }
}
```

```java
String command = request.getParameter("cmd");
CommandHandler handler = null;

if(command == null) {
    handler = new Handler();
} else if( command.equals("BoardList") ) {
    handler = new BoardListHandler();
} else if( command.equals("BoardWriteForm") ) {
    handler = new BoardWriteFromHandler();
}

String viewPage = handler.process(request, response);

RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
dispatcher.forward(request, response);
```

#### 설정 파일에 커맨드와 클래스의 관계 명시하기

```java
public class SimpleController extends HttpServlet {

    private Map<String, CommandHanlder> commandHandlerMap = new HashMap<>();

    public void init() throws ServletException {
        
        String configFile = getInitParameter("configFile");
        Properties prop = new Properties();
        String configFilePath = getServletContext().getRealPath(configFile);
        try (FileInputStream fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new SevletException(e);
        }

        Iterator keyIter = prop.keySet().iterator();
        while(keyIter.hasNext()) {
            String command = (String) keyIter.next();
            String handlerClassName = prop.getProperty(command);
            try {
                Class<?> handlerClass = Class.forName(handlerClassName);
                CommonHandler handlerInstance = (CommonHandler) handlerClass.newInstance();
                commandHandlerMap.put(command, handlerInstance);
            } catch ( ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOExcetption {
        process(requst, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOExcetption {

        // 요청 파악
        String command = request.getParameter("cmd");
        CommandHandler handler = commandHandlerMap.get(command);

        // 요청한 기능을 수행
        if(handler == null) {
            // 1.
            handler = new NullHandler();
        }

        String viewPage = null;
        try {
            viewPage = handler.process(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        if(viewPage != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/simpleView.jsp");
            dispatcher.forward(request, response);
        }
    }
}
```

1. NullHandler 구현

```java
public class NullHandler implements CommandHanlder {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.sendError(HttpServletResonse.SC_NOT_FOUND);
        return null;
    }
}
```

2. SimpleController init() 내부의 configFile 초기화 파라미터에 설정 파일 경로를 지정

/WEB-INF/commandHandler.properties 파일로부터 설정 정보를 읽어와서 <명령어, 핸들러 객체> 매핑정보를 생성함

```xml
// web.xml
<servlet>
    <servlet-name> SimpleController </sevlet-name>
    <servlet-class> ..SimpleController </servlet-class>
    <init-param>
        <param-name>configFile</param-name>
        <param-value>/WEB-INF/commandHandler.properties</param-value>
    </init-param>
</servlet>
```

명령어 매핑 정보파일 작성

```
// commandHandler.properties
hello = any.HellowHandler
```

```java
public class HelloHandler implements CommandHandler {
    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("hello", "안녕하세요");
        return "/WEB-INF/view/hello.jsp";
    }
}
```

```jsp
// url : localhost:8080/SimpleController?cmd=hello
<%= request.getAttribute("hello") %>
<%-- 안녕하세요 --%>
```

#### `request.getParameter("cmd")` 대신 URI 명령어로 사용하기

```java
// ...

String command = request.getRequestURI();

if(command.indexOf(request.getContextPath()) == 0) {
    command = command.substring(request.getContextPath().length());
}

// ...
```