# JDBC

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/JSP/images/jsp-struct.png" />

JSP를 포함한 자바 애플리케이션에 데이터베이스를 사용할 떄에는 데이터베이스 종류에 상관없이 JDBC API를 이용해서 데이터베이스에 접근한다.

JDBC API에는 DBMS(MySQL, Oracle 등등)에 알맞는 JDBC 드라이버만 있으면 어떤 데이터베이스라도 사용할 수 있다.

## 실행 순서

1. JDBC 드라이버 로딩
2. 데이터베이스 커넥션 구함
3. 쿼리 실행을 위한 Statement 객체 생성
4. 쿼리 실행
5. Statement 종료
6. 데이터베이스 커넥션 종료

## DBMS와의 통신을 위한 JDBC 드라이버

- MySQL : `com.mysql.jdbc.Driver`
- Oracle : `oracle.jdbc.driver.OracleDriver`
- MSSQL : `com.microsoft.sqlserver.jdbc.SQLServerDriver`

## 데이터베이스 식별을 위한 JDBC URL

`jdbc:[DBMS]:[데이터베이스식별자]`

`jdbc:mysql://HOST[:PORT]/DBNAME[?param=value&param1=value2&...]`

## JDBC 드라이버 로딩하기

웹 애플리케이션이 시작할 때, 자동으로 JDBC 드라이버를 로딩하도록 지정하면 페이지마다 JDBC 드라이버를 로딩할 필요가 없다.

```java
// src\jdbc\MySQLDriverLoader

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class MySQLDriverLoader extends HttpServlet {

    public void init(ServletConfig config) throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(Exception ex) {
            throw new ServletException(ex);
        }
    }
}
```

```java
// \webapps\manager\WEB-INF

// ...

<selvet>
    <sevlet-name> mysqlDriverLoader </sevlet-name>
    <sevlet-class> jdbc.MySQLDriverLoader </sevlet-class>
    <load-on-startup>1</load-on-startup>
</selvet>

// ...

```

## JDBC 트랜잭션 처리

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Database/images/transection.png" />

- 트랜잭션 구현하는 방법
  1. JDBC의 오토 커밋 모드를 false로 지정하는 방법
  2. JTA를 이용하는 방법

```java
try {
    conn = DriverManager.getConnection(...);
    conn.setAutoCommit(false);

    // ... 쿼리 실행
    // ... 쿼리 실행

    // 트랜잭션 커밋
    conn.commit();
} catch(SQLException ex) {
    if(conn != null) {
        conn.rollback();
    }
} finally {
    if(conn!= null) {
        try {
            conn.close();
        } catch(SQLException ex) {}
    }
}
```

## 커넥션 풀

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Database/images/connection-pool.png" />

- 커넥션 풀의 특징
  - 풀 속에 미리 커넥션이 생성되어 있기 때문에 커넥션을 생성하는 유지비용이 덜 듬
  - 커넥션을 계속해서 재사용하기 때문에 생성되는 커넥션 수가 일정하게 유지됨

### DBCP를 이용해서 커넥션 풀 사용하기

자카르타 프로젝트의 DBCP2 API를 사용할 때 다음과 같은 과정을 거침

1. DBCP 관련 jar 파일과 JDBC 드라이버 jar 파일 설치하기
2. 커넥션 풀 초기화하기
3. 커넥션 풀로부터 커넥션 사용하기

#### 필요한 jar 파일 복사하기

- Commons DBCP API 관련 jar 파일
- Commons DBCP API 가 사용하는 Commons Pool API의 jar 파일
- 로그기록에 사용하는 Commins Logging API 관련 jar 파일

####