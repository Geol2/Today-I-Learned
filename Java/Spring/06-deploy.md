# 네이버 클라우드를 이용해서 배포해보기

홈 컨트롤러에 Hello World를 출력해주고 있는 간단한 프로젝트를 배포하기

## 배포파일 만들어보기

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Spring/deploy-1.png" />

인텔리제이에 Gradle 탭이 존재하는데 `bootJar > Run` 을 하면, `프로젝트 > build > libs > *-0.0.1-SNAPSHOT.jar` 이 있다

## 네이버 클라우드 설정

배포를 하기 위해서 네이버 클라우드에 서버를 만들어야 한다

`.pem` 키는 파일은 중요하니까 잘 보관하고 있고 접속 전용 아이피를 이용해서 접속한 뒤, mobaXterm을 이용해서 접속했다

네이버 클라우드에서 서버의 오른쪽 클릭으로 관리자 비밀번호를 확인할 수 있어서 `id/pw`를 이용해서 접속한다

1. ACG 에서 80번 포트를 연다
2. 공인 IP에서 IP를 발급받는다

## CentOS에 java 17 설치하기

서버에 접속하면 java 17부터 설치해줘야 한다

```
$ wget https://download.java.net/java/GA/jdk17.0.2/dfd4a8d0985749f896bed50d7138ee7f/8/GPL/openjdk-17.0.2_linux-x64_bin.tar.gz

$ tar xvf openjdk-17.0.2_linux-x64_bin.tar.gz

$ sudo mv jdk-17.0.2/ /opt/jdk-17/

$ vim ~/.bashrc
export JAVA_HOME=/opt/jdk-17
export PATH=$PATH:$JAVA_HOME/bin 

$ source ~/.bashrc
```

```
$ echo $JAVA_HOME
/opt/jdk-17

$ java --version
openjdk 17.0.2 2022-01-18
OpenJDK Runtime Environment (build 17.0.2+8-86)
OpenJDK 64-Bit Server VM (build 17.0.2+8-86, mixed mode, sharing)
```

## 배포파일 실행하기

8080포트로 기본설정이 되어 있어서 80포트로 실행할 수 있게끔 바꿔서 실행한다

```
java -Dserver.port=80 -jar helpu-0.0.1-SNAPSHOT.jar
```

`Hello World` 출력되면 성공!