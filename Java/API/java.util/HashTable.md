# HashTable

키-값을 매핑하는 해시 테이블을 구현, null 이 아닌 객체는 키-값으로 사용할 수 있다.

`hashCode()`와 `equals()`를 재정의하는 것은 중요

[HashMap](https://github.com/Geol2/Today-I-Learned/blob/main/Java/API/java.util/HashMap.md)와 기능은 비슷하다.

```java
public class Hashtable<K,V> extends Dictionary<K,V> implements Map<K,V>, Cloneable, Serializable
```

성능에 미치는 두 가지
- 초기용량(initialCapacity)
- 부하율

put(), get(), remove()에 `syncronized`가 있어 쓰레드에 안전하다.

```java
public synchronized V put(K key, V value) {
    // Make sure the value is not null
    if (value == null) {
        throw new NullPointerException();
    }

    // Makes sure the key is not already in the hashtable.
    Entry<?,?> tab[] = table;
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
    @SuppressWarnings("unchecked")
    Entry<K,V> entry = (Entry<K,V>)tab[index];
    for(; entry != null ; entry = entry.next) {
        if ((entry.hash == hash) && entry.key.equals(key)) {
            V old = entry.value;
            entry.value = value;
            return old;
        }
    }

    addEntry(hash, key, value, index);
    return null;
}
```

`Iterator`의 `fail-fast`에 대한 설명이 있는데 `ConcurrentModificationException` 라는 예외에 의존해서 작성하는 것을 금지한다.

|                         | HashMap | HashTable |
|-------------------------|---------|-----------|
| key,value 에 null 허용  | O        | X         |
| Thread-safe             | X       | O         |