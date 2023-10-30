# JUnit

모든 단위 테스트에 대한 프레임워크가 따라야 하는 세 가지 규칙

- 단위 테스트는 다른 모든 단위 테스트들과 독립적으로 실행되어야 한다
- 프레임워크는 테스트 각각의 오류를 식별하고 보고해야 한다
- 어떤 테스트를 실행할지 선택하기 쉬워야 한다

JUnit 에 대한 설계 목표 세 가지가 존재한다고 한다

- 유용한 테스트를 작성하는데 보탬이 되어야 한다
- 시간이 지나도 가치가 변치 않는 테스트를 작성하는 데 보탬이 되어야 한다
- 코드 재사용을 통해 테스트 작성 비용을 낮추는 데 보탬이 되어야 한다


예제의 한 부분을 참고해서 작성을 해보았다

```java
package com.meme.example;

public class Calculator {

  public double add(double num1, double num2) {
    return num1 + num2;
  }

}
```

```java
package com.meme.code.exampleTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.meme.example.Calculator;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
  @Test
  public void testCalculatorAdd10And50() {
    Calculator cal = new Calculator();
    double result = cal.add(10, 50);
    assertEquals(60, result, 0);
  }

  @Test
  public void testCalculatorAdd10And50Failed() {
    Calculator cal = new Calculator();
    double result = cal.add(10, 50);
    assertEquals(30, result, 0);
    // assertNotEquals(30, result, 0); // success
  }
}
```

인텔리제이에서 해당 테스트 클래스를 만들어서 테스팅을 해볼 수 있다

@Test 라고 만들면, main 메소드에서 실행하지 않고 해당 메소드가 단위 테스트 메소드임을 나타내고 실행할 수 있도록 제공해준다

실행결과는 다음과 같다

```
expected: <30.0> but was: <60.0>
Expected :30.0
Actual   :60.0

// ...

2 tests completed, 1 failed
```

### 간단한 콘솔에서 실행하는 방법

```shell
# Windows
javac -cp \junit4.6\junit-4.6.jar *.java
javac -cp \junit4.6\junit-4.6.jar *.java org.junit.runner.JUnitCore CalculatorTest # 테스트 러너
# Unix
javac -cp /opt/junit4.6/junit-4.6.jar *.jar 
javac -cp /opt/junit4.6/junit-4.6.jar *.jar org.junit.runner.JUnitCore CalculatorTest # 테스트 러너
```

콘솔에서 실행하는 것도 있지만 IntelliJ나 이클립스 툴에서도 테스트를 충분히 실행해볼 수 있다.


자동화된 테스트를 사용하는 개발자가 되어서 해당 만들어둔 테스트를 계속 재사용해서 코드를 증명할 수 있게 만드는 것이 좋다.
