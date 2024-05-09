# 인스턴스화를 막으려거든 private 생성자를 사용하라

```java
public class UtilityClass {
  // 기본 생성자가 만들어지는 것을 막는다.
  private UtilityClass() {
    throw new AssertionError();
  }
  // ...
}
```

유틸리티 클래스 같은 경우 정적 메서드나 정적 필드만을 담은 클래스만 만들고 싶을 때, 

초기에 생성자를 명시하지 않아 컴파일러가 자동으로 기본 생성자를 만들어준다.

이 의미는 자동으로 public 생성자가 만들어지고 자동으로 생성되는 것인지 구분할 수 없다.

추상 클래스로 만드는 것으로는 인스턴스화를 막을 수 없다.

private 생성자를 추가하면 클래스의 인스턴스화를 막을 수 있다.

위의 예제처럼 굳이 `AssertionError`를 리턴할 필요는 없지만, 어떠한 경우에도 인스턴스화되는 것을 막아준다.

이 방식은 상속을 불가능하게 하는 효과도 있다. 모든 생성자는 명시적이든 묵시적이든 상위 클래스의 생성자를 호출하게 되는데, 이를 private으로 선언해서 상위 클래스의 생성자에 접근할 길이 막힌다.