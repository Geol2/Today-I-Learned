# CAS 알고리즘

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/CS/images/CAS.png" />

1. 인자로 기존 값과 변경할 값을 전달한다.
2. 기존 값이 현재 메모리가 가지고 있는 값과 같다면 변경할 값을 반영하며 `true`를 반환한다.
3. 반대로 기존 값이 현재 메모리가 가지고 있는 값과 다르다면 값을 반영하지 않고 `false`를 반환한다.

멀티 쓰레드, 코어에서는 메인 메모리에서 변수값을 참조하는 것이 아닌 CPU의 캐시 메모리 값을 참조한다.

이 때, 메인 메모리의 값과 CPU 캐시에 저장된 값이 다른 경우가 있다.(가시성 문제)

현재 스레드에 저장된 값과 메인 메모리에 저장된 값을 비교하여 일치하는 경우, 새로운 값으로 교체하고 일치하지 않는다면 실패하고 재시도를 한다고 한다

Atomic-Type인 `AtomicInteger`, `AtomicBoolean` 등의 클래스가 존재하고 

## synchronized의 문제점

synchronized 키워드는 멀티 스레드 환경에서 공유하는 객체를 블로킹(blocking)을 하여 동기화하기 때문에 다른 스레드는 멈추므로 기다리는 상황이 되니 낭비가 심하게 된다

멀티 쓰레드 환경에서 동기화 문제를 별도의 synchronized 키워드 없이 해결하기 위해서 고안된 방법(Non-blocking)으로 Atomic이 있다고 한다

Atomic 연산의 핵심원리는 CAS에 있다고 한다

## Atomic

멀티 스레드 환경에서 연산에 대한 원자성을 보장하기 위해 나온 개념

멀티쓰레드 환경에서 연산 동기화 문제를 synchronized 키워드 없이 동기화 문제를 해결하기 위해 고안된 방법

## Atomic 타입 클래스의 내부구현

```java
AtomicInteger atomicVariable = new AtomicInteger();
```
CAS 를 알아보기 위해 main 에 Atomic 타입의 객체를 만들었다.

```java
public class AtomicInteger extends Number implements java.io.Serializable {
    
    /*
     * This class intended to be implemented using VarHandles, but there
     * are unresolved cyclic startup dependencies.
     */
    private static final jdk.internal.misc.Unsafe U = jdk.internal.misc.Unsafe.getUnsafe();
    private static final long VALUE = U.objectFieldOffset(AtomicInteger.class, "value");

    private volatile int value;
    
    // ...

    public final int incrementAndGet() {
        return U.getAndAddInt(this, VALUE, 1) + 1;
    }
}
```
`incrementAndGet()` 해당 내부에서 CAS 알고리즘을 구현한다고 한다

`value`의 값은 메인 메모리에 할당을 하겠다는 뜻이 된다

```java
public final class Unsafe {
    
    // ...
    
    @HotSpotIntrinsicCandidate
    public final int getAndAddInt(Object o, long offset, int delta) {
        int v;
        do {
            v = getIntVolatile(o, offset);
        } while (!weakCompareAndSetInt(o, offset, v, v + delta));
        return v;
    }

    // ...

    @HotSpotIntrinsicCandidate
    public native int getIntVolatile(Object o, long offset);

    // ...
    
    @HotSpotIntrinsicCandidate
    public final boolean weakCompareAndSetInt(Object o, long offset,
                                              int expected,
                                              int x) {
        return compareAndSetInt(o, offset, expected, x);
    }

    // ...

    @HotSpotIntrinsicCandidate
    public final native boolean compareAndSetInt(Object o, long offset,
                                                 int expected,
                                                 int x);
}
```

`weakCompareAndSetInt` 에서 현재값과 메모리에 저장된 값을 비교해서 동일하다면, 메모리에 변경한 값을 저장하고 true를 반환한다.

따라가보면 native 메소드이므로 더 상세한 구현을 보는 것은 불가능한 것 같다

-----

Atomic : 더 나눌수 없는 행위를 말하거나 최소단위의 명령어, 연산. 데이터베이스에선 트랜잭션은 모두 성공하거나 또는 실패하는 데이터베이스 운용의 집합

[java.util.concurrent.atomic 문서](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/concurrent/atomic/AtomicInteger.html)

[참고자료](https://steady-coding.tistory.com/568)