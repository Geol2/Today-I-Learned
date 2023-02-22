# Strategy

```
클라이언트가 전략을 생성해 어떤 전략을 실행할 컨텍스트에 주입하는 패턴
```

- 전략 메소드를 가진 전략 객체
- 전략 객체를 사용하는 컨텍스트
- 전략 객체를 생성해 컨텍스트에 주입하는 클라이언트

```java
interface Stratgy {
    public abstract void runStrategy();
}

class StrategyGun implements Strategy {
    @Override
    public void runStrategy() {
        System.out.println("탕, 타당, 타다당");
    }
}

class StrategySword implements Strategy {
    @Override
    public void runStrategy() {
        System.out.println("슉 슈슉 슈슈슉");
    }
}

class StrategyBow implements Strategy {
    @Override
    public void runStrategy() {
        System.out.println("피융 슝");
    }
}

class Soldier {
    void runContext(Strategy strategy) {
        System.out.println("전투 시작");
        strategy.runStrategy();
        System.out.println("전투 종료");
    }
}

public class Main {
    public static void main(String[] args) {
        Strategy strategy = null;
        Soldier rambo = new Soldier();

        // 총을 건네서 수행하게 한다, 원하는 전략을 골라서 수행시킨다
        strategy = new StrategyGun();
        rambo.runContext(strategy);

        // 검을 건네서 수행하게 한다
        /* strategy = new StrategySword();
        rambo.runContext(strategy); */

        // 활을 건네서 수행하게 한다
        /* strategy = new StrategyBow();
        rambo.runContext(strategy); */ 
    }
}
```