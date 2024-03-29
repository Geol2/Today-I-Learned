# INDEX

데이터 검색을 높이기 위해서 사용

```sql
/* INDEX - 데이터 검색 속도를 높이기 */
-- FULL ACCESS TABLE
EXPLAIN PLAN FOR 
SELECT ename, sal
FROM emp
WHERE sal = 3000;

SELECT * FROM TABLE(dbms_xplan.display); -- 실행계획

CREATE INDEX emp_sal
ON emp(sal);

SELECT ename, sal
FROM emp
WHERE sal = 3000;

SELECT * FROM TABLE(dbms_xplan.display);

-- 물리적인 주소를 담고 있는 rowid, 인덱스를 보고 싶을 떄 사용
SELECT rowid, empno, ename
FROM emp;

rollback;
```

```sql
/* 사원테이블에 직업에 인덱스를 생성하시오 */
CREATE INDEX emp_job
on emp(job);

SELECT ename, job
FROM emp
WHERE job = 'PRESIDENT';

EXPLAIN PLAN FOR
SELECT ename, job
FROM emp
WHERE job = 'PRESIDENT';

SELECT * FROM TABLE(dbms_xplan.display);
```

## FK (Foreign key)의 인덱스

FK 생성 여부에 관계없이 조인 성능을 향상시키기 위한 인덱스 생성을 해주는 것이 좋다

## 페이징 스킬

### 보통 페이징 처리

```sql
SELECT * FROM 테이블명 LIMIT (숫자 A), (숫자 B)
```

### 인덱스 페이징 처리

```sql
SELECT * FROM 테이블명 LIMIT (숫자 A) OFFSET (숫자 B)
```
B번째부터 A개수까지 가져오는 방법

- OFFSET : 풀 스캔 방식으로 모든 레코드를 조회한 뒤, 범위를 조회하기 때문에 크게 설정하면 속도가 느리게 된다

```sql
SELECT * FROM 테이블명 WHERE 컬럼 >= (숫자 A) LIMIT (숫자 B)
```
A 부터 B 개수만큼 가져오는 방법

offset을 사용하지 않고 더 빠르게 가져올 수 있다

# SEQUENCE 

고유번호를 생성시키기 위해서 사용

```sql
/* 사원번호에 번호를 입력할 때 데이터가 중복되지 않고 순서대로 입력되게 하세요 */
CREATE SEQUENCE seq1;

SELECT seq1.nextval
FROM dual;

-- 1부터 1씩 증가시키면서 100번까지만..
CREATE SEQUENCE seq2
START WITH 1
MAXVALUE 100
INCREMENT BY 1
NOCYCLE;

SELECT seq2.nextval
FROM dual;
```