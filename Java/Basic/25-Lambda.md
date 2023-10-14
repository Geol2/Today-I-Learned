# Lambda

메서드를 하나의 식으로 표현한 것으로 익명 함수라고도 불린다

```java
int[] arr = new int[5];
Arrays.setAll(arr, (i) => (int)(Math.random() * 5 + 1));
```

위에서 `(i) => (int)(Math.random() * 5) + 1` 이 람다식에 해당된다

## 람다식을 작성하는 방법

```
반환타입 메서드이름(매개변수 선언) {
    문장들
}
```
위의 메서드 형태를 아래처럼 바꿔줄 수 있고 항상 타입추론이 가능해진다.

매개변수가 하나뿐인 경우는 `()`를 생략할 수 있고 하나뿐인 문장일 땐, `{}`를 생략할 수 있다

```java
(매개변수 선언) -> {
    문장들
}
```


## 함수형 인터페이스

단 하나의 추상 메서드만 선언된 인터페이스

람다식을 다루기 위한 참조타입은 함수형 인터페이스로 한다

### 선언 방법
```java
@FunctionalInterface # 컴파일러에게 함수형 인터페이스임을 나타내줌
interface MyFunction {
  public void abstract myMethod();
}
```


```java
// 람다식
타입 f = (int a, int b) -> a > b ? a : b;

// 익명 클래스
MyFunction f = new MyFunction() {
    public int max(int a, int b) {
        return a > b? a : b;
    }
}
int big = f.max(5, 3);



// 람다식
List<String> list = Arrays.asList("abc", "aaa", "bbb", "ddd", "aaa");

// 익명 클래스
Collections.sort(list, new Comparator<String>() {
    public int compare(String s1, String s2) {
        return s2.compareTo(s1);
    }
});
```

### 람다식의 타입과 형변환

람다식은 정확히 타입은 있지만, 컴파일이 정해주는 것이라 타입을 정해주므로 정확히 알 수 없다.

```java
MyFunction f = (MyFunction) (() ->()); // 양변의 타입이 다르므로 형변환이 필요

Object obj = (Object)(()->()); // Object형 타입으론 변환할 수 없다
Object obj = (Object)(MyFunction(()->()));
```


## 람다 예제

```java
List<Product> product = new ArrayList<>();
product.add(new Product(100L, "name1", "100"));
product.add(new Product(200L, "name2", "200"));
product.add(new Product(300L, "name3", "300"));

// product 객체에 있는 상품번호를 prdNos에 담아보세요. for문 사용해서
List<Long> prdNos = new ArrayList<>();
/**
 * for(int i = 0; i < product.size(); i++) {
 *    prdNos.add(product.get(i).prdNo);
 *  } 
 **/

// 람다로 변환
prdNos = product.stream()
    .map(x -> x.prdNo)
    .collect(Collectors.toList());
System.out.println(prdNos);


//상품가격이 200이상인 경우의 리스트만 담기
List<Product> productOver_2000Won = new ArrayList<>();
// for 문으로 일단 작성
/** 
 * for(int i = 0; i < product.size(); i++) {
 *    if(Integer.parseInt(product.get(i).prdPrice) >= 200) {
 *        productOver_2000Won.add(product.get(i));
 *    }
 * } 
 **/

// 람다로 변환
productOver_2000Won = product.stream()
    .filter(x -> Integer.parseInt(x.prdPrice) >= 200)
    .collect(Collectors.toList());

System.out.println(productOver_2000Won.size());
```