# Tomcat

JSP와 서블릿을 사용하기 위해 JDK, Tomcat을 설치하고 세팅을 해주어야 한다.

JDK 8.0 과 Tomcat 8.5 를 설치했다.

1. 환경 변수 : C:\Program Files\Eclipse Adoptium\jdk-8.0.352.8-hotspot\bin

2. 톰캣 다운로드
  - http://tomcat.apache.org/downlad-80.cgi

3. 톰캣 실행
  - `\bin\startup.bat` 를 직접 실행하거나 `catalina.bat run` 으로 터미널로 실행한다.
  - 톰캣창이 Server startup이 안뜬다면 터미널에서 실행해보자
  - 환경변수에서 JAVA_HOME과 JRE_HOME이 설정이 안되있다면 다음과 같은 에러들이 뜬다.

    ```
    # JRE_HOME, JAVA_HOME 설정이 없을 때,
    Neither the JAVA_HOME nor the JRE_HOME environment variable is defined
    At least one of these environment variable is needed to run this program
    ```

    ```
    # JRE_HOME 설정이 없을 때,
    The JRE_HOME environment variable is not defined correctly
    This environment variable is needed to run this program
    ```

    ```
    # 성공되면 다음과 같은 경우가 뜬다.
    [main] org.apache.catalina.startup.Catalina.start Server startup in 575 ms
    ```

