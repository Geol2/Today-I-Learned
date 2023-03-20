# 싱글톤 컨테이너

```java
@Configuration
public class AppConfig {

  @Bean
  public FixDiscountPolicy getDiscountPolicy() {
    return new FixDiscountPolicy();
  }
  @Bean
  public MemoryMemberRepository getMemberRepository() {
    return new MemoryMemberRepository();
  }
  @Bean
  public MemberService memberService() {
    return new MemberServiceImpl(getMemberRepository());
  }
  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
  }
}
```

처음에 생성한 순수 DI 컨테이너인 AppConfig는 요청할 때마다 새로운 객체를 생성한다

트래픽이 초당 100이면 초당 100개 객체가 생성하고 소멸되므로 메모리 낭비가 심해짐으로 1개만 생성해서 공유하도록 한다

# 싱글톤 패턴

객체가 딱 1개만 생성되는 패턴이다

2개 이상 생성되지 않게 하면 된다

```java
public class SingletonServiceTest {

  private static final SingletonServiceTest instance = new SingletonServiceTest(); // 1.

  public static SingletonServiceTest getInstance() {
    return instance; // 2.
  }

  private SingletonServiceTest() {

  }

  public void logic() {
    System.out.println("싱글톤 객체 로직 호출");
  }

}
```

- 싱글톤 패턴을 구현하는 다양한 방법이 있다

## 문제점

- DIP 위반
- 테스트하기 어렵다
- 클라이언트가 구체 클래스에 의존 
- OCP 위반할 가능성이 높다
- 안티패턴으로 불리기도 한다
- 내부 속성을 변경하거나 초기화하기 어렵다

# 싱글톤 컨테이너

싱글톤 패턴의 문제점을 해결하면서 객체를 싱글톤으로 관리할 수 있다

```java
// 상태가 있는 클래스
public class StatefulService {

  private int price;

  public int order(String name, int price) {
    System.out.println("name = " + name + ", price = " + price);
    this.price = price;
  }

  public int getPrice() {
    return this.price;
  }
}

public class SingletonTest {
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println(memberService1);
        System.out.println(memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
```

요청이 들어올 때마다 객체를 생성하는 것이 아니라 이미 만들어진 객체를 공유해서 효율적으로 재사용할 수 있다

스프링의 빈 등록 방식은 기본적으로 싱글톤이지만, 빈 스코프라고 요청이 들어올 때마다 새로운 객체를 생성해서 반환하는 기능도 제공한다

## 싱글톤, 싱글톤 컨테이너 방식의 주의점

싱글톤 객체는 무상태로 설계하도록 한다

1. 특정 클라이언트에 의존적인 필드가 있으면 안된다
2. 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다
3. 가급적 읽기만 가능해야 한다
4. 필드 대신에 지역변수, 파라미터, ThreadLocal 처럼 공유되지 않는 것을 사용한다

```java
public class StatefulService {

  // private int price;

  public int order(String name, int price) {
    System.out.println("name = " + name + ", price = " + price);
    return price;
  }

}
```

```java
class StatefulServiceTest {
  @Test
  void statefileServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    // 동일한 객체임..!
    int price1 = statefulService1.order("userA", 10000);
    int price2 = statefulService2.order("userB", 20000);

    System.out.println(price1);
    System.out.println(price2);
    
    Assertions.assertThat(price1).isNotEqualTo(price2);

  }
```

# @Configuration과 싱글톤

```java
@Configuration
public class AppConfig {

  // @Bean memberService -> new MemoryMemberRepository()
  // @Bean orderService -> new MemberMemberRepository()

  /*@Bean
  public FixDiscountPolicy getDiscountPolicy() {
    return new FixDiscountPolicy();
  }

  @Bean
  public MemoryMemberRepository getMemberRepository() {
    return new MemoryMemberRepository();
  }*/

  @Bean
  public MemberService memberService() {
    System.out.println("Call AppConfig.memberService");
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    System.out.println("Call AppConfig.memberRepository");
    return new MemoryMemberRepository();
  }

  @Bean
  public OrderService orderService() {
    System.out.println("Call AppConfig.orderService");
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
    System.out.println("Call AppConfig.discountPolicy");
    return new FixDiscountPolicy();
  }
}
```

- `memberService` 빈을 만드는 코드를 보면 `memberServiceImpl()`를 생성하고, `memberRepository();`를 호출한다.
- `orderService` 빈을 만드는 코드를 보면 `OrderServiceImpl()`를 생성하고, `memberRepository();`를 호출한다.

```java
public class ConfigurationSingletonTest {

  @Test
  void configurationTest() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class); // 1.

    MemberRepository memberRepository1 = memberService.getMemberRepository(); // 2.
    MemberRepository memberRepository2 = orderService.getMemberRepository();  // 3.

    System.out.println("memberService -> memberRepository = " + memberRepository1);
    System.out.println("orderService -> memberRepository = " + memberRepository2);
    System.out.println("memberRepository = " + memberRepository);

    assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
    assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
  }
}
```

위의 테스트 코드에서 `memberRepository()`는 3번 호출되야 하는 것이 아닐까?
1. 스프링 컨테이너가 스프링 빈에 등록하기 위해 @Bean이 붙어있는 `memberRepository()` 호출
2. `memberService()` 로직에서 `memberRepository()` 호출
3. `orderService()` 로직에서 `memberRepository()` 호출

출력결과로는 한 번만 출력됨으로 보인다
```
Call AppConfig.memberService
Call AppConfig.memberRepository
Call AppConfig.orderService
Call AppConfig.discountPolicy
```