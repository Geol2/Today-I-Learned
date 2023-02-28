# Singleton

```
인스턴스를 하나만 만들어 사용하기 위한 패턴
= 클래스 인스턴스를 하나만 만들고, 그 인스턴스로의 전역 접근을 제공한다
```

커넥션 풀, 디바이스 설정 객체, 스레드 풀 등 객체를 여러 개 만들게 되면 불필요한 자원을 사용하게 해서 예상치 못한 결과를 낳는다

싱글턴 패턴을 적용할 경우, 두 개 이상의 객체가 존재할 수 없다

1. 생성자에 private 접근 제어자를 지정
2. 유일한 단일 객체를 반환할 수 있는 정적 메소드가 필요하다
3. 유일한 단일 객체를 참조할 정적 참조 변수가 필요하다
4. 쓰기 가능한 속성을 가지지 않고 읽기만 가능하다

```java
class Singleton {
   private static Singleton single; // 3.

    private Singleton() {} // 1.

    public static Singleton getInstance() { // 2.
        if(single == null) {
            single = new Singleton();
        }

        return single;
    }
}

public class Main {
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        Singleton s3 = Singleton.getInstance();

        System.out.println(s1); // [package].Singleton@263c8db9
        System.out.println(s2); // [package].Singleton@263c8db9
        System.out.println(s3); // [package].Singleton@263c8db9
    }
}
```

----

```java
public class ChcolateBoiler {
    private boolean empty;
    private boolean boiled;
    private ChcolateBoiler uniqueInstance; // 3.

    private ChocolateBoiler() { // 1.
        empty = true;
        boiled = false;
    }

    private ChocolateBoiler getInstance() { // 2.
        if(uniqueInstance == null) {
            return new ChocolateBoiler();
        }
        return uniqueInstance;
    }

    public void fill() {
        if(isEmpty()) {
            empty = false;
            boiled = false;
        }
    }

    public void drain() {
        if(!isEmpty() && isBoiled()) {
            empty = true;
        }
    }

    public void boil() {
        if(!isEmpty() && !isBoiled()) {
            boiled = true;
        }
    }

    public boolean isEmpty() {
        return empty;
    }

    public boolean isBoiled() {
        return boiled;
    }
}
```

### 멀티 스레드에서 아래의 코드를 만들게 된다면 여러 개의 객체를 만들게 될까?

1번 스레드와 2번 스레드가 있다고 가정하고 아래의 코드를 실행하면

`getInstace()` 메소드 호출 과정에서 두 스레드가 경합하는 과정이 발생된다

```java
ChcolateBoiler boiler = ChocolateBoiler.getInstance();
boiler.fill();
boiler.boil();
boiler.drain();
```

싱글턴 패턴이 무너지게 되어 두 개의 객체를 생성할 수 있다

### 해결하는 방법은 어떤 것이 있을까?

1. 메소드 syncronized 키워드를 이용한다
2. 인스턴스가 필요할 때 생성하지 말고 처음부터 만들어버린다
3. `uniqueInstance` 에 volatile 키워드를 이용하고 처음에만 동기화를 해주도록 개선한다

```java
public class ChcolateBoiler {
    private boolean empty;
    private boolean boiled;
    private volatile static ChcolateBoiler uniqueInstance;

    public static ChocolateBoiler getInstance() {
        if(uniqueInstance == null) {
            syncronized(ChocolateBoiler.class) {
                if(uniqueInstance == null) {
                    uniqueInstance = new ChocolateBoiler();
                }
            }
        }
        return uniqueInstance;
    }

    // .. 기타 등등
}
```