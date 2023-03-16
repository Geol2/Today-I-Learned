# Hook을 이용해서 자동 빌드 수행하는 방법은 어떻게 할까?

자동으로 브랜치에 푸쉬하면 빌드를 해주게 설정을 해보자

## WebHook
WebHook이란, 특정 이벤트가 발생하였을 때 타 서비스나 응용프로그램으로 알림을 보내는 기능

## 인증

자신의 깃허브 프로필에 

`Setting > Develpoer Settings > Personal Access Token`

`Generate new token` 을 이용해서 하나 발급을 받아야 하는데, 젠킨스를 이용하기 위한 토큰 인증 과정에 필요해서 해두도록 한다

`repo`, `admin:repo-hook`에 전체 체크를 해두고 만들면 키를 하나 발급해주는데 젠킨스에서 사용할 일이 있으니 따로 잘 저장을 해둔다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Project/accessToken.png?raw=true" />

## GtiHub WebHook 설정

설정을 할 레포지토리에서 WebHook 설정을 별도로 지정해주어야 한다

그래야 젠킨스가 푸쉬를 감지해서 이벤트를 발생시킨다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Project/webHook-setting.png?raw=true" />

처음에 설정을 하고 보면 회색 검정색 동그라미표로 아직 활성화가 안되있는 것을 볼 수 있고

젠킨스의 별도 플러그인 설치가 필요했다

## Jenkins Plugin Install

대쉬보드 > 젠킨스 관리 > 플러그인 관리 > `GitHub Integration Plugin` 검색 > 설치

이렇게 하면 Github WebHook 설정에서 초록색 체크로 활성화가 된다

여기서부터 젠킨스에서 pipeline을 만들어야 주었다

## Jenkins Pipeline 설정

대쉬보드에서 새로운 아이템을 만들고 `build-trigger`라는 이름의 파이프라인을 생성하였다

파이프라인 구성은 다음과 같이 설정하였다

### General

- Github Project 체크 > 레포지토리 등록

### Build trigger

- GitHub hook trigger for GITScm polling 체크
- Pipeline
    - Definition : Pipeline script for SCM
    - SCM : git
    - Repositories : 감지할 레포지토리 URL을 등록
    - Add Credential
        - 종류 : SSH Username with private key
        - ID, username : 하고 싶은대로 설정함
        - PassPhrase : 처음에 생성해둔 Personal Access Token 값을 넣어서 인증
    - 위에서 만든 Credential을 선택한다
    - Branches to build : 일단 테스트용 브랜치를 설정한다, 빈 칸 등록 시 전체 다한다고 한다
- Script PATH : Jenkinsfile
    - 등록된 리포지토리 홈에 `Jenkinsfile`이라고 만들어놓고 스크립트를 작성해두면 감지되며 스크립트가 실행된다

## 배포하는 과정이 어떻게 될까?

직접 손배포를 할 때, 클릭클릭해서 하는 과정을 커맨드로 이용을 할 수 있어야 젠킨스에게 맡길 수 있을 것 같다는 생각을 했다

예전에 잠깐 윈도우 개발할 때, 배치파일을 이용해서 컴파일하고 실행파일을 만들어본 경험이 있었는데

해당 파일을 이용하는 생각이 들었다

- 윈도우 기반의 시스템에서는 `gradlew.dat` 을 배치파일을 이용
- 리눅스 기반의 시스템에서는 `gradlew` 파일을 이용

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Project/build-1.png?raw=true" />

로컬 개발환경에서 커맨드로 배포파일을 만드는 중간중간 실패하는 과정을 겪었었다

자바 버전에 문제가 있어서 변경을 해주었다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Project/build-setting.png?raw=true" />

이것저것 건드려보니까 `gradle/libs` 에 생성되기도 하니, 손으로 커맨드를 두드리는 것을 젠킨스에게 맡겨보자

## 그럼 빌드 스크립트를 작성해볼까?

최종 결과 스크립트는 다음과 같았다

```gradle
pipeline {
  agent any

  stages {
    stage('Build') {
      steps {
        sh 'chmod +x gradlew'
        sh 'sudo ./gradlew'
      }
    }
  }
}
```

푸쉬 시, 젠킨스가 이벤트를 감지하고 스크립트를 실행하는 것을 확인했으면 성공이다

이 과정에서 작성된 스크립트가 작동이 안되는 여러 이슈들을 만났는데, 조급해말고 하나하나 찾아가면서 해결해보았다

### ! 중간 이슈 해결 과정

stage('Build') 안의 steps 내에 쉘 명령어에서 다음과 같은 문제들이 발생했다

```
[msg-1] ./gradlew: Permission Denied
[msg-2] sudo no tty present and no askpass program specified
[msg-3] ./gradlew: command not found
```

1. [msg-1]: sudo를 사용하지 않은 문제로 나타났다, sudo를 사용 시 msg-2에러가 발생했다
2. [msg-2]: 원격에서 sudo 를 이용하면서 패스워드를 물으면서 타이핑을 할 수 없는 문제로 젠킨스 서버에 비밀번호를 묻지 않게 아래과 같이 할 수 있게끔 만들어주었는데, 보안상 이슈가 발생될지도 모른단 생각을 해보았다
3. [msg-3]: 스크립트 내부에 `chmod +x gradlew`를 추가해서 실행할 수 있게끔 권한을 지정해주면서 해결하였다

#### 비빌번호 묻기 생략하기

```
$ sudo visudo
```
해당 파일을 수정해주었다

Jenkins 그룹이나 유저에 대해선 비밀번호를 묻지 않게 하게 해주는 것 같다
```
## Allows members of the users group to mount and unmount the
## cdrom as root
# %users  ALL=/sbin/mount /mnt/cdrom, /sbin/umount /mnt/cdrom
Jenkins ALL=(ALL) NOPASSWD=(ALL)
```

훅을 이용해서 빌드까지 성공한 결과이다

<img src="https://github.com/Geol2/Today-I-Learned/blob/main/Java/images/Project/jenkins-stage-view.png?raw=true" />