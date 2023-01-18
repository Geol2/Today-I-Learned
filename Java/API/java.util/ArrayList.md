# ArrayList

```java
public class ArrayList<E>
extends AbstractList<E>
implements List<E>, RandomAccess, Cloneable, Serializable
```

```java
private static final int DEFAULT_CAPACITY = 10;

private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
```

기본 크기는 10을 가지고 있다

최대 크기는 Integer.MAX_VALUE - 8 이다

null 을 허용한다

동기화를 허용하지 않는다

동기화 시, `Collections.synchronizedList()`를 사용해서 래팽해야 한다

Collections 의 종류 중 하나이다

| 리턴 타입 | 메소드 이름 및 매개 변수                       | 설명                                                                   |
|----------|----------------------------------------------|------------------------------------------------------------------------|
| boolean  | add(E e)                                     | 매개 변수로 넘어온 데이터를 가장 끝에 담는다. |
| boolean  | addAll(Collection)                           | 매개 변수로 넘어온 데이터를 지정된 index 위치에 담는다. |
| void     | addAll(Collection<? extends E> c)            | 매개 변수로 넘어온 컬렉션 데이터를 가장 긑에 담는다. |
| boolean  | addAll(int index, Collection<? extends E> c) | 매개 변수로 넘어온 컬렉션 데이터를 index에 지정된 위치부터 담는다. |
| int      | size()                                       | ArrayList 객체에 있는 데이터의 개수를 리턴한다. |
| E         | get(int index)                              | 매개 변수에 지정한 위치에 있는 데이터를 리턴한다. |
| int       | indexOf(Object o)                           | 매개 변수로 넘어온 객체와 동일한 데이터의 위치를 리턴한다. |
| int       | lastIndexOf(Object o)                       | 매개 변수로 넘어온 객체와 동일한 마지막 데이터의 위치를 리턴한다. |
| Object[]  | toArray()                                   | ArrayList 객체에 있는 값들을 Object[] 타입의 배열로 만든다. |
| <T> T[]   | toArray(T[] a)                              | ArrayList 객체에 있는 값들을 매개 변수로 넘어온 T 타입의 배열로 만든다. |
| void      | clear                                       | 모든 데이터를 삭제한다. |
| E         | remove(int index)                           | 매개 변수에서 지정한 위치에 있는 데이터를 삭제하고, 삭제한 데이터를 리턴한다. |
| boolean   | remove(Object o)                            | 매개 변수에 넘어온 객체와 동일한 첫 번째 데이터를 삭제한다. |
| boolean   | removeAll(Collection<?> c)                  | 매개 변수로 넘어온 컬렉션 객체에 있는 데이터와 동일한 모든 데이터를 삭제한다. |
| E         | set(int index, E element)                   | 지정한 위치에 있는 데이터를 두번째 매개변수로 넘긴 값으로 변경한다. 그리고, 해당 위치에 있던 데이터를 리턴한다. |

## 배열과의 차이점

ArrayList는 가변적으로 할당해주지만, 배열은 고정적으로 미리 할당해줘야 함 

## 링크드리스트와의 차이점

ArrayList는 연속적인 저장공간에 할당되지만, LinkedList는 불연속적인 저장공간에 할당됨

ArrayList는 삽입 및 삭제 시, 사이즈를 늘려주는 연산과 빈 인덱스를 채우는 연산이 필요하지만, LinkedList는 별도의 연산이 필요없어 삽입/삭제가 많이 일어나는 연산에 용이하다

ArrayList는 무작위 접근이 가능하지만, LinkedList는 순차접근만 가능하다