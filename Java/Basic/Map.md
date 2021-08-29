# Map
- 인터페이스
-----

## MapHash
- Map 인터페이스의 구현 클래스
-----

- 키, 값인 pair관계를 가지는 형태인 자료구조

- 예제
```java
import java.util.HashMap;

public class MapGetOrDefaultEx { 
  public static void main(String arg[]) { 
    Map<String, Integer> map = new HashMap<>();
    map.put("One", 1);
    map.put("Two", 2);
    map.put("Three", 3);
    System.out.println(map);
  }
```

```java
import java.util.HashMap;

public class MapGetOrDefaultEx { 
  public static void main(String arg[]) { 
    String [] alphabet = { "A", "B", "C" ,"A"};
    HashMap<String, Integer> hm = new HashMap<>();
    for(String key : alphabet) {
      hm.put(key, hm.getOrDefault(key, 0) + 1);
    }
    System.out.println("결과 : " + hm); // 결과 : {A=2, B=1, C=1} } 
  }
```
