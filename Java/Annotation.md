# Annotation 정리

- @Override : 오버라이드된 메서드에 사용되는 어노테이션.
- @AfterEach : 사용되고 난 후 종료해줄 리소스를 처리하는 어노테이션. 
- @Controller : 해당 클래스가 컨트롤러임을 알리는 어노테이션.
- @ResponseBody : 컨트롤러에서 바로 데이터를 전달해줘야 할 필요가 있을 때 사용되는 어노테이션.(JSON or String)
- @GetMapping : URL에 노출되는 HTTP GET방식 핸들러 어노테이션. `/users`, `/users/1`...
- @Test : 테스트 함수로 작성된 어노테이션.

- @Service, @Repository : 루트 컨테이너에 빈 객체로 생성해주는 어노테이션, @component 어노테이션을 붙여도 상관없지만 헷갈리지 않기 위해 구분해서 사용하기를 권장한다.
- @AutoWired
  - 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 연결해주는 어노테이션.
  - 객체 의존 관계를 외부에서 넣어주는 것을 DI(Dependency Injection)이라 불린다.
