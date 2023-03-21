# FLASHBACK

rollback을 할 수 없는 데이터를 복구시키기 위한 방법이다

하지만, 일정 시간이 지나면 복구할 수 없다는 한계점이 있다

```sql
/* 실수로 지운 데이터 복구하기 FLASHBACK */
SELECT * FROM emp;

DELETE FROM emp;
COMMIT;

rollback;

SELECT *
FROM emp
AS OF TIMESTAMP(systimestamp - INTERVAL '5' MINUTE); -- 5분전의 데이터

SELECT systimestamp
FROM dual; -- 현재시간

/* 복구 쿼리 */
INSERT INTO emp
SELECT *
FROM emp
AS OF TIMESTAMP(systimestamp - INTERVAL '5' MINUTE); -- 5분전의 데이터

SELECT * FROM emp;

COMMIT;

SHOW PARAMETER UNDO; -- 900초(15분) 내에 복구 가능(회사마다 다르게 세팅되어 있음)
```

## FLASHBACK TABLE

무료버전이면 안됨...

```sql
/* FLASHBACK TABLE */
SELECT * FROM emp;

DELETE FROM emp;

commit;

rollback;

SELECT * FROM emp;

ALTER TABLE emp ENABLE ROW MOVEMENT;

FLASHBACK TABLE emp TO TIMESTAMP(systimestamp - INTERVAL '10' MINUTE);

SELECT * FROM emp;

select parameter, value from v$option where value = 'FALSE' order by 1;

```

## FLASHBACK DROP

```sql
/* FLASHBACK DROP */
SELECT * FROM emp;

DROP TABLE emp;

SELECT * FROM emp;

SELECT * FROM user_recyclebin
ORDER BY droptime DESC;

FLASHBACK TABLE emp TO BEFORE DROP;

purge recyclebin; -- 휴지통 비우기
```