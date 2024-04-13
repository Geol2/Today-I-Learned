# 자원을 직접 명시하지 말고 의존 객체 주입을 사용해라

```java
class SpellChecker {
  private static final Lexicon dictionary = ''; // ...

  private SpellChecker() {} // 객체 생성 방지

  public static boolean isValid(String word) { 
    // ...
  }

  public static List<String> suggestions(String typo) {
    // ...
  }
}
```

위 방식은 정적 메서드 방식으로 클래스를 구현

```java
class SpellChecker {
  priavte final Lexicon doctionary = ''; //...

  private SpellChecker(
    //...
  ) {}
  public static SpellChecker INSTANCE = new SpellChecker(
    // ...
  );

  public boolean isValid(String word) {
    // ...
  }
  public List<String> suggestions(String typo) {
    // ...
  }
}
```

위 방식은 구싱글턴 방식으로 클래스를 구현

둘 다 테스트가 용이하지 않고 유연하지 않고 동작이 달라지는 클래스에는 적합하지 않다.

```java
public class SpellChecker {

  private final Lexicon dictionary;

  public SpellChecker(Lexicon dictionary) {
    this.dictionary = Objects.requireNinNull(dictionary);
  }

  public boolean isValid(String word) {
    // ...
  }

  public List<String> suggestions(String typo) {
    // ...
  }
}
```

위 방식은 **의존 객체 주입 패턴**이다. 

의존성이 너무 많은 프로젝트에서는 코드를 어지럽게 만들기도하지만 스프링 같은 프레임워크는 이런 것들을 해소시켜준다.

클래스가 내부적으로 하나 이상의 자원에 의존하고, 그 지원이 클래스 동작에 영향을 준다면 싱글턴과 정적 메서드는 사용하지 않는 것이 좋다.