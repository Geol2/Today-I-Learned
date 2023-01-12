# Optional

```java
public final class Optional<T> extends Object
```

옵셔널은 null을 반환하면 오류가 발생할 가능성이 높은 경우(NullPointerException)에 '결과없음'을 드러내기 위해 반환타입으로 사용되도록 제한적인 경우로 설계함

남발 시, 다음과 같은 문제가 생길 수 있다.

- `NullPointerException` 대신 `NoSuchElementException` 가 발생함
- 시간적, 공간적 비용(또는 오버헤드)이 증가함

Optional은 객체를 감싸는 객체이므로 기존 객체를 더해 추가적인 Optional 객체를 저장하기 위해 메모리를 더 할당하게 된다.

또한, 추가적인 Optional 객체에 접근해야 하므로 접근 비용이 증가한다.

값이 없는 경우엔 `Optional.Empty()` 로 초기화하는 것이 좋다.


## orElse와 orElseGet

- `orElse(T other)`
  - 존재하면 값을 반환하고 그렇지 않으면 `other`를 반환함
  - `other`의 값이 없으면 null 값 일 수 있다

- `orElseGet(Supplier<? extends T> other)`
  - 존재하는 경우 값을 반환하고, 그렇지 않으면 `other.get()`를 반환한다.
  - `other`의 값이 없으면 Supplier의 결과가 반환된다.
