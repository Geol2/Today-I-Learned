# Hosts 파일

### OS별 파일위치

- Windows : %SystemRoot%System32\drivers\etc\hosts
- Unix, Mac : /etc/hosts, /private/etc/hosts

형태는 보통 다음과 같다

```
아이피주소 도메인이름
```

아이피주소와 도메인이름이 대응되어 있어서 DNS에서 주소 정보를 제공받지 않아도 서버의 위치를 찾게 해준다

설정한 컴퓨터 내에서만 해당되는거라 웹 상에서 무조건 되는 것은 아닌데

아주아주 옛날에는 이렇게? 사용했다고 한다

해당 파일을 마음대로 바꾸는 것은 보안 취약점에 노출될 수 있어서 조심해야한다

