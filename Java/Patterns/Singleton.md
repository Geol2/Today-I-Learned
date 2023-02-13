# Singleton

인스턴스를 하나만 만들어 사용하기 위한 패턴

커넥션 풀, 디바이스 설정 객체, 스레드 풀 등 객체를 여러 개 만들게 되면 불필요한 자원을 사용하게 해서 예상치 못한 결과를 낳는다

싱글턴 패턴을 적용할 경우, 두 개 이상의 객체가 존재할 수 없다

1. 생성자에 private 접근 제어자를 지정
2. 유일한 단일 객체를 반환할 수 있는 정적 메소드가 필요하다
3. 유일한 단일 객체를 참조할 정적 참조 변수가 필요하다
4. 쓰기 가능한 속성을 가지지 않고 읽기만 가능하다

```java
class Singleton {
    static Singleton single; // 3.

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



