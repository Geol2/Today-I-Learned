# 함수형 인터페이스

java.util.function 패키지에 일반적으로 자주 쓰이는 형식의 메서드를 함수형 인터페이스로 정의해둠

함수형 인터페이스를 새롭게 정의하는 것보다 이 패키지의 인터페이스를 활용하는 것이 유지보수나 재사용성 측면에서 좋다

## `java.lang.Runnable`

`void run()` : 매개변수도 없고 반환값도 없음

## `Supplier<T>`

T get() : 매개변수는 없고 반환값만 있음

## `Consumer<T>`

`void accept(T t)` : Supplier와 반대로 매개변수만 있고 반환값이 잆음

## `Function<T, R>`

`R apply(T t)` : 일반적인 함수. 하나의 매개변수를 받아서 결과를 반환

## `Predicate<T>`

`boolean test(T t)` : 조건식을 표현하는데 사용되고 매개변수는 하나이고, 반환 타입은 boolean


매개변수로 보통 T 라는 문자를 임의로 많이 사용되고 있고 파라마터가 하나씩 추가될 때마다 U, V, W ... 으로 사용된다

매개변수가 두 개인 함수형 인터페이스는 `BiConsumer<T, U>`, `BiPredicate<T, U>`, `BiFunction<T, U, R>`

매개변수가 세 개 이상인 함수형 인터페이스는 직접 만들어서 써야한다

