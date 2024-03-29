# HashMap

키에 대한 해시 값을 사용하여 값을 저장하고 조회하며, 키-값 쌍의 개수에 따라 동적으로 크기가 증가하는 연관 배열

## HashMap 은 어떻게 구현되어있길래 키와 값을 빠르게 매핑할 수 있을까?

원하는 키를 해싱를 이용해서 데이터가 저장될 위치의 인덱스를 구하기 때문에 최적 `O(1)`로 빠르게 사용할 수 있다.

`put()` 호출을 하면 `hashCode()`를 이용해 결정된 버킷에 저장된 후, 버킷 내에 `equals()` 여부를 판단하게 되므로 `hashCode()`와 `equals()`를 재정의하는 것은 중요하다.

```java
public class HashMap<K,V> extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable
```

Map, Cloneable, Serializable 인터페이스를 구현하고 Collections 중 하나이고 null 값과 키를 허용한다

- 성능에 미치는 두 가지
  - 초기용량(initialCapacity) : 해시 테이블의 버킷 수(기본 16개), 초기용량은 해시 테이블이 생성된 시점
  - 부하율 : 기본 부하 계수(기본 75%)와 공간 비용으로 적절하게 할당됨, 값이 클수록 공간 오버헤드는 줄어들지만 조회 비용은 증가함

```java
// 해시맵 내부
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 16
static final float DEFAULT_LOAD_FACTOR = 0.75f; // 75%
```

```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}

final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```

동일한 HashCode()로 많은 키를 사용하는 것은 해시 테이블의 성능을 저하시킨다

`synchronized` 되지 않으며(Not Thread-safe), 여러 스레드가 동시에 접근하고 수정하는 경우 외부에서 해주어야 한다

`Map m = Collections.synchronizedMap(new HashMap(...));`

또한, `ConcurrentHashMap`을 사용해서 스레드에 안전하게도 만들 수 있다.

`Iterator`의 `fail-fast`에 대한 설명이 있는데 `ConcurrentModificationException` 라는 예외에 의존해서 작성하는 것을 금지한다.

## 메소드

자주 쓰일 것 같은 메소드들만 추렸다.

- size() : int
- isEmpty() : 없을 시 true
- containsKey(Object key) : 있다면 true, 없다면 false
- containsValue(Object value) : 있다면 true, 없다면 false
- get(Object key) : null | 지정된 키가 매핑되는 값
- put(K key, V value) : null | 키와 관련된 이전 값
- replace(K key, V oldValue, V newValue) : 교체되었다면 true
- remove(Object key) : null | 키와 관련된 이전 값
- clear()

더 자세한 건 API 문서에 있다.

-----
- [Oracle 문서 - HashMap](https://docs.oracle.com/javase/8/docs/api/index.html?java/util/HashMap.html)
- [HashMap vs HashTable 블로그 작성](https://geol2.github.io/til/HashMap_HashTable/)
- 로드 팩터(load factor) : 해시 테이블이 얼마나 가득찰 수 있는지를 측정한 것, 가득 차 있다면 용량이 자동으로 증가함
- 오버헤드(overhead) : 어떤 처리를 하기 위해 들어가는 간접적인 처리 시간 · 메모리