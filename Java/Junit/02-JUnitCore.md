# JUnit

## 테스트 메소드가 되기 위한 조건

- @Test 어노테이션 여부
- public 접근 제어자
- 파라미터를 받지 않는다
- 반환형은 `void` 이다

### 테스트 (Test)

@Test 어노테이션이 부여된 메소드로 한 개의 테스트를 뜻한다

### 테스트 클래스 (TestClass)

@Test가 부여된 테스트를 하나 이상 포함한 클래스

### 테스트 슈트 (Test suite)

테스트들의 집합, 관련된 테스트들을 함께 묶는 편리한 수단

### 러너 (Runner)

테스트 슈트를 실행하는 엔진으로 다양한 러너가 존재한다

### 어설트 (Assert)

테스트하려는 조건을 명시한다. 조건만 만족되면 지나가고, 만족하지 못하면 예외를 던진다.

## 파라미터화 테스트

파라미터화 테스트 러너는 하나의 테스트를 여러 번 반복 실행하는 기능을 제공한다

이 뜻은 뜩같은 테스트를 반복하는 의미가 아니라, 테스트에 사용되는 데이터들을 뽑아내서 매번 바꿔가면서 테스트를 호출하는 말이 된다.

아래 코드에서 `getTestParameters()` 와 같이 매번 테스트 데이터들을 바꿔가면서 테스트를 실행한다.

```java
@RunWith(value=Parameterized.class) // 1
public class ParameterizedTest {

    private double expected;
    private double valueOne;
    private double valueTwo; // 2

    @Parameters
    public static Collection<Integer[]> getTestParameters() { // 3
        return Arrays.asList(new Integer[][] {
            {2, 1, 1}, // 예상값, 값1, 값2
            {3, 2, 1}, // 예상값, 값1, 값2
            {4, 3, 1}  // 예상값, 값1, 값2
        });
    }

    public ParameterizedTest(double expected, 
                             double valueOne,
                             double valueTwo) { // 4
        this.expected = expected;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo
    }

    @Test
    public void sum() { // 5
        Calculator calc = new Calculator(); // 6
        assertEquals(expected, calc.add(valueOne, valueTwo), 0); // 7
    }
}
```

파라미터화 테스트 러너
1. @Runwith 어노테이션 부여
2. Parameterized 클래스를 사용
3. `getTestParameters()` 메소드와 같은 반복할 테스트 데이터가 필요하다, 
4. 파라미터를 받는 생성자가 사용된다
5. 테스트 메소드를 구현
6. 객체를 생성
7. 제공된 파라미터들을 사용해 Array 컬렉션의 크기만큼 인스턴스를 생성하고 결과를 나타내준다

```java
sum : assertEquals(2, calculator.add(1, 1), 0);
sum : assertEquals(3, calculator.add(2, 1), 0);
sum : assertEquals(4, calculator.add(1, 1), 0);
```

## JUnit Core

개발 &rightarrow; 수행 &rightarrow; 테스트 &rightarrow; 개발

테스트 &rightarrow; 개발 &rightarrow; 수행 &rightarrow; 테스트

어떠한 경우든 테스트를 넣도록 만들도록 하는 것이 좋지만 JUnit 별로 러너를 잘 지정해서 사용하도록 한다

### JUnitCore 퍼사드

퍼사드

### 테스트 슈트를 이용한 테스트 조직하기

```java
public class TestCaseA {
    @Test
    public void testA1() {
        // ...
    }
}

public class TestCaseB {
    @Test
    public void testB1() {
        // ...
    }
}

@Runwith(value=Suite.class)
@SuiteClasses(value = {TestCaseA.class})
public class TestSuiteA {}

@Runwith(value=Suite.class)
@SuiteClasses(value = {TestCaseB.class})
public class TestSuiteB {}

@Runwith(value=Suite.class)
@SuiteClasses(value = {TestCaseA.class, TestCaseB.class})
public class MasterTestSuite {} // 주목 !
```

### 테스트 슈트 vs IDE, Ant, Maven

슈트를 구성하지 않고 Maven, IDE, Ant 등을 이용해서 테스트 클래스와 슈트를 묶을수도 있다. 구성하기 나름이니까 트레이트 오프를 고려해서 잘 구성하는 것이 중요하다