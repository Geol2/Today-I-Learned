# CRUD

`INSERT INTO 테이블(칼럼1, 칼럼2, ...) VALUES (값1, 값2, ...)`

## SELECT
```sql
SELECT 컬럼명 FROM 테이블;

SELECT 컬럼명 || '의 직업은 ' || 컬럼명 || ' 입니다.' FROM emp;

SELECT 컬럼명 FROM 테이블 ORDER BY 2 asc;

SELECT 컬럼명 FROM 테이블 WHERE 컬럼명 = '조건';
```

`UPDATE 테이블 SET 칼럼1=값1 WHERE 조건절`

`DELETE FROM 테이블 WHERE 조건절`