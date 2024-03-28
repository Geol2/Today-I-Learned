# private 생성자나 열거 타입으로 싱글턴임을 보증해라

싱글턴은 인스턴스를 오직 하나만 생성할 수 있는 클래스

**싱글턴으로 만들면 이를 사용하는 클라이언트는 테스트하기가 힘들다**

싱글턴을 만드는 방식은 보통 둘 중 하나이다.


**필드 방식**

```java
public class Elvis {
  public static final Elvis INSTANCE = new Elvies(); # 해당 클래스가 싱글턴임이 드러남
  private Elvis() {...}

  public void leaveTheBuilding() {...}
}
```

private 생성자는 public static final 필드인 Elvis.INSTANCE를 초기화할 때 딱 한 번만 호출

클라이언트가 손 쓸 방법이 없다

**정적 팩토리 메소드 방식**


```java
public class Elvis {
  private static final Elvis INSTANCE = new Elvis();
  private Elvis() {...}
  public static Elvis getInstance() { return INSTANCE; }

  public void leaveTheBuilding() {...}
}
```
편의에 따라 API를 바꾸지 않고도 싱글턴이 아니게 변경이 됨

정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다는 점 (아이템 30)

정적 팩터리의 메서드 참조를 공급자(Supplier)로 사용할 수 있다는 점. `Elvis::getInstance()` -> `Supplier<Elvis>` (아이템 43, 44)

직렬화 시, 단순히 Serializable 을 구현한다고 선언하는 것으로는 부족하며 모든 인스턴스 필도를 일시적(transient)로 선언하고 readResolve 메서드를 제공해야 한다.(아이템 89) 이렇게 하지 않으면 역직렬화 시 새로운 인스턴스가 생성된다.

당장은 각 아이템들에 대해 이해가 안된다 ㅡㅡ

```java
private Object readResolve() {
  return INSTANCE;
}
```
가짜 Evlis 탄생을 예방하고 싶다면 클래스에 다음의 readResolve 메서드를 추가한다

**원소가 하나인 열거 타입을 선언한다**

```java
public enum Elvis {
  INSTANCE;

  public void leaveTheBuilding() {...}
}
```

대부분 상황에서는 원소가 하나뿐인 열거 타입이 싱글턴을 만드는 가장 좋은 방법이라고 한다