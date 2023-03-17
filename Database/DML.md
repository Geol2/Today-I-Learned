# DML

## INSERT

테이블 내에 데이터를 추가할 때 많이 사용한다

```sql
INSERT INTO 테이블(칼럼1, 칼럼2, ...) 
          VALUES (값1, 값2, ...)
```

## UPDATE

테이블 내에 데이터를 일부 수정할 때 많이 사용한다

```sql
UPDATE 테이블 SET (칼럼1 = 값1, ...) WHERE 조건절
```

## DELETE, TRUNCATE, DROP

### DELETE 

- 테이블 내의 데이터를 삭제하기 위해 사용한다
- 저장공간과 저장구조가 유지된다

- 조건절을 안 붙이면 모든 데이터가 날라가니까 조심히 사용해야 한다
- `COMMIT`을 안했다면, `ROLLBACK`을 사용해서 복구시킬 수 있다

```sql
DELETE FROM 테이블명 WHERE 조건절
```

### TRUNCATE

- 테이블 내의 데이터를 삭제하기 위해 사용한다
- 저장공간이 삭제되고 저장구조는 유지한다
- `COMMIT`을 안해도 `ROLLBACK` 되지 않는다

```sql
TRUNCATE TABLE 테이블명
```

### DROP

- 테이블 내의 데이터, 저장공간, 저장구조를 삭제하기 위해 사용한다

```sql
DROP TABLE 테이블명
```

# ROLLBACK, COMMIT

- `COMMIT`: 변경된 데이터에 대해서 영구히 저장하게 된다
- `ROLLBACK`: `COMMIT` 되지 않은 데이터들을 복구한다

## MERGE

- `DELETE`, `UPDATE`, `INSERT`를 한번에 다 한다

```sql
MERGE INTO 테이블명 AS A USING 테이블명 AS B ON (A.컬럼명 = B.컬럼명)
WHEN MATCHED THEN
UPDATE SET A.컬럼명 = B.컬럼명 /* 나름대로 */
WHEN MATCHED THEN
INSERT (A.컬럼명, ...) VALUES (값1, ...) /* 나름대로 */
```

```sql
/* 사원 테이블에 부서명 컬럼을 추가하고 해당 사원의 부서명으로 값을 갱신하기 */
ALTER TABLE emp
ADD dname varchar2(10);

MERGE INTO emp e
USING dept d
ON (e.deptno = d.deptno)
WHEN MATCHED THEN
UPDATE SET e.dname = d.dname;
```

## ROCK

DBMS에 접속된 세션1과 세션2가 있을 때, 

세션1에서 행 하나를 업데이트 하고 세션2에서 또 업데이트하면 세션2는 해당 행 전체에 락에 걸린 상태로 빠진다.

세션1에서 커밋이나 롤백을 완료하면 세션2는 락이 풀린다.

