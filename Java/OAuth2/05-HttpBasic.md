# HttpBasic

순서대로 실행됨

- 요청발생 -> BasicAuthenticationFilter -> BasicAuthenticationConvertor -> UsernamePasswordAuthenticationToken -> AuthenticationIsRequired

------

- AuthenticationIsRequired 가 N 인 경우 -> Chain.doFilter 실행
- AuthenticationIsRequired 가 Y 인 경우 -> ProviderManager -> DaoAuthenticationProvider -> 인증성공 -> UsernamePasswordAuthenticationToken -> SecurityContext -> 인증 성공
- DaoAuthenticationProvider -> 인증실패 -> BasicAuthenticationEntryPoint -> 재인증 -> Login Prompt
- BasicAuthenticationEntryPoint -> 인증취소 -> 401 UnAuthorized

UsernamePasswordAuthenticationToken 의 requestHeader 내에 보면 Authorization : Basic ~ 값이 헤더에 존재함

