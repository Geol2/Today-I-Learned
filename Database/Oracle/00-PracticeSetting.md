세팅된 운영체제 환경 : Mac M2 > Parallels : Windows 11

Mac M2(arm)에서의 실행은 공식적으로 지원하지는 않고 Docker 환경에서 작동시켜야 하는데, 

인터넷에서 돌아다니는 오래된 자료 몇 개는 이미지의 x86_64 CPU 아키텍처 환경에 맞춰진 건지 M2에서 작동하지 않는 것을 확인했다. (예전 intell 모델 맥에선 되겠지 싶기도..)

윈도우에선 도커를 사용할 수 있으므로 도커를 이용해도 되고 직접 깔아도되고,,

1. 맥에서 애용되는 `brew` 패키지 관리자를 이용해서 `colima` 를 이용한 자료가 있긴하다(?)
    1. colima는 맥OS에서 컨테이너 런타임으로 x86_64환경을 지원하도록 해주는 패키지로 추측된다.( https://github.com/abiosoft/colima )
2. `Docker`를 이용한 환경

```bash
docker pull container-registry.oracle.com/database/free:latest
```

오라클에서 공식적으로 지원하는 컨테이너로 보인다. 그 외에도 다양한 이미지들이 존재한다.

도커 이미지를 컨테이너로 실행하는 방법이다.

```bash
docker run --name <container name> \
-p <host port>:1521 -p <host port>:5500 \
-e ORACLE_PWD=<your database passwords> \
-e ORACLE_CHARACTERSET=<your character set> \
-v [<host mount point>:]/opt/oracle/oradata \
oracle/database:21.3.0-xe
```

위 코드블록 부분이 핵심세팅 내용으로 보인다.

- docker pull :  도커에서 제공해주는 이미지를 로컬PC로 가져오는 역할을하고 : 뒤에는 버전이다.
- docker run : 로컬에 있는 이미지를 컨테이너로 실행시켜주는 역할을 하고 각 파라미터들은 컴퓨터와 가상환경 간 필수 매핑요소로 넣어줘야되는 설정. (-p 포트설정 매핑, -e 환경설정 매핑, -v 저장경로 매핑)

아래 첨부자료에 자세한 설정이 나와있다.

https://github.com/oracle/docker-images/tree/main/OracleDatabase/SingleInstance

# 그냥 윈도우11에서 직접 깔기로 했다..

(Windows 10은 테스트해보지 않아서 다를 수 있다)

오라클 데이터베이스를 다운받고 설치 시, Administrator 계정에서 진행해줘야 잘 작동되었다.

윈도우에서 터미널(윈도우키 + R)에서 다음과 같은 커맨드를 입력해서 Administrator 계정에 접근한다.

```
net user Administrator /active:yes
```

1. 아래 경로에서 **Run Oracle Database 21c XE on Windows** 설치
    1. https://www.oracle.com/kr/database/technologies/oracle-database-software-downloads.html
2. **SQL Developer** 다운로드
    1. https://www.oracle.com/database/sqldeveloper/technologies/download/

오래 걸리므로 존버한다.

## OracleXE213_Win64

진행하다가 안되는 경우가 너무 많았다. (관리자 계정)

압축풀고 해당 위치에서 **setup.exe**를 실행하고 진행해준다. 

설정된 비밀번호는 꼭 기억해두어야한다. (까먹었는지 접속이 안되서 고생…)

### 계정 생성

- 계정명과 비밀번호 : scott / tiger ← 이렇게 쓰는 것을 어디선가(?) 본 적이 있는 것 같음

```bash
sqlplus
사용자명 입력: sys as sysdba
비밀번호는 엔터쳐서 넘긴다.
```

```bash
CREATE USER scott IDENTIFIED BY tiger;
GRANT CONNECT, RESOURCE, DBA TO scott; # scott에 모든 권한 부여
COMMIT; # 저장
```

## SQL Developer

접속을 위해서 다음과 같이 입력했다.

세부정보에서 호스트이름을 `localhost`로 하면 안되고 ipconfig에 나오는 아이피를 넣는다.

완료~!