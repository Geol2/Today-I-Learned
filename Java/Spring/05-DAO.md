# DAO

어떻게 데이터를 등록하고 가져올 것인가에 대한 관심사를 담당

- JDBC API를 사용할 것인가 / DB 전용 API를 사용할 것인가
- 어떤 테이블 이름과 필드 이름을 사용해서 어떤 SQL 질의를 만들 것인가
- 어떤 객체를 통해 DB의 정보를 전달하고 DB에서 꺼내온 정보를 저장해서 넘길 것인가

예를 들어 데이터베이스 커넥션을 만들 때, 책임과 역할을 잘 분리하도록 한다

```java
public class DAO {
    private ConnectionMaker simpleConnection;

    public DAO() {
        // ConnectionMaker 제공할 수 있는 방법이 고정되어 있다는 단점이 있다
        simpleConnection = new SimpleConnection();
    }

    //...
}
```

한 클래스에서 책임과 역할을 모두 한다면, 결합도가 매우 높아져서 수정이 발생되면 애플리케이션이 거대할수록 수정하기 위한 엄청난 시간/비용이 발생하게 된다

```java
interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException
}

class DConnectionMaker implements ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        //...
    }
}

class NConnectionMaker implements ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        //...
    }
}

class DAO {

    ConnectionMaker connectionMaker;

    public DAO(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    // ...
}
```

DAO 클래스는 어떤 종류의 커넥션이던 간에 상관없이 DBMS에 잘 연결해서 가져올 수 있도록 한다