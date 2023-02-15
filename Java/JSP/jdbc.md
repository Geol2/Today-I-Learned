# JDBC

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/JSP/images/jdbc-struct.png" />

JSP를 포함한 자바 애플리케이션에 데이터베이스를 사용할 떄에는 데이터베이스 종류에 상관없이 JDBC API를 이용해서 데이터베이스에 접근한다.

JDBC API에는 DBMS(MySQL, Oracle 등등)에 알맞는 JDBC 드라이버만 있으면 어떤 데이터베이스라도 사용할 수 있다.

## 실행 순서

1. JDBC 드라이버 로딩
```java
Class.forName("com.mysql.jdbc.Driver")
```

2. 데이터베이스 커넥션 구함
```java
String jdbcDriver = "jdbc:mysql://localhost:3306/chap14?" +
                    "useUnicode=true&characterEncoding=utf8";
String dbUser = "jspexam";
String dbPass = "jsppw";

String query = "SELECT * FROM MEMBER order by MEMBERID";

Connection conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
```

3. 쿼리 실행을 위한 Statement 객체 생성
```java
Statement stmt = conn.createStatement();
```

4. 쿼리 실행
```java
ResultSet rs = stmt.executeQuery(query);
```

5. Statement 종료
```java
finally {
    if(rs != null) try { rs.close(); } catch(SQLException ex) {}
    if(stmt != null) try { stmt.close(); } catch(SQLException ex) {}
    //...
}
```

6. 데이터베이스 커넥션 종료
```java
finally {
    // ...
    if(conn != null) try { conn.close(); } catch(SQLException ex) {}
}
```

5번과 6번에서 java 7의 `try-with-resource`에 대해 공부를 했었으므로 다음과 같이 나타낼 수도 있다

```java
try (Connection conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(query)) {
        while(rs.next()) {
            //...
        }
} catch(Exception e) {
    // ...
}
```

## Statement를 사용한 쿼리 실행

Connection 객체를 생성한 후, Connection 객체에서 Statement를 생성하고 createStatement() 메소드를 사용해서 쿼리를 실행할 수 있다.

```java
ResultSet executeQuery(String query) // SELECT 쿼리를 실행
int executeUpdate(String query)      // INSERT, UPDATE, DELETE 쿼리를 실행
```

## PreparedStatement를 사용한 쿼리 실행

1. Connection 객체를 생성한 후, Connection 객체에서 prepareStatement() 메소드를 사용해서 PreapredStatement를 생성한다

2. PareparedStatement의 set 메소드를 사용해서 필요한 값 지정

3. PreparedStatement의 executeQuery() 또는 executeUpdate() 메소드를 사용해서 쿼리를 실행

4. PreparedStatement를 수동으로 닫거나 AutoCloseable 을 사용해서 닫기

```java
String memberId = request.getParameter("memberID");
String password = request.getParameter("password");
String name = request.getParameter("name");
String email = request.getParameter("email");

String query = "INSERT INTO MEMBER(MEMBERID, NAME, EMAIL) VALUES (?, ?, ?)";

Connection conn = DriverManager.getConnection(jdbcDriver, dbUser, dbPass);
PreparedStatement pstmt = conn.prepareStatement(query);
pstmt.setString(1, memberID);
pstmt.setString(2, password);
pstmt.setString(3, name);
pstmt.setString(4, email);

pstmt.executeUpdate();
```

## Statement VS PreparedStatement

흔히 PreparedStatement를 사용한다고 한다

1. 값 변환을 자동으로 하기 위해서 (SQL Injection 방어)
2. 간결한 코드를 위해서

DBMS는 내부적으로 4가지 과정(parse, bind, excute, fetch)를 거쳐 결과를 출력한다

Statement를 사용하여 쿼리를 입력 시, 매번 parse부터 fetch까지 모든 과정을 수행한다

PreparedStatement를 사용하면 parse 과정을 최초 1번 수행 후 메모리에 저장해두고 필요할 때마다 사용하며 자주 변경되는 부분을 변수로 선언해두고 매번 다른 값을 대입하여 사용한다

1. Statement를 이용

```java
String query = "SELECT * FROM users WHERE a = '" + str + "'";
// SELECT * FROM users WHERE a = 'a' OR 1 = 1#'
```

`str` 변수에 `a' OR 1 = 1#'` 을 준다면 입력이 모두 참으로 되서 전체 데이터가 출력된다

getConnection() 메소드의 파라미터 내에 allowMultiQueries 옵션을 true로 지정하는 것은 좋지 않다

Statement를 이용해서 `str` 변수에 `a' OR 1 = 1; SELECT * FROM users;`를 대입해보면 전체 데이터가 출력되고 `;` 뒤를 조작해서 유저를 보거나 수정/삭제를 진행할 수 있다

2. PreparedStatement를 이용

```java
String query = "SELECT * FROM users WHERE a = ?";

pstmt.setString(1, str);
// SELECT * FROM users WHERE a = 'a\' or 1 = 1#'
```

`str` 변수에 `a' OR 1 = 1#'` 을 준다면 `'`문자를 이스케이핑하여 SQL Injection 방어가 이루어질 수 있다

작은 따옴표가 필요한 상황이여도 적절하게 작은 따옴표를 두 번으로 나타낼 수 있다

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

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/JSP/images/transection.png" />

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

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/JSP/images/connection-pool.png" />

- 커넥션 풀의 특징
  - 풀 속에 미리 커넥션이 생성되어 있기 때문에 커넥션을 생성하고 닫는 유지비용이 덜 듬
  - 커넥션을 계속해서 재사용하기 때문에 생성되는 커넥션 수가 일정하게 유지됨

