# 간단한 명령어 모음집


## php 버전 선택하기

### 우분투

```shell
apt update

update-alternatives --config php

# 선택한 뒤, 만든 php 버전을 선택한 뒤에 맞는 php[버전]-[패키지명] 으로 필요 패키지를 전부 설치해주는 것이 좋다
```


## 이미지 커밋/푸쉬하기

```shell
# 최근 이미지 커밋/푸쉬

docker commit [CONTAINER_NAME] [IMAGE_NAME]:[TAG] # 커밋

docker push [IMAGE_NAME]:[TAG] # 푸쉬
```

## exec

도커에 접속하기 위한 명령어

```shell
docker exec -it [container] /bin/bash
```