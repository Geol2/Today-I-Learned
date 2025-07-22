# @Order

클래스 내의 @Test 들은 위에서 아래로 순서대로 진행한다는 보장이 되어있지 않다고 한다

테스트에 순서를 지정할 수 있는 방법으로 낮은 순위일수록 먼저 실행된다

`@TestInstance(Lifecycle.PER_CLASS)`와 함께 `@TestMethodOrder`를 사용할 수 있다

`OrderAnnotation`, `Alphanumeric`, `Random` 세 가지가 존재한다

```java
import org.junit.jupiter.api.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderTest {
  @Order(1)
  void testOrder1() {
    // ...
  }

  @Order(2)
  void testOrder2() {
    // ...
  }
}
```

