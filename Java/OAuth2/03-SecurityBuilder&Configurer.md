# SecurityBuilder

- 빌더 클래스로 웹 보안을 구성하는 빈 객체와 설정 클래스들을 생성하는 역할
- WebSecurity, HttpSecurity 가 대표적으로 해당 역할을 한다

SecurityBuilder 인터페이스는 HttpSecurity, WebSecurity, AuthenticationManagerBuilder 를 구현하고 있다.

# SecurityConfigurer

- Http 요청과 관련된 보안처리를 담당하는 필터들을 생성하고 여러 초기화 설정에 관여함
- SecurityBuilder는 SecurityConfigurer를 포함하고 있으며 인증 및 인가 초기화 작업은 SecrityConfigurer에 의해 진행됨

SecurityConfigurer 인터페이스는 SecurityContextConfigurer, HttpBasicConfigurer, FormLoginConfigurer, CsrfConfigurer, ExceptionHandlingConfigurer, AnonymousConfigurer 를 구현하고 있다.

## 실행

AutoConfiguration의 build()를 통한 빌드 클래스가 생성되면 SecurityBuilder 설정 클래스가 생성되고

SecurityConfigurer init()과 configure() 메소드가 실행되면서 초기화 작업을 실행한다

WebSecurity 의 FilterChainProxy 가 생성되면 HttpSecurity의 SecurityFilterChain이 생성되고

FilterChainProxy은 SecurityFilterChain의 상호 관계가 있다

## 자동설정으로 어플리케이션을 구동

시큐리티 시작 동작 방식

1. SpringWebMvcImportSelector : WebMvcSecurityConfiguration

2. SecurityFilterAutoConfiguration : DelegatingFilterProxyRegistrationBean - DelegatingFilterProxy 등록 ("springSecurityFilterChain" 이름의 빈을 검색)

3. WebMvcSecurityConfiguration
  : `AuthenticationPrincipalArgumentResolver ☆` - @AuthenticationPrincipal로 Principal 객체 바인딩
  : CurrentSecurityContextArgumentResolver
  : ScrfTokenArgumentResolver

4. HttpSecurityConfiguration : HttpSecurity - 공통 설정 클래스와 필터들을 생성하고 최종적으로 `SecurityFilterChain 빈 반환 ☆`

---

gradle에서 시큐리티를 포함시키고 구동시키면 아래와 같은 것이 시작된다

SpringBootWebSecurityConfiguration 은 다음과 같은 코드를 실행시킨다

```java
@Bean
@Order(SecurityProperties.BASIC_AUTH_ORDER)
SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
  http.authorizeRequests().anyRequest().authenticated();
  http.formLogin();
  http.httpBasic();

  return http.build();
}
```

WebSecurityConfiguration은 WebSecurity를 생성한다

WebSecurity 내에 속성으로 securityFilterChainBuilders가 있고 그 안에 SecurityBuilder 익명 클래스들이 들어가고 그 안에 SecurityFilterChain을 가지고 있다.

위에 초기화를 거치고 build() 과정을 거치면서 FilterChainProxy 를 생성하게 되고 속성이 SecurityFilterChains을 가지고 있고 또 그 안에 SecurityFilterChain 들을 가지고 있다.

```java
@Bean
@Order(0)
SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
  http.authorizeRequests().anyRequest().authenticated();
  http.formLogin();
  http.httpBasic();

  return http.build();
}

@Bean
@Order(1)
SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
  http.authorizeRequests().anyRequest().authenticated();
  http.formLogin();
  http.httpBasic();

  return http.build();
}
```

사용자 정의 설정 클래스를 생성하고 어플리케이션을 구동하면 기본으로 생성되는 SpringBootWebSecurityConfiguration의 SecurityFilterChainConfiguration 클래스가 구동되지 않는다.
