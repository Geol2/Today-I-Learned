# Spring Container

```java
ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
```

- `ApplicationContext`를 스프링 컨테이너라고 하고 인터페이스이다

- 스프링 컨테이너 생성 방법 : `new AnnotationConfigApplicationContext(AppConfig.class)` 으로 만들고 `ApplicationContext` 인터페이스의 구현체

## 생성 방법
1. xml 기반
2. 어노테이션 기반

@Bean으로 설정되어있는 함수를 스프링 컨테이너에 빈 저장소에 등록한다

### 빈 이름
- `메서드 이름`을 사용
- `@Bean(name="서비스이름");`을 사용

다른 이름으로 부여하도록 하는 것이 좋다

스프링은 빈을 생성하고 의존관계를 주입하는 단계가 나누어져 있다

## 빈 출력하기

```java
@Test
@DisplayName("모든 빈 출력하기")
void findAllBean() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for(String beanDefinotionName : beanDefinitionNames) {
        Object bean = ac.getBean(beanDefinotionName);
        System.out.println("name = " + beanDefinotionName + " object = " + bean);
    }
}

@Test
@DisplayName("애플리케이션 빈 출력하기")
void findApplicationBean() {
    String[] beanDefinitionNames = ac.getBeanDefinitionNames();
    for(String beanDefinitionName: beanDefinitionNames) {
        BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

        if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
        // 애플리케이션의 빈을 출력할 수 있ㄷ다
        Object bean = ac.getBean(beanDefinitionName);
        System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }
}
```
- `ac.getBeanDefinitionNames()` : 스프링에 등록된 모든 빈 이름을 조회한다
- `ac.getBean()` : 빈 이름으로 빈 객체를 조회
- `BeanDefinition.ROLE_APPLICATION` : 사용자가 정의한 빈
- `BeanDefinition.ROLE_INFRASTRUCTURE` : 스프링 내부에서 사용하는 빈

# BeanFactory와 ApplicationContext

## BeanFactory

- 스프링 컨테이너의 최상위 인터페이스
- 스프링 빈을 관리하고 조회하는 역할
- 대부분의 기능을 제공한다

## ApplicationContext

- BeanFactory 기능을 모두 상속받아서 제공한다
- 부가로 제공하는 기능들이 많다

1. 메시지 소스를 활용한 국제화 기능 (MessageSource)
    - 한국어, 영어 등 지역화에 따른 언어 출력 제공
2. 환경변수 (EnvironmentCapable)
    - 로컬, 개발, 운영, 스테이징 환경 분리
3. 어플리케이션 이벤트 (ApplicationEventPublisher)
    - 이벤트를 발행하고 구독하는 모델을 편리하게 지원
4. 편리한 리소스 조회 (ResourceLoader)
    - 파일, 클래스 패스, 외부 등에서 리소스를 편리하게 조회

# 다양한 설정 형식 지원 - 자바, XML

ApplicationContext 인터페이스를 상속하는 세 가지가 있다

## AnnotationConfigApplicationContext
- AppConfig.class

## GenericXmlApplicationContext
- appConfig.xml

## XxxApplicationContext
- appConfig.xxx

1. xml 기반
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="memberService" class="com.meme.member.MemberServiceImpl" >
    <constructor-arg name="memberRepository" ref="memberRepository" />
  </bean>

  <bean id="memberRepository" class="com.meme.member.MemoryMemberRepository" />

  <bean id="orderService" class="com.meme.order.OrderServiceImpl">
    <constructor-arg name="memberRepository" ref="memberRepository" />
    <constructor-arg name="discountPolicy" ref="discountPolicy" />
  </bean>

  <bean id="discountPolicy" class="com.meme.discount.RateDiscountPolicy" />

</beans>
```

# BeanDefinition

`AnnotationConfigApplicationContext`는 `AnnotatedBeanDefinitionReader`를 사용해서 `AppConfig.class` 을 읽고 `BeanDefinition`을 생성한다

`GenericXmlApplicationContext`는 `xmlBeanDefinitionReader`를 사용해서 `AppConfig.xml` 을 읽고 `BeanDefinition`을 생성한다

## BeanDefinition 정보
- BeanClassName : 생성할 빈의 클래스명
- factoryBeanName : 팩토리 역할의 빈을 사용할 경우 이름
- factoryMethodName : 빈을 생성할 팩토리 메서드 지정
- Scope : 싱글톤
- lazyInit : 스프링 켄테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때까지 최대한 생성을 지연처리 하는지 여부
- InitMethodName : 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
- DestroyMethodName : 빈 생명주기가 끝나서 제거하기 직전에 호출되는 메서드명
- Construct arguments, Properties : 의존관계 주입에서 사용한다