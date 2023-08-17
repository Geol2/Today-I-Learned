# Binary Log

mysql-bin.xxxxxx 번호로 /var/log/mysql 내부에 차근차근 쌓여있다

해당 로그를 통해서 데이터 복구도 가능하지만 간혹 용량 문제를 야기하곤 해서 해결방법이 필요하다

## 바이너리 로그 세팅

my.cnf
```
expire_logs_days = 10
binlog_expire_logs_seconds = 864000
```
10일 동안 저장이 가능하다

expire_logs_days = 0 이라면 삭제를 하지 않는다

### 해당 환경 세팅은 저장하고 재시작해도 mysql에서 확인하면 적용이 안되는 것 같다..

```sql
SHOW GLOBAL VARIABLE LIKE '%expire%'; # 일자와 초단위까지 확인이 가능하다
SET GLOBAL expire_logs_days = 2; # 2일로 설정
```



## 바이너리 로그 삭제

바이너리 로그를 삭제할 때 `rm -rf` 같은 명령을 사용해서 삭제처리를 하는 것보단 mysql 에 접속 한 뒤에 삭제 쿼리를 통해서 삭제하는 것이 안전하다고 한다

```sql
SHOW BINARY LOGS;
PURGE BINARY LOGS to 'mysql-bin.xxxxxx';
```

