# 생성자 대신 정적 팩터리 메서드를 고려하기

클라이언트에서 클래스의 인스턴스를 얻는 전통적인 수단은 public 생성자가 있지만, 정적 팩터리 메서드를 추가적으로 제공할 수 있다.

디자인 패턴에서의 팩터리 메서드나 추상 팩토리 패턴과는 다른 것이다라고 한다.

```java
class Person {

  public String name;

  public int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
}
```

생성자의 파라미터들이 정확히 어떤일을 하는지 알기가 힘들다. 사실 어렵지 않은 클래스들은 쉽게 유추할 수 있지만, 정확히 명시해줘야할 클래스가 생길 수도 있다.

그럴 때 정적 팩터리 메서드를 고려하자. `BigInteger(int, int, Random)` 과 `BigInteger.probablePrime` 중에서 어떤 것이 **값이 소수**인 `BigInterger`를 반환한다고 유추하기 쉬울지 보면 된다.

생성자 방식과 정적 팩터리 메서드 방식 중에서 어떤 것을 사용하는 것이 적절한지는 그때그때마다 다르지만 일반적으로 정적 팩토리 메서드로 만드는 것이 유리하다고 한다.

```java
class PersonStatic {
  public static String name;

  public static int age;

  public PersonStatic(String name) {
    PersonStatic.name = name;
  }

  public PersonStatic(int age) {
    PersonStatic.age = age;
  }

  public static PersonStatic createName(String name) {
    return new PersonStatic(name);
  }

  public static PersonStatic createAge(int age) {
    return new PersonStatic(age);
  }
}

public class Item01 {
  public static void main(String[] args) {
    PersonStatic.createAge(20);
    PersonStatic.createName("백!");

    System.out.println(PersonStatic.age);
    System.out.println(PersonStatic.name);
    
  }
}
```

위 코드를 보면 PersonStatic 클래스에서 정확히 무엇을 하고 있는지 명시해주고 있으므로 이름을 가질 수 있는 장점이 있다.

또한, new 키워드 없이 인스턴스를 생성할 필요가 없다.

```java
interface Person {
  public String hello(); 
}

class PersonStatic implements Person {
  public String hello() {
    return "난 일반 사람이야!";
  }
}

class PolicePersonStatic implements Person {
  public String hello() {
    return "난 경찰이야!";
  }
}

class PersonFactory {
  public static Person of(String kind) {
    if(kind.equals("person")) {
      return new PersonStatic();
    } else if(kind.equals("police")) {
      return new PolicePersonStatic();
    }
    return null;
  }
}

public class Item01 {
  public static void main(String[] args) {
    Person person = PersonFactory.of("person");
    System.out.println(person.hello()); // 난 일반 사람이야!
  }
}
```

반환 타입의 하위 타입 객체를 반환할 수 있는 능력이 있으므로 많은 유연성이 존재하고

입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.

다섯 번 째 장점은 아직 이해가 잘 가지 않아서 적질 못하겠다...ㅠㅠㅠㅠ

단점으로는 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다는 단점이 있다.

또한, 프로그래머가 정적 팩터리 메서드를 찾기가 힘들어서 API 문서를 찾아보던가 잘 알려진 규칙에 맞춰 작성하는 식으로 완화가 필요하다고 한다.

from, of, valueOf, instance, create, getType, new Type, type 등등 쓰인다고 한다.