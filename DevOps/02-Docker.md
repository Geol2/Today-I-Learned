# Docker 사용하기

- `-it ... /bin/bash`: 옵션은 실행 후 바로 꺼지지 않게 하기 위해서 한다
- `-p`: 옵션을 이용해서 여러 포트를 로컬과 컨테이너 포트에 매핑을 할 수 있다
- `-v`: 매핑할 볼륨 저장 경로를 지정할 수 있다

## php 버전 선택하기

### 우분투

```shell
apt update

update-alternatives --config php

# 선택한 뒤, 만든 php 버전을 선택한 뒤에 맞는 php[버전]-[패키지명] 으로 필요 패키지를 전부 설치해주는 것이 좋다
```

## mysql, mariadb 접속하기

```shell
docker pull mysql
docker run -it -p 3307:3306 /bin/bash
````

```php
$DB_HOST = "docker.for.mac.host.internal"; # docker.host.internal
$DB_PORT = "3307";
$DB_USER = "root";
$DB_PW = "비번";
$DB_NAME = "데이터베이스 이름";
```


## docker commit, push

```shell
docker images # 이미지 확인

# 최근 이미지 커밋/푸쉬
docker commit [CONTAINER_NAME] [IMAGE_NAME]:[TAG] # 커밋

docker push [IMAGE_NAME]:[TAG] # 푸쉬
```

## docker run

도커 이미지를 컨테이너로 실행하기 위해서 사용할 수 있다.

```shell
docker run --name [컨테이너 이름] -p [로컬포트]:[컨테이너포트] -p[로컬포트]:[컨테이너포트] -it [도커 이미지] /bin/bash

# 예시 스프링 mvc 서버를 세팅
docker run --name webserver-tomcat -p 10099:10099 -p 10100:10100 -p 10021:21 -p 8085:80 -p 8086:8085 -v /Users/geol/eclipse-workspace-3.10.0/my-app/target:/opt/tomcat/apache-tomcat-9.0.80/webapps -it geol2/webserver-tomcat:1.2 /bin/bash
```

## docker exec

도커 컨테이너에 접속하기 위한 명령어

```shell
docker exec -it [container] /bin/bash

# 실행 예시
docker exec -it webserver-tomcat /bin/bash
```