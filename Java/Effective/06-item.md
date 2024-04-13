# 불필요한 객체 생성을 피해라

```java
String s = new String("bikini"); // 1. 이런 식으로 사용하지 말자.

String s = "bikini"; // 2. 이와 같이 사용한다.
```

1. 반복문이나 빈번하게 호출되는 메서드 안에 있다면 String 인스턴스가 수백만개 만들어지는 단점

2. 하나의 String 인스턴스를 반복해서 사용하므로 객체의 재사용이 보장

아니면 정적 팩터리를 이용해서 불필요한 객체의 사용을 피할 수 있다

```java
static boolean isRomanNumeral(String s) {
  return s.matches("^(?.)M*(C[MD]|D?C{0,3})" + 
    "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}
```
위의 방법은 성능이 중요한 상황에서 반복해서 사용하기엔 적합하지 않다. 이유는 한 번 쓰고 쓰고 버려지므로 가비지 컬렉션 대상이 된다.


```java
class RomanNumerals {
  private static final Pattern ROMAN = Pattern.compile(
    "^(?=.)M*(C[MD]|D?C{0,3})"
      + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

  static boolean isRomanNumeral(String s) {
    return ROMAN.matcher(s).matches();
  }
}
```

이런 식으로 값비싼 객체를 만들어두고 직접 생성해 캐싱해두면 나중에 매서드가 호출될 때마다 인스턴스를 재사용한다.

위 시간 차이는 내 컴퓨터에서 230000~400000(nano)와 8000~120000(nano)의 시간차이가 존재했다.

```java
class RomanNumerals {
  private static final Pattern ROMAN = Pattern.compile(
    "^(?=.)M*(C[MD]|D?C{0,3})"
      + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

  static boolean isRomanNumeral(String s) {
    return ROMAN.matcher(s).matches();
  }
}

public class Test02 {
  public static void main(String[] args) {
    long beforeTime = System.nanoTime();

   RomanNumerals.isRomanNumeral("skdjf3kj2khkfjdhkjhdfjhdjfkh3");
    // isRomanNumeral("skdjf3kj2khkfjdhkjhdfjhdjfkh3");

    long afterTime = System.nanoTime();
    long diffTime = afterTime - beforeTime; // 두 개의 실행 시간

    System.out.println("실행 시간(nano): " + diffTime);
  }
}
```