# Template Callback

```
전략을 익명 내부 클래스로 구현한 전략 패턴
```

전략 패턴의 변경으로 DI에서 사용하는 특별한 형태의 전략 패턴

전략을 익명 내부 클래스로 정의하는데서 차이가 있다

```java
interface Strategy {
    public abstract void runStrategy();
}

class Solider {
    void runContext(String weaponSound) {
        System.out.println("전투 시작");
        executeWeapon(weaponSound).runStrategy();
        System.out.println("전투 종료");
    }

    private Strategy executeWeapon(final String weaponSound) {
        return new Strategy() {
            @Override
            public void runStrategy() {
                System.out.println(weaponSound);
            }
        };
    }
}

public class Client {
    public static void main(String[] args) {
        Soldier rambo = new Soldier();

        rambo.runContext("총!");
        System.out.println();

        // rambo.runContext("칼!");
        // System.out.println();

        // rambo.runContext("도끼!");
        //System.out.println();
    }
}
```