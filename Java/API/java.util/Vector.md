# Vector

Collection 프레임워크에 해당되며 ArrayList와 동일한 구조를 띄고 있다

가변길이를 지원하지만, ArrayList와 비교해서 레거시에 분류되고 있다

항상 동기화 메소드를 사용하는 특징이 있어서 멀티 스레드 환경에서도 안정성은 좋지만, 성능이 떨어져서 ArrayList 보다 느리다고 한다

```java
Vector<Integer> v = new Vector<>(7);
v.add(3); // 값 추가
v.add(null); // null값도 add가능
v.add(1,10); // index 1뒤에 10 삽입
```

