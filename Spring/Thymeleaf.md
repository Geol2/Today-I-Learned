---
title: "Thymeleaf"
categories:
  - Thymeleaf
tags:
  - Thymeleaf
last_modified_at: 2022-06-13T09:00:00-10:00:00
---

# Thymeleaf

타임리프에 대해 알아보자

<img src="/assets/images/jjal/sungmo(1).png" width="360" >

간단하게 서버 사이드 렌더링을 위한 자바 템플릿 엔진이지만 실제론 자바 템플릿 엔진 프레임워크에 가깝다.

HTML, XML, JavaScript, CSS 및 일반 텍스트를 처리한다.

## Dialects : The Standard Dialect

```jsp
<form:inputText name="userName" value="${user.name}" />
```

```jsp
<input type="text" name="userName" value="James Carrot" th:value="${user.name}" />
```
