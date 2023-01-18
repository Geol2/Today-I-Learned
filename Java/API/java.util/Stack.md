# Stack

```java
class Stack 
extends Vector<E>
implements Serializable, Cloneable, Iterable<E>, Collection<E>, List<E>, RandomAccess
```

스택 자료구조, LIFO의 형태이지만 Vector 클래스를 상속받고 있어서 중간에 데이터를 삽입, 삭제가 되는 문제를 가지고 있다

Stack 클래스는 synchronized 키워드가 붙어있기 때문에 Thread-safe 하다는 특징

```java
import java.util.Stack;

public class Test {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        System.out.println(stack.get(1));            // 해당 인덱스 원소 찾기
        System.out.println(stack.set(1, 1));         // 해당 인덱스에 원소 넣기
        System.out.println(stack.remove(1));         // 해당 인덱스 원소를 삭제
    }
}
```

## 메소드

| type    |     Method       | description                 |
|---------|------------------|-----------------------------|
| boolean | empty()          | 비어있는지 확인              |
|    E    | peek()           | 스택의 끝을 제거없이 선택한다 |
|    E    | pop()            | 스택의 끝을 꺼내고 리턴한다   |
|    E    | push(E item)     | 스택에 요소를 추가한다       |
|   int   | search(Object o) | 스택 객체의 위치를 반환한다  |