# iptables 방화벽

```shell
iptables -V
```

## 방화벽 리스트 확인

```shell
iptables -nL
```

## IP 추가 및 수정

```shell
iptables -I INPUT [우선순위] -p tcp -s [추가할 아이피] ---dport [포트] -j ACCEPT

iptables -A INPUT -p tcp -s [추가할 아이피] ---dport [포트] -j ACCEPT
```

우선 순위가 필요할 때는, `-A` 대신 `-I`로 변경하도록 한다
