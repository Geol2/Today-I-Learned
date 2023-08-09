# 데이터베이스 리플리케이션

리플리케이션을 자동으로 세팅하는 쉘이다.

```bash
#!/bin/bash

# MySQL 서버 정보
HOST='' # 현재 서버의 MySQL 접속 호스트
USER=''
PASS=''

# Master 정보
MASTER_HOST='' # 현재 서버의 컴퓨터 아이피 넣기
MASTER_USER=''
MASTER_PASS=''
REPLI_USER=''
REPLI_PASS=''

# Get MASTER_LOG_FILE and MASTER_LOG_POS from the Master server
MASTER_STATUS=$(mysql -h $MASTER_HOST -u $MASTER_USER -p$MASTER_PASS -e "SHOW MASTER STATUS\G")
MASTER_LOG_FILE=$(echo "$MASTER_STATUS" | grep "File:" | awk '{ print $2 }')
MASTER_LOG_POS=$(echo "$MASTER_STATUS" | grep "Position:" | awk '{ print $2 }')

# MySQL 복제 상태 확인
SLAVE_STATUS=$(mysql -h $HOST -u $USER -p$PASS -e "SHOW SLAVE STATUS\G")

# 복제가 중지되었는지 확인
if echo $SLAVE_STATUS | grep -q "Slave_IO_Running: No\|Slave_SQL_Running: No"; then
    # 복제 재시작 시도
    mysql -h $HOST -u $USER -p$PASS <<-EOSQL
        STOP SLAVE;
        CHANGE MASTER TO MASTER_HOST='$MASTER_HOST', MASTER_USER='$REPLI_USER', MASTER_PASSWORD='$REPLI_PASS', MASTER_LOG_FILE='$MASTER_LOG_FILE', MASTER_LOG_POS=$MASTER_LOG_POS;
        START SLAVE;
EOSQL
    # 이메일 등을 통해 알림 보내기 (옵션)
    # echo "MySQL replication stopped. Trying to start again." | mail -s "MySQL Replication Alert" your-email@example.com
fi
```