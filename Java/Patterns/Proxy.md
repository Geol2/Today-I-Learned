# Proxy

기존 코드에 추가하지 않고 새 기능을 추가하기

```java
class Cash implements Payment {
     @Override
    public void pay(int amount) {
        System.out.println(amount + " 현금 결제");
    }
}

class CashPerf implements Payment {
    Payment payment = new Cash();

    @Override
    public void pay(int amount) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        cash.pay(amount);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}

interface Payment {
    
    void pay(int amount);

}

class Store {
    Payment payment;

    public Store(Payment payment) {
        this.payment = payment;
    }

    public void buySometing(int amount) {
        payment.pay(amount);
    }
}

///////////////////////

// 테스트
class StoreTest {
    @Test
    public void testPay() {
        //Payment cash = new Cash(); // 성능 측정이 되지 않음
        Payment cashPerf = new CashPerf(); // 성능 측정이 됨
        Store store = new Store(cashPerf);
        store.buySomething(100);
    }
}
```

위의 코드를 UML과 시퀀스로 표현하면 다음과 같다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Pattern/Proxy-1.png" />

## 정의
```
제어의 흐름을 조절하기 위해 중간자를 둔다
== 특정 객체로의 접근을 제어하는 대리인 객체를 제공한다
```

## 사례

결제된 가격을 표시하는 코드에서 성능을 측정하고자 할 때, 결제된 가격을 표시하는 코드에서 측정하는 코드를 추가하지 않고 새로운 코드에서 추가하고자 한다

간단하게 정리하면,
- 기존 코드 : 결제된 가격
- 새로운 코드 : 기존 코드 + 성능 측정

기존의 결제된 가격만 표시하지 않고 추가로 성능을 측정하고자 할 때, 기존 코드에서 추가하지 않고 성능을 측정하고 싶을 때 사용할 수 있다

클라이언트 코드가 기존 코드에서 추가/확장 되지 않고 새로운 코드로 접근하는 방식을 변경하는 패턴이라고 볼 수 있다