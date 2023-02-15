# Template Method

```
템플릿 메소드를 수행할 때, 하위 클래스에서 오버라이딩한 추상/훅 메소드를 호출

템플릿 메소드에서 호출하는 추상메소드는 하위 클래스가 반드시 오버라이딩 해야함

템플릿 메소드에서 호출하는 훅 메소드는 하위 클래스가 선택적 오버라이딩 해야함

상위 클래스의 템플릿 메소드에서 하위 클래스가 오버라이딩한 메소드를 호출하는 패턴
```

## 훅?

- 추상 클래스에 선언되어 있으며, 기본적인 내용만 구현되어있거나 아무 코드도 들어있지 않은 메소드

```java
class Dog {
    public void payWithOwner() {
        System.out.println("귀염둥이야");
        System.out.println("멍! 멍!");
        System.out.println("꼬리 살랑살랑");
        System.out.println("잘했어!");
    }
}

class Cat {
    public void payWithOwner() {
        System.out.println("귀염둥이야");
        System.out.println("야옹! 야옹!");
        System.out.println("꼬리 살랑살랑");
        System.out.println("잘했어!");
    }
}
```

`payWithOwner()` 내에서 둘째 줄을 빼고는 동일하다

```java
abstract class Animal {
    // 템플릿 메소드
    public void playWithOwner() {
        System.out.println("귀염둥이야");
        play();
        runSomething();
        System.out.println("잘했어");
    }

    // 추상 메소드
    abstract void play();

    // 훅 메소드
    void runSomething() {
        System.out.prinln("꼬리 살랑살랑");
    }
}

class Dog extends Animal {
    @Override
    // 추상 메소드 오버라이딩
    void play() {
        System.out.println("멍! 멍!");
    }

    @Override
    // 훅 메소드 오버라이딩
    void runSomething() {
        System.out.println("꼬리 살랑살랑");
    }
}

class Cat extends Animal {
    @Override
    // 추상 메소드 오버라이딩
    void play() {
        System.out.println("야옹! 야옹!");
    }

    @Override
    // 훅 메소드 오버라이딩
    void runSomething() {
        System.out.println("꼬리 살랑살랑");
    }
}

public class Driver {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        dog.playWithOwner();

        System.out.println();
        System.out.println();

        cat.playWithOwner();
    }
}
```

```
귀염둥이야
멍! 멍!
꼬리 살랑살랑
잘했어


귀염둥이야
야옹! 야옹!
꼬리 살랑살랑
잘했어
```
