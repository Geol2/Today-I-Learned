# Dockerfile

자바 배포할 때 사용되는 명령어이다

```dockerfile
FROM openjdk:11
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

`docker build`, `docker run` 순서대로 진행한다
