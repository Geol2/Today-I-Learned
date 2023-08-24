# SSH auth

깃이나 소스트리, 빗버킷 등에 git clone, pull, push 등을 위해서 ssh 공개키, 비밀키를 만들어서 플랫폼에 인증시켜서 사용할 수 있는 방식이 존재하는 것 같다

PC 별로 별도의 인증키를 가지고 등록을 해주어야 했다

별의별 오류를 만나서 되는대로 정리를 해보았다

## 빗버킷 리눅스

OpenSSH 인증을 위해서 맥이나 리눅스 서버에선 다음과 같은 패키지가 설치되어있어야 했다

```shell
apt install openssh-client # 보통 설치되어 있다

ssh -V

ps -aux c | grep ssh-agent

eval $(ssh-agent) # 한번도 실행한 적이 없다면 꼭 해주어야 한다
```

.ssh 가 있는 폴더를 찾아야 하는데

보통 `/home/계정/.ssh`(보통여기에 있는 듯), `/root/.ssh`, `/www/.ssh`에 존재해서 잘 찾아본다

.ssh로 이동했다면 다음과 같은 명령어를 만들어준다

```shell
ssh-keygen -t ed25519 -b 4096 -C "big9401@gmail.com" -f geol_ed25529
```

하면 두 가지의 파일이 만들어진다

```
geol_ed25529
geol_ed25529.pub
```

첫 번째 파일은 비밀키, 두 번째 파일은 공개키가 되고

비밀키를 추가시킨다

```shell
ssh-add geol_ed25529
```

.ssh/config 파일을 생성해서 다음과 같은 코드를 추가시킨다

```
Host bitbucket.org
    AddKeysToAgent yes
    IdentityFile geol_ed25529
```

이제 빗버킷에 접속해서 개인 빗버킷 세팅에서 ssh key를 추가시켜주어야 한다

```shell
cat geol_id25519.pub
#########################
ssh-ed25529 쏼라쏼라 big401@gmail.com
```

명령어를 통해 나오는 공개키를 ssh key로 추가하고 본인이 충분히 어떤건지 식별할 수 있도록 설정한다

```shell
ssh-add -l
```

해당 명령어로 잘 등록이 되어있는지 확인하고 안되어 있다면 다음과 같은 명령을 실행한다

```
ssh-add geol_ed25529
```

### 접속 테스트

```shell
ssh -T git@bitbucket.org

Connection to bitbucket.org closed
Transfered: sent 2472, recived 2164 btyes, in 0.4 seconds
Bytes per second : sent 5559.2, received 4866.5
```

해당 요청을 응답해준다면 완료된 것이고 등록되었다는 메일과 함께 빗버킷 설정에서 수정할 수 없게 된다

하지만 안되는 경우도 있는 것 같아서 자료를 좀 탐색해보았다

빗버킷의 호스트 사이트키가 변경되어 안되는 경우도 존재했었던 것으로 추측하는데

```shell
ssh-keygen -R bitbucket.org && curl https://bitbucket.org/site/ssh >> ~/.ssh/known_host
```

해당 명령어를 실행하니 문제없이 잘 되었다


마지막으로 clone, pull, push가 이상없이 잘 작동하는지 확인한다

