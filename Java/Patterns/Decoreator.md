# Decorator

```
메소드 호출의 반환값에 변화를 주기 위해 중간에 장식자를 두는 패턴
```

프록시 패턴과 구현 방법은 같지만,

프록시 패턴은 클라이언트가 최종적으로 반환되는 값에 변경이 없이 제어의 흐름을 변경할 뿐이지만, 데코레이터 패턴은 반환값에 장식을 덧붙인다

```java
interface IService {
    public abstract String runSomething();
}

class Service implements IService {
    public String runSomething() {
        return "서비스";
    }
}

class Decoreator implements IService {
    IService service;

    public String runSomething() {
        service = new Service();
        return "더하는 " + service.runsomething();
    }
}

public class Main {
    public static void main(String[] args) {
        IService decoreator = new Decoreator();
        System.out.println( decoreator.runsomething() );
    }
}
```

장식자(데코레이터)는 서비스의 인터페이스를 구현한다

장식자(데코레이터)는 서비스를 참조변수로 가진다

장식자(데코레이터)는 서비스의 메소드를 오버라이딩하고 장식을 추가해서 반환한다

장식자(데코레이터)는 실제 서비스의 메소드 호출 전후로 별도의 로직을 수행할 수 있다

