# 스프링부트 갱신 방법

```shell
sudo certbot renew --dry-run
# error

sudo certbot certonly --standalone

# 갱신하려는 내 도메인 입력
# 비밀번호 입력
# 비밀번호 재입력

# /etc/letsoncrypt/live 내에 생성된 pem 확인
```

스프링부트는 별도 아파치에 pem을 넣어 동작시키는 방식이 아니므로 PKCS12 방식으로 변환이 필요하다.

pem키가 생성된 곳에서 다음과 같은 명령어를 실행해준다.

```shell
sudo openssl pkcs12 -export -in fullchain.pem -inkey privkey.pem -out keystore.p12 -name ttp -CAfile chain.pem -caname root
```
그럼 `keystore.p12`란 파일이 생성되고 이것을 스프링부트 프로젝트내에 넣어주도록 한다.

파일질라 등 도구의 `sftp` 나 `ftp`로 옮겨주도록 한다.

```shell
sudo certbot renew
```
재등록이 완료되었는지 확인한다. 축하한다고 뜨면 성공이다.

## 스프링에서의 PKCS12 적용 방법

`application.properties`는 아래와 같이 정의함

```
server.port=443
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store=type=PKCS12
server.ssl.key-store-password= 내가 설정한 비밀번호
```

`keystore.12` 파일은 `/src/resources/keystore.p12` 에 위치시켜주었다.

스프링부트를 일단 꺼준다.

그리고 재빌드 후, 나오는 jar파일을 서버에 다시 넣어주고 프로젝트를 실행시켜준다.

ssl 적용이 잘 되었는지 확인한다.