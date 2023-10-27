# root 관리

사용자의 계정정보 위치 : /etc/passwd 파일에 시스템 계정이 존재

root 계정관리

1. root UID는 0이다. 다른 계정에 0이 있는지 확인한다
2. pam을 이용해서 root계정으로 직접 로그인하는 것을 막는다
3. 관리자라도 일반 계정으로 로그인하고 su 명령을 통해 사용을 유도한다
4. TMOUT을 설정해서 장시간 로그인을 막는다
5. 일반 사용자가에게 특정 명령어 권한을 줄 때 sudo를 이용하도록 한다

### useradd

```shell
# posein 계정을 생성
useradd posein

# marine 이라는 사용자를 생성하고 홈 디렉터리의 경로와 그룹을 지정
useradd marine -d /home/terran/marine -g terran
```
## passwd

```shell
# 지금 로그인한 사용자의 암호를 변경
passwd

# yuloje 라는 사용자의 암호를 변경
passwd yuloje
```

### su

```shell
# 실제 root 권한을 수행에는 환경변수 등에는 제약이 따른다.
su

# 실제 root 권한을 수행한다
su -

# 일시적으로 명령을 내릴 때 -c 옵션을 준다
su root -c "tail /etc/shadow"

# 사용자로 전환한다, root일 경우 패스워드를 묻지 않고 전환한다
su - yuloje
```

### passwd

```shell
passwd -l username # username 을 잠금
passwd -u username # username 을 잠금해제
passwd -d username # username 패스워드 제거
passwd -n 3 -x 200 -w 5 -i 10 uesrname # 200일간 사용할 수 있고 변경 시 3일은 변경할 수 없고 5일전부터 만료경고를 알려주고 만료 뒤 10일간 유예기간을 둠
passwd -e username # 다음 로그인 시, 패스워드 변경을 반드시 한다
```

### /etc/passwd

구조 : `username:password:UID:GID:fullname:home-directory:shell`

패스워드에는 /etc/shadow 로 암호화해서 관리된다

### /etc/shadow

구조 : `username:password:last:may:must:warn:expire:disable:reserved`

- last : 마지막 패스워드 변경일
- may : 패스워드 변경 후 최소로 사용해야하는 날짜수
- must : 현재 사용 중인 패스워드의 최대 사용 가능 기간, 99999면 계속 사용가능
- warn : 만료기한이 다가오는 알람 디데이 일 수
- expire : 만료 후 사용할 수 있는 일 수
- disable : 계정 만기일
- reserved : 공백


- su 명령으로 간단하게 관리자 권한을 획득

```shell
# debian
sudo passwd root
# 패스워드 지정
# 패스워드 재지정
```