### 1. DBCP를 이용해서 커넥션 풀 사용하기

자카르타 프로젝트의 DBCP2 API를 사용할 때 다음과 같은 과정을 거침

1. DBCP 관련 jar 파일과 JDBC 드라이버 jar 파일 설치하기
2. 커넥션 풀 초기화하기
3. 커넥션 풀로부터 커넥션 사용하기

#### 2. 필요한 jar 파일 복사하기

- Commons DBCP API 관련 jar 파일
- Commons DBCP API 가 사용하는 Commons Pool API의 jar 파일
- 로그기록에 사용하는 Commins Logging API 관련 jar 파일

#### 3. 커넥션 풀 초기화 서블릿 클래스 작성

```java
// DBCPInit.java

//..
// 1. 실제 커넥션을 생성할 ConnectionFactory를 생성
ConeectionFactory conn = new DriverManagerConnectionFactory(jdbcUrl, username, pw);
// 2. 커넥션 풀로 사용할 PoolableConnectionFactory을 생성
PoolableConnectionFactory poolConnFactory = new PoolableConnectionFactory(connFactory, null);

// 3. 커넥션이 유효한지 여부를 검사할 때, 사용할 쿼리
// 검증 쿼리로 인한 오류가 발생되지 않게 설정 또한 필요하다고 한다
poolConnFactory.setValidationQuery("select 1");

// 4. 커넥션 풀의 설정 정보를 생성
GenericObjectConfig poolConfig = new GenericObjectConfig();
poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
poolConfig.setTestWileIdle(true);
poolConfig.setMinIdle(4);
poolConfig.setMaxTotal(50);

// 5. 커넥션 풀 생성
GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolConnFactory, poolConfig);
poolConnFactory.setPool(connectionPool);

// 6. 커넥션 풀을 제공하는 JDBC 드라이버를 등록
Class.forName("org.apache.commons.dbcp2.PoolingDriver");
PoolingDriver driver =(PoolingDrvier)DriverManager.getDriver("jdbc:apache:commons:dbcp:");

// 7. 커넥션 풀 드라이버에 ConnectionPool을 등록한다
driver.registerPool("chap14", connectionPool);

```
#### 4. 커넥션 풀 초기화 서블릿 설정

```xml
<servlet>
    <sevlet-name>DBCPInit</servlet-name>
    <sevlet-class>jdbc.DBCPInit</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
```

#### 5. 커넥션 풀로부터 커넥션 사용하기

```java
try {
    String jdbcDriver = "jdbc:apache:commons:dbcp.chap14";
    String query = "SELECT * FROM MEMBER ORDER BY MEMBERID";
    conn = DriverManager.getConnection(jdbcDriver);
    stmt = conn.createStatement();
    rs = stmt.executeQuery(query);
    // ...
} 
```

#### 6. 커넥션 풀 속성 설명

[DBCP 설정](https://commons.apache.org/proper/commons-dbcp/configuration.html)

GenericObjectConfig PoolConfig를 사용할 때, 커넥션 풀의 크기, 검사 주기 등을 설정할 수 있는 메소드를 제공하고 있다.

| 속성 이름	   | 설명 |
|-------------|------|
| initialSize | BasicDataSource 클래스 생성 후 최초로 getConnection() 메서드를 호출할 때 커넥션 풀에 채워 넣을 커넥션 개수 |
| maxActive	  | 동시에 사용할 수 있는 최대 커넥션 개수(기본값: 8) |
| maxIdle	  | 커넥션 풀에 반납할 때 최대로 유지될 수 있는 커넥션 개수(기본값: 8) |
| minIdle	  | 최소한으로 유지할 커넥션 개수(기본값: 0) |

- maxActive 값과 maxIdle 값이 같은 것이 바람직하다.
- maxActive >= initialSize 로 설정한다.
- maxIdle >= mindle 로 설정하여 논리적 오류가 발생하지 않게 설정한다.

maxActive 값은 DBMS의 설정과 애플리케이션 서버의 개수, Apache, Tomcat에서 동시에 처리할 수 있는 사용자 수 등을 고려해서 설정해야 한다.

## 커넥션 풀 프레임워크 종류

Apache Commons DBCP, Tomcat DBCP, HikariCP, Oracle UCP 등등 있다

각 커넥션 풀 프레임워크의 종류에 따라 적정한 커넥션 풀 사이즈를 제안하고 있다고 한다

## 적절한 connection 수를 찾기 위해서

서버의 CPU, 메모리 사용량, DB 서버의 CPU 등을 확인
- 모니터링 환경 구축
- 백엔드 시스템 부하 테스트
- 단위 초당 처리량, 평균적인 응답시간 확인

요청마다 스레드를 발생시키는 모델 : active thread 수 확인

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/JSP/images/connection-pool-2.png" />

요청을 위한 스레드 풀을 만들어두고 요청이 올 때마다 스레드 풀에서 스레드가 나가서 하나씩 대응되는 동작

스레드 풀의 스레드가 병목을 일으키는 원인이 될 수도 있기 때문에 스레드 풀의 개수를 변경해서 해결할 수도 있다

스레드 풀의 문제가 아니라면 DBCP의 active 커넥션 수를 확인해보도록 한다

사용할 서버 수를 고려해서 DBCP의 Max pool Size 를 결정하면 된다

------

- [D2-NAVER Commons DBCP 이해하기](https://d2.naver.com/helloworld/5102792)