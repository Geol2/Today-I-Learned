# AuthenticationEntryPoint 이해

1. FormLoginConfigurer

2. ExceptionHandlingConfigurer

3. defaultEntryPointMappings.put

4. HttpBasicConfigurer

5. defaultEntryPointMappings.put

`FormLoginConfigurer`, `HttpBasicConfigurer` 보통 두 가지 중 하나의 방식으로 한다.

두 가지 방식 중 처리는 `ExceptionHandlingConfigurer` 에서 모든 인증 과정에서의 요청을 처리한다.

커스텀 방식으로 선택 시, `CustomAuthenticationEntryPoint`를 생성하면 가장 우선 시 선택된다.

커스텀 방식이 아닌 경우, 두 가지 방식이 존재하지 않으면 `Http403ForbiddenEntiryPoint` 이 나타난다.

최종 엔트리포인트는 `ExceptionTranslationFilter`에 전달된다.

