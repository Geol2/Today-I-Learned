# 서브쿼리

메인쿼리 안에 서브쿼리가 존재한다

- 단일행 서브쿼리

```sql
/* JONES 보다 월급을 더 많이 받는 사원들의 이름과 월급을 출력 */
SELECT ename, sal FROM emp
WHERE sal > (SELECT sal
                FROM emp
                WHERE ename = 'JONES');

/* ALLEN 보다 더 늦게 입사한 사원들의 이름과 월급을 출력 */
SELECT ename, sal 
FROM emp 
WHERE hiredate > ( SELECT
                    hiredate FROM emp
                    WHERE ename = 'ALLEN');
```

- 다중행 서브쿼리 : 서브쿼리에서 여러 행이 리턴될 경우

```sql
/* 직업이 SALESMAN 인 사람 중에 같은 월급인 사람의 이름과 월급을 출력 */
SELECT ename, sal 
FROM emp
WHERE sal IN ( SELECT sal 
                FROM emp
                WHERE job = 'SALESMAN'
            );
```

```sql
/* 부서번호가 20번인 사람 중에 같은 직업을 가진 사람의 이름과 직업을 출력 */
SELECT ename, job
FROM emp
WHERE job IN ( SELECT job
                FROM emp
                WHERE deptno = 20 );
```

### 서브쿼리가 올 수 있는 절
- SELECT
- FROM
- WHERE
- HAVING
- ORDER BY

## NOT IN

같지 않고 AND 연결을 함으로 null 데이터를 주의한다

```sql
/* 관리자인 사원들의 이름을 출력 */
SELECT empno, ename, mqr FROM emp;

SELECT ename
FROM emp
WHERE empno IN (SELECT mqr FROM emp);

/* 관리자가 아닌 사원들의 이름을 출력 */
SELECT ename
FROM emp
WHERE empno NOT IN (SELECT nvl(mqr, -1) FROM emp);
```

## EXIST, NOT EXIST

```sql
/* 부서 테이블에 있는 부서번호 중에서 사원 테이블에 존재하지 않는 부서번호에 대한 모든 컬럼을 출력하세요 */
SELECT * FROM dept d
WHERE NOT EXISTS ( SELECT * 
                    FROM emp e
                    WHERE d.deptno = e.deptno );
```

## HAVING 절의 서브쿼리

그룹 함수의 조건은 `WHERE`에 넣지 않는다

```sql
/* 직업과 직업별 총 월급을 출력하는데 직업이 SALESMAN 인 사원들의 총 월급보다 더 큰 것만 출력되게 하시오 */
SELECT job, sum(sal)
FROM emp
GROUP BY job
HAVING SUM(sal) > ( SELECT sum(sal)
                    FROM emp
                    WHERE job = 'SALESMAN');

/* 부서번호, 부서번호별 인원수를 출력하는데 10번 부서번호의 인원수보다 더 큰 것만 출력하라 */
SELECT deptno, count(*)
FROM emp
GROUP BY deptno
HAVING count(*) > ( SELECT count(*)
                    FROM emp WHERE deptno = 10 );
```

## FROM 절의 서브쿼리

```sql
SELECT * 
FROM ( SELECT ename, 
              sal, 
              rank() over (order by sal desc) rnk
        FROM emp )
WHERE rnk = 1;

/* 직업이 SALESMAN인 사원들 중에서 가장 먼저 입사한 사원의 이름과 입사일을 출력 */
SELECT *
from (SELECT ename, 
             hiredate, 
             rank() over (ORDER BY hiredate asc) rnk
        FROM emp e
        WHERE job = 'SALESMAN')
WHERE rnk = 1;
```

## SELECT 절의 서브쿼리

```sql
/* 직업이 SALESMAN 인 사원들의 이름과 월급을 출력하면서
 * 그 옆에 직업이 SALESMAN인 사원들의 최대월급과 최소월급을 출력되게 하세요 */
SELECT ename, sal, 
    (SELECT max(sal) FROM emp WHERE job = 'SALESMAN') as max,
    (SELECT min(sal) FROM emp WHERE job = 'SALESMAN') as min
FROM emp
where job = 'SALESMAN';

/* 부서번호가 20번인 사원들의 이름과 월급을 출력하고 
 * 그 옆에 20번 부서번호인 사원들의 평균월급이 출력되게 하세요 */
SELECT ename, 
    sal,
    (SELECT avg(sal) FROM emp WHERE deptno = 20) as 평균
FROM emp
WHERE deptno = 20;
```

## SELECT FOR UPDATE 절

조회하는 동안 다른 누구도 데이터를 갱신할 수 없게 할 수 있다

```sql
/* 락을 걸면서 수행 */
SELECT ename, sal, deptno
FROM emp
WHERE ename = 'JONES';
FOR UPDATE;

/* 락이 걸려서 다른 세션에서 수행할 수 없음 */
UPDATE empp
SET sal = 9000
WHERE ename = 'JONES';
```

```sql
/* 부서번호가 10번인 emp테이블의 데이터를 emp2 테이블에 넣는다 */
INSERT INTO emp2 (empno, ename, sal, deptno)
SELECT empno, ename, sal, deptno
FROM emp
WHERE deptno = 10;
```

