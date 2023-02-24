# 필터

## 구현

HTTP 요청과 응답을 변경할 수 있는 재사용 가능한 클래스

필터는 디스패처 서블릿이 컨트롤러를 호출하기 전과 후에 요청과 응답을 참조하거나 가공할 수 있는 기능을 제공

필터를 구현하는데 있어 핵심은 3개 타입이 있다

- javax.servlet.Filter 인터페이스 : 클라이언트와 최종 자원 사이에 위치하는 필터를 나타내는 개체가 구현해야 하는 인터페이스
- javax.servlet.ServletRequestWrapper 클래스 : 필터가 요청을 변경한 결과를 저장하는 래퍼
- javax.servlet.ServletResponseWrapper 클래스 : 필터가 응답을 변경하기 위해 사용하는 래퍼

### Filter 인터페이스

- `public void Init(FilterConfig filterConfig) throws ServletException` : 필터를 초기화
- `public void doFilter(ServletRequest request, ServeltResponse response, FilterChain chain) throws java.io.IOException, ServletException` : 필터 기능을 수행
- `public void destroy()` : 필터가 웹 컨테이너에서 삭제될 때 호출

```java
public class FirstFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        // 필터 초기화 작업
    }

    public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain)
                        throws IOException, ServletException {
        // 1. request 파라미터를 이용해서 요청의 필터 작업 수행

        // 2. 체인의 다음 필터 처리

        // 3. response를 이용하여 응답의 필터링 작업 수행
    }

    public void destroy() {
        // 주로 필터가 사용한 지원을 반납
    }
}
```

1. request 파라미터를 이용하여 클라이언트의 요청을 필터링한다
2. chain.doFilter() 메소드를 호출, 요청의 필터링 결과를 다음 필터에 전달
3. response 파리미터를 사용하여 클라이언트로 가는 응답을 필터링한다


### 필터 설정하기 : xml

### 필터 설정하기 : @WebFilter 어노테이션

