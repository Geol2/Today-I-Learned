# Math.*
- 수학에 필요한 클래스.

## floor
- 버림

## ceil
- 올림

## round
- 반올림

```java
System.out.println(Math.ceil(10.0));      // 10.0
System.out.println(Math.ceil(10.1));      // 11.0
System.out.println(Math.ceil(10.000001)); // 11.0

System.out.println(Math.floor(10.0));     // 10.0
System.out.println(Math.floor(10.9));     // 10.0

System.out.println(Math.round(10.0));     // 10
System.out.println(Math.round(10.4));     // 10
System.out.println(Math.round(10.5));     // 11
```

## pow
- 제곱 연산을 수행하고 double형을 띤다.
- Math.pow(x, y) -> x^y 을 수행한다.
```java
System.out.println((int)Math.pow(5, 2)); // 25
System.out.println((int)Math.sqrt(25));  // 5
```

- ...추가 예정