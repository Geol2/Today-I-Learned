# GRANT

DBMS 계정에 권한을 부여할 때 사용하는 명령어

```sql
# MySql
GRANT 권한목록 ON 데이터베이스.대상 TO '계정'@'호스트'

# Oracle
GRANT DBA to '계정'; # DBA로 권한을 할당해줌
CONNECT '계정'/'암호'; # 접속이 가능하디
```

- 권한목록
  - select, update, delete, create, drop, all privileges

- 호스트
  - % 일 경우, 모든 호스트에서 해당 계정으로 접근할 때 해당 암호를 사용
  - localhost와 다른 호스트를 구분하므로 사용자 계정을 생성할 때, localhost용 계정과 외부에서 접근할 때 사용할 계정을 각각 생성해주어야 한다.