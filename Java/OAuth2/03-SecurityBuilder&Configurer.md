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

