# FactoryMethod

```
오버라이드된 메소드가 객체를 반환하는 패턴
```

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Pattern/FactoryMethod-1.png?raw=true" />

```java

abstract class Animal {
    abstract AnimalToy getToy();
}

abstract class AnimalToy {
    abstract void identify();
}

class Dog extends Animal {
    @Override
    AnimalToy getToy() {
        return new DogToy();
    }
}

class Cat extends Animal {
    @override
    AnimalToy getToy() {
        return new CatToy();
    }
}

class DogToy extends AnimalToy {
    public void identify() {
        System.out.println("나는 테니스공! 강아지의 친구");
    }
}

class CatToy extends AnimalToy {
    @Override
    public void identify() {
        System.out.println("나는 캣타워! 고양이의 친구");
    }
}

public class Main {
    public static void main(String[] args) {
        Animal bolt = new Dog();
        Animal kitty = new Cat();

        AnimalToy boltBall = bolt.getToy();
        AnimalToy kittyTower = kitty.getToy();

        boltBall.identify();
        kittyTower.identify();
    }
}
```