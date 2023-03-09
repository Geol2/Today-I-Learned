# assertEquals

`static import` 를 통해서 사용할 수 있다

## Parameter

매우 다양한 파라미터를 제공하고 있어서 확인하면서 사용해야 한다

### 예제

```java
package com.meme.code.exampleTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.meme.example.Calculator;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
  @Test
  public void calculatorTest() {
    Calculator cal = new Calculator();
    double result = cal.add(10, 50);
    assertEquals(60, result, 0);
  }
}
```

```java
package com.meme.example;

public class Calculator {

  public double add(double num1, double num2) {
    return num1 + num2;
  }

}
```