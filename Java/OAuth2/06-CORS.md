# CORS (Cross-Origin Resource Sharing)

- 교차 출처 리소스 공유
- HTTP 헤더를 사용해서 한 출처에서 실행 중인 어플리케이션이 다른 출처의 선택한 자원에 접근할 수 있는 권한을 부여하도록 브라우저에 알려주는 체제
- 출처를 비교하는 방법은 `Protocol`, `Host`, `Port` 세 가지가 동일한지 확인하고 나머지는 달라도 상관없다
- 세 가지 중 하나라도 다르면 다른 출처로 판단한다

#### Simple Request

- Simple Request 는 예비요청(PreFilght)을 과정없이 바로 서버에 요청을 한 후, 서버가 응답의 헤더에 `Access-Control-Allow-Origin` 과 같은 값을 전송하면 브라우저가 서로 비교 후 CORS 정책 위반여부를 검사하는 방식
- 제약사항
  - `GET`, `POST`, `HEAD` 중에 한 가지 Method 를 사용해야한다.
  - 헤더는 `Accept`, `Accept-Language`, `Content-Language`, `Content-Type`, `DPR`, `Downlink`, `Save-Data`, `Viewport-Width Width`만 가능하고 Customer Header는 허용되지 않는다.
  - `Content-type`은 `application/x-www-form-urlencoded`, `multipart/form-data`, `text/plain` 만 가능하다

#### Preflight Request (예비요청)

- 브라우저는 요청을 한번에 보내지 않고 예비요청과 본 요청을 나누어서 서버에 전달하는데 예비요청을 보내는 것을 Preflight 라고 하며 메소드는 OPTIONS가 된다.
- 요청사항이 Simple Request 에 해당하지 않을 경우 브라우저가 Preflight Request를 실행한다.

### 동일 출처 기준

https://security.io

- https://security.io/auth?username=user1 : 스킴, 호스트, 호트 동일
- https://user:password@security.io : 스킴, 호스트, 포트 동일
- http://security.io : 스킴이 다름
- https://api.security.io : 호스트가 다름
- https://security.io:8000 : 브라우저의 구현에 따라 다름, explorer는 포트자체를 무시함

#### CORS 해결 - 서버에서 Access-Control-Allow-* 세팅

- Access-Control-Allow-Origin : 헤더에 작성된 출처만 브라우저가 리소스를 접근할 수 있도록 허용
- Access-Control-Allow-Methods : preflight request 에 대한 응답으로 실제 요청 중에 사용할 수 있는 메서드를 나타낸다
- Access-Control-Allow-Headers : preflight request 에 대한 응답으로 실제 요청 중에 사용할 수 있는 헤더 필드 이름을 나타낸다
- Access-Control-Allow-Credentials : 실제 요청이나 쿠키나 인증 등의 사용자 자격 증명이 포함될 수 있음을 나타낸다. Client의 credentials:include 일 경우 true가 필수
- Access-Control-Max-Age : preflight 요청 결과를 캐시할 수 있는 시간을 나타내는 것으로 해당 시간동안은 preflight 요청을 다시 하지 않게 된다

####  CorsConfigurer

- Spring Security 필터 체인에 CorsFilter를 추가한다
- corsFilter 라는 이름의 Bean이 제공되면 해당 CorsFilter가 사용된다
- corsFilter 라는 이름의 Bean이 없고 CorsConfigurationSource 빈이 정의된 경우 해당 CorsConfiguration이 사용된다
- CorsConfigurationSource 빈이 정의되어 있지 않은 경우, Spring MVC가 클래스 경로에 있으면 HandlerMappingIntrospector가 사용된다

#### CorsFilter

- CORS 예비 요청을 처리하고 CORS 단순 및 본 요청을 가로채고, 제공된 CorsConfiguration 를 통해 일치된 정책에 따라 CORS 응답 헤더와 같은 응답을 업데이트하기 위한 필터이다
- Spring MVC Java 구성과 Spring MVC XML 네임스페이스에서 CORS를 구성하는 대인이라 볼 수 있다
- 스프링 웹에 의존하는 응용 프로그램이나 javax.servlet 에서 CORS 검사를 수행해야 하는 보안 제약 조건에 유용한 필터이다

```java
@Override
protected void configure(final HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .anyRequest().authenticated()
        .and();

    http.cors().configurationSource(corsConfiguationSource()); // 설정을 초기환한다.
}

@Bean
public CorsConfiguationSource corsConfiguationSource() {

    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("*");
    configuration.addAllowedMethod("*");
    configuration.addAllowedOrigin("*");
    configuration.addAllowCredentials(true);
    configuration.addMaxAge(3600L);
    
    UrlBAsedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
}
```
