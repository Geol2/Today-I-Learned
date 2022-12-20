---
title: "SpringBoot"
categories:
  - SpringBoot
tags:
  - SpringBoot
last_modified_at: 2022-04-10T09:00:00-14:00:00
---

# 스프링부트 설치

## 스프링, 스프링부트

- 의존성 관리를 자동화
- [스프링부트 Initailizer](https://start.spring.io/)
  - `gradle` 사용

<img src="/assets/images/spring/SpringBoot(1).png">

- 의존성 패키지 설치 (Spring Web, Thymeleaf)
- 추가 `JPA`의 사용이 필요할 시, `build.gradle`에 `dependencies` 아래에 추가한다.

```markdown
dependencies {
    //...
    
    implementation org.springframework.boot:spring-boot-starter-data-jpa
    
    //...
}
```

- `src/main/resources/templates/index.html` 파일 생성

<img src="/assets/images/spring/SpringBoot(2).png">

- `public static void main()` 함수 옆에 실행버튼 클릭

<img src="/assets/images/spring/SpringBoot(3).png"><br>

- 실행됨을 확인함

<img src="/assets/images/spring/SpringBoot(4).png">

- `localhost:8181` 확인

---

# @MVC


## 결론

<img src="/assets/images/jjal/force(1).jpeg">

**아 스프링 개신기하다..**<br>
**뭐가 이렇게 기능이 ~~졸라~~ 다양함**<br>

## 기능

- 일단 자주 사용된다고 해서 시간이 없어서 정리함.
- 추가로 더 구조에 대해 알게 되면 그때그때 수정하겠음.

## @RequestMapping 핸들러 매핑

### String[] value(): URL 패턴

- 간단하게 URI 매핑 정보를 나타낸다.

```java
@ReauestMapping("/hello")
@ReauestMapping("/main*")
@ReauestMapping("/view.*")
@ReauestMapping("/admin/*/user")

@RequestMapping("/user/{userid}") // {} : 패스 변수
```

### RequestMethod[] method(): HTTP 요청 메소드

- `GET`, `POST` 메소드 매핑

```java
@RequestMapping(value="/user/add", method=RequestMethod.GET)
@RequestMapping(value="/user/add", method=RequestMethod.POST)
```

### String[] params(): 요청 파라미터

```java
@RequestMapping(value="/user/edit") // 1
@RequestMapping(value="/user/edit", param="type=admin") // 2
@RequestMapping(value="/user/edit", param="type=member") // 3
```

- `<input name="type" value="admin">` 일 경우, 1, 2 적용
- `<input name="type" value="member">` 일 경우, 1, 3 적용

### String[] header(): HTTP 헤더

- `MIME type` 지정

```java
@RequestMapping(value="/view", headers="content-type=text/*")
```

## @RequestParam

- HTTP 요청 파라미터를 메소드 파라미터에 넣어주는 어노테이션
- 해당 파라미터가 없으면 `400 - Bad Request`지만, 사용하지 않을 때 아래처럼 사용할 `defaultValue`를 지정해 줄 수도 있다.

```java
public String add(@RequestParam Map<String, String params>) {...}
public String add(@RequestParam(value="id", required=false, defaultValue="1"){...}
```
----

## @RequestBody, @ResponseBody

- HTTP 요청의 본문 부분이 그대로 전달됨
- XML, JSON 기반의 메시지를 사용하는 경우 유용

```java
@GetMapping(value='/users')
public @ResponseBody User findUser(@RequestParam("username") String userName) {
    return Response.Ok();
}
```
- 이 두가지로 ajax 호출을 만들 수 있다.

----

## @PostMapping

- `@RequestMapping`을 사용하지 않고 HTTP POST 요청만 받아들이는 방법
- 컨트롤러 클래스 안에 요청 처리 메소드를 작성
```java
@PostMapping("/sample/post-mapping")
@ResponseBody
public String SamplePostMapping() {
    return "this is post mapping example";
}
```

## @GetMapping

- `@RequestMapping`을 사용하지 않고 HTTP GET 요청만 받아들이는 방법
- 컨트롤러 클래스 안에 요청 처리 메소드를 작성

```java
@GetMapping("/sample/get-mapping")
@ResponseBody
public String SampleGetMapping() {
    return "this is get mapping example";
}
```

## @Query, @Param

- JPA Repository 인터페이스에서 사용되는 어노테이션
- 직접 쿼리를 작성하는 것이 가능함
- @Param을 통해서 `:authCode`에 매핑

```java
public interface xxRepository extends JpaRepository<xx, String> {
    @Query(value = "SELECT * FROM [table] WHERE [column]=:authCode]")
    Xx findOneByAuthCode(
    @Param("authCode") String authCode);
}
```
-----

# 추가 

## @Controller

- 주로 `View`를 반환하기 위해 사용됨. 

## @RestController

- `@Controller`와 `@ResponseBody`를 합쳤기 때문에 핸들러 메소드에서 `@ResponseBody`를 사용할 필요없음.

---


# IOC, DI

# 빈 Bean
- 스프링 IoC 컨테이너가 관리하는 객체
- 컨테이너 내부에 만든 객체

# 제어의 역전 IoC

컨테이너가 코드 대신 오브젝트에 대한 제어권을 갖고 있음.<br>
스프링 컨테이너를 IoC 컨테이너라고 부른다.<br>
IoC를 담당하는 컨테이너를 빈 팩토리(BeanFactory 인터페이스)나 어플리케이션 컨텍스트(Application Context 인터페이스)라고도 불린다.<br>

```java
public interface ApplicationContext extends ListableBeanFactory, HirarchiBeanFactory, MessageSource, ApplicationEventPublicsher, ResourcePatternResolver { ... }
```

- 이 인터페이스를 구현한 클래스의 오브젝트가 스프링 컨테이너 또는 Ioc 컨테이너

## Ioc 컨테이너를 이용한 애플리케이션 만들기

### Ioc 컨테이너
Bean을 만들고 엮어주며 제공한다.<br>
Bean의 의존성을 관리해주며 `IntelliJ`에서 콩이 붙어있다!<br>
컴포넌트 스캐닝이 붙어있는 친구들이다. `ApplicationContext (BeanFactory)`<br>
<br>
IoC 컨테이너가 관리하는 빈은 오브젝트 단위<br>
직접사용하는 일은 드물 것 같다<br>

```java
StaticApplicationContext ac = new StaticApplicationContext();
// 빈 컨테이너 생성함

ac.registerSigleton("hello1", Hello.class);
// 빈 싱글톤 등록

Hello hello1 = ac.getBean("hello1", Hello.class);
assertTrue(hello1, is( notNullValue() ) );
// 등록된 hello1 빈을 요청하고 NULL인지를 확인한다.

```

## IoC/DI 를 위한 빈 등록 방법

1. XML : `<bean>` 태그
2. XML : 네임스페이스와 전용 태그
3. 자동인식 : 어노테이션과 빈 스캐너
    - 일일이 XML에 선언하는 것은 꽤나 번거로운 작업일 수 있다. 빈 스캐닝이라는 것을 사용해서 자동인식 빈 등록 기능이 있다.
    - `@Repositoy` : 데이터 액세스 계층(DAO) 및 레포지토리 클래스
    - `@Service` : 서비스 계층의 클래스
    - `@Controller` : 웹 요청을 처리하는 컨트롤러 빈으로 선정
4. 자바코드에 의한 빈등록 : @Configuration 클래스의 @Bean 메소드

- 여러방법이 있으므로 팀의 문화와 정책에 맞는 적절한 조합을 일관성 있게 사용하는 것이 좋을 것 같다.

## 빈 오브젝트 상의 DI를 위한 의존관계 작성

1. XML : `<property>`, `<construcotr-arg>`
2. XML : 자동와이어링
3. XML : 네임스페이스와 전용태그
4. 어노테이션 : `@Resource`
5. 어노테이션 : `@AutoWired`/`@Inject`

- @AutoWired/@Inject
  - `생성자`
  - `필드`
  - `Setter`

```java
public class VasSqlService implements SqlService {
    protected SqlReader sqlReader;
    protected SqlRegistry sqlRegistry;

    @Autowired
    public BasSqlService(SqlReader sqlReader, SqlRegistry sqlRegistry) {
        this.sqlReader = sqlReader;
        this.sqlRegistry = sqlRegistry;
    }
}
```

## Properties 값 설정

1. XML : <property> 전용 태그

```xml
<bean id="hello" ...>
    <property name="name" value="Everyone" />
</bean>
```

2. 어노테이션 : @Value

```java
public class Hello {
    private String name;
    @Value("EveryOne")
    public void setName(String name) {
        this.name = name;
    }
}
```

3. 자바코드 : @Value

```java
@Configuration
public class Config {
    @Value("${database.username}")
    private String name;

    @Bean
    public Hello hello() {
        Hello hello = new Hello();
        hellp.setName(this.name);
        return hello;
    }
}
```


- 간단한 정리일 뿐이라서 더 상세하게 공부가 필요한 개념이다.


# ❗

- 스프링을 이번에 처음 사용하게 되서 공부하게 되었지만, 예전부터 옆에서 많이 들어봤었고 이론책에서의  개념이나 어떤 핵심적인 역할을 하는지 정리할 필요가 있을 것 같아서 적어놓는다.

---

# GenerationType

```
java.lang.Object
    - java.lang.Enum<GenerationType>
        - javax.persistence.GenerationType
```

`public enum GenerationType`

`extends Enum<GenerationType>`

- 기본키 생성 전략의 유형을 정의한 enum 타입 클래스

|   종류   |                                           설명                                            |
|:--------:|------------------------------------------------------------------------------------------|
|   AUTO   | 특정 데이터베이스에 대해 적절한 전략을 선택해야 함                                          |
| IDENTITY | 데이터베이스 ID 열을 사용하여 엔터티에 대한 기본 키를 할당해야 함                            |
| SEQUENCE | 데이터베이스 시퀀스를 사용하여 엔터티에 대한 기본 키를 할당해야 함                           |
|  TABLE   | 고유성을 보장하기 위해 기본 데이터베이스 테이블을 사용하여 엔터티에 대한 기본 키를 할당해야 함 |


## GenerationType.IDENTITY

```java
@Entity
@Table(name = "USER_MEMBER")
public class USER_MEMBER {
    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Long id;

    //...
}
```

`@GeneratedValue(Strategy = GenerationType.IDENTITY)`

기본키 생성을 데이터베이스에 위임하며 `id`값을 `null`로 하면 `AUTO_INCREMENT` 해주는 방법


-----

- [참고](https://docs.oracle.com/javaee/7/api/javax/persistence/GenerationType.html)

---

# ResponseEntity

- org.springframework.http!
- `java.lang.Object` : `org.springframework.http.HttpEntity<T>` : `org.springframework.http.ResponseEntity<T>`

- 파라미터 타입 T - the body type
- `HttpEntity`를 확장

## 컨트롤러

```java
class UserController() {
    //..

    @PostMapping(value = "...")
    public ResponseEntity<String> func(@Request USER_MEMBER body) {
        // ...

        return new ResponseEntity<string>(null, responseHeader, HttpStatus.OK);
    }
}
```

- 첫 번째 매개변수의 `null`에 body를 담아서 보낼 수 있는 것을 확인했다.

## @ResponseBody, ResponseEntity

- 기능상의 차이점은 없다고 함.
- @ResponseBody의 경우 파라미터로 객체를 받아 header를 변경
- ResponseEntity의 경우 클래스 객체를 생성 후 객체에서 header 값을 변경

## PHP CURL

```php
//..curl
$response = curl_exec($ch);
$http_code = curl_getinfo($ch, CURLINFO_HTTP_CODE);
//..
```

- 위 코드를 통해 JAVA의 `HttpStatus` 상태값을 받아올 수 있었다.
- `$response` 는 `ResponseEntity`가 `null`이였으므로 `''`값을 가져오는 것을 확인했다.
- 리턴될 `$data`는 `json_decode($response)`한 뒤,  다시 `json_encode($data)` 만들었다.


```php
$data = [
    "code" => $http_code,
    "msg" => json_decode($response)
];

return json_encode($data);
```

# ❗️

- 기존에 개인적으로 쓰던 `ResponseJson` 클래스 대신 이것을 사용하기로 변경했다.

-----

