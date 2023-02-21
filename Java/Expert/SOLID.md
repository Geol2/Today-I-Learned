# SOLID

## SRP (단일 책임 원칙 - Single Responsibility Principle)

```
어떤 클래스를 변경해야 하는 이유는 오직 하나뿐이어야 한다
```

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/solid-SRP-1.png" />

남자의 역할과 책임이 너무 많아서 단일 책임 원칙으로 변경시켜보자

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/solid-SRP-2.png" />

하나의 클래스를 네 개로 쪼개게 되면서 단일 책임 원칙을 지키고 있다

```java
class 사람 {
    String 군번;
    // ...
}

사람 로미오 = new 사람();
사람 줄리엣 = new 사람();

줄리엣.군번 = "1573042009"; // 잘못함..
```

```java
class 강아지 {
    final static Boolean 수컷 = true;
    final static Bollean 암컷 = false;
    Boolean 성별;

    void 소변보다() { // 메서드가 SRP를 지키지 않음
        if(this.셩별 == 수컷) {

        } else {

        }
    }
}
```

## OCP (개방 폐쇄 원칙 - Open Closed Principle)<sup>*

```
소프트웨어 엔티티는 확장에 대해서는 열려 있어야 하지만 변경에 대해서는 닫혀 있어야 한다

== 자신의 확장에 대해선 열려있고 주변의 변화에 대해서는 닫혀있어야 한다
```

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/solid-OCP-1.png" />

위의 그림에서 소나타가 추가되었을 때, 소나타를 사용할지 마티즈를 사용할지 선택도 해야되고 메서드 별로도 상황이 달라서 운전자가 운전에 대해 영향을 받아야만 한다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/solid-OCP-2.png" />

자동차의 입장에서 자신의 확장은 열려있고 운전자의 입장에서는 변화에 폐쇄되어 있게 하는 것이 좋다

## LSP (리스코프 치환 법칙 - Liskov Subtition Principle)

```
서브 타입은 언제나 자신의 기반 타입으로 교체할 수 있어야 한다
```

is a kind of (클래스) : 하위 분류는 상위 분류의 한 종류

is able to (인터페이스)

## ISP (인터페이스 분리 법칙 - Interface Segregation Principle)

```
클라이언트는 자신이 사용하지 않는 메서드에 의존 관계를 맺으면 안된다
```

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/solid-ISP-2.png" />

인터페이스 분할 원칙을 이야기할 때, 인터페이스를 최소주의 원칙이있는데 인터페이스를 통해 메서드를 외부에 제공할 땐 최소한의 메서드만 제공하라는 뜻이다

상위 클래스는 풍족할수록 좋고 인터페이스는 작을수록 좋다고 한다

그래서 하위 객체가 상위 객체인 척 할 수 있다

## DIP (의존 역전 원칙 - Dependency Inversion Principle)<sup>*

```
고차원 모듈은 저차원 모듈에 의존하면 안된다. 이 두 모듈 모두 다른 추상화된 것에 의존해야 한다.

추상화된 것은 구체적인 것에 의존하면 안된다. 구체적인 것이 추상화된 것에 의존해야 한다.

자주 변경되는 구체(Concrete) 클래스에 의존하지 마라

== 자신보다 변하기 쉬운 것에 의존하지 마라
```

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/solid-DIP-1.png" />

자동차가 스노우타이어(자주 변경되는 객체라고 가정)에 직접 의존하기보단 위처럼 인터페이스에만 의존하게 해서 변경되어도 영향을 받지 않는 형태로 구성하는 것이 좋다