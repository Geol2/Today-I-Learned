# 다 쓴 객체 해제하기

자바에는 GC가 메모리 관리를 해주지만, 전부는 아니라고 한다.

```java
public class Stack {

  private Object[] elements;
  private int size = 0;
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  public Stack() {
      elements = new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public void push(Object e) {
      ensureCapacity();
      elements[size++] = e;
  }

  private void ensureCapacity() {
      if (elements.length == size)
          elements = Arrays.copyOf(elements, 2 * size + 1);
  }

  // public Object pop() {
  //     if (size == 0)
  //         throw new EmptyStackException();
  //     return elements[--size];
  // }

  // 코드 7-2 제대로 구현한 pop 메서드 (37쪽)
  public Object pop() {
      if (size == 0)
          throw new EmptyStackException();
      Object result = elements[--size];
      // elements[size] = null; // 다 쓴 참조 해제
      return result;
  }

  public static void main(String[] args) {
      Stack stack = new Stack();
      for (String arg : args)
          stack.push(arg);

      while (true)
          System.err.println(stack.pop());
  }
}
```

스택에서 elements에 오브젝트가 쌓이기만 하고 빠지지 않는다.

스택, 캐시 등은 메모리 누수에 유의해야 한다.

