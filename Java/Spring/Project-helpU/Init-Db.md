# 로컬 개발환경 구성하기

1. 의존성 추가
2. H2 데이터베이스 접속
3. DDL 스크립트 작성
4. DML 스크립트 작성

- [2.1.x 데이터베이스 참고](https://docs.spring.io/spring-boot/docs/2.1.x/reference/html/howto-database-initialization.html)
- [3.0.4 데이터베이스 참고](https://docs.spring.io/spring-boot/docs/3.0.4/reference/html/howto.html#howto.data-initialization)

## 의존성 추가

JPA를 사용해서 어플리케이션이 실행할 때, DDL을 생성을 위한 기능이 존재한다

```groovy
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-data-jdbc'

	developmentOnly 'org.springframework.boot:spring-boot-devtools'

	runtimeOnly 'com.h2database:h2' // 런타임 시 h2 작동
}
```
왼쪽에 시작버튼을 눌러서 추가해준다

## application.yml 작성

```yaml
spring:
  # H2
  h2:
    console:
      enabled: true                 # in-memory 방식을 사용하는 경우 H2 콘솔을 사용할 것인지의 유무
      path: /h2-console

  # DataBase info
  datasource:
    driverClassName: org.h2.Driver
    url: jdbc:h2:mem:test
    username: sa
    password:
  # JPA
  jpa:
    database-platform: H2
    # show-sql: true                # sql문 확인 시 주석 해제
    # generate-ddl: true
    hibernate:
      ddl-auto: create              # 운영, 스테이징 단계 : validate
    open-in-view: false
  sql:
    init:
      mode: always
```

- `localhost:8080` 으로 실행 시, `localhost:8080/h2-console`에 접속하면 H2에 접속할 수 있다

- `/src/main/java/resources` 하위에 `application.yml`, `import.sql`, `schema.sql` 을 만들어두면 문법에 문제가 없다면 자동으로 테이블과 데이터가 생성된다
    1. import.sql : DML 구문
    2. schema.sql : DDL 구문
    3. application.yml : 서버 설정 스크립트

- H2가 실행이 안되는 경우
    - `File > Invalidate Cache` 를 해서 재시작한다

### Datasource.url
- `jdbc:h2:file:~/test` : 로컬 컴퓨터의 디스크에 접근해서 `*.mv.db`, `*.trace.db` 파일을 이용함으로 해당 파일을 공유
- `jdbc:h2:mem:test` : 메모리에 접근해서 사용 가능하고 DDL, DML 파일을 같이 제공하면서 초창기 변경사항을 빠르게 대응할 수 있음

각자 장단점이 존재함으로 현재 상황에서 가장 적합한 방법을 쓰도록 한다

### DDL 관련 설정
- `spring.jpa.generate-ddl` : boolean을 이용해서 DDL을 사용하고 안하고를 간단하게 정의할 수 있다
- `spring.jpa.hibernate.ddl-auto` : 좀 더 세분화된 방식을 이용해서 정의를 할 수 있다
    - none : 사용하지 않음
    - create : 기존 테이블 삭제 후 테이블 생성
    - create-drop : 기존 테이블 삭제 후 테이블 생성, 종료 시점에 테이블 삭제
    - update : 변경된 스키마 적용
    - validate : 엔티티와 테이블 정상 매핑 확인

## 접속화면과 테이블 생성 확인

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Project/h2-connect.png?raw=true" />

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Project/h2-connect-table.png?raw=true" />

