# Operator

## * 연산자

```sql
/* Oracle */
SELECT ename, sal*12 FROM emp WHERE job = 'ANALYST';
```

## !=, ^= 연산자
```sql
/* Oracle */
SELECT ename, job FROM emp WHERE job != 'SALESMAN';
```

## BETWEEN, AND, OR, >, <, <=, >= 연산자
```sql
/* Oracle */
SELECT ename, sal FROM emp WHERE sal NOT BETWEEN 1000 AND 3000;
SELECT ename, sal FROM emp WHERE sal < 1000 OR sal > 3000;

SELECT ename, hiredate FROM emp WHERE hiredate BETWEEN '81/11/01' AND '82/05/30';

SELECT ename, sal, comm FROM emp WHERE deptno = 30 AND comm >= 100;
```

## %, _ 연산자
- % : 자릿수에 관계없이 모두 검색
- _ : 한 자리만 찾아서 검색
```sql
SELECT ename FROM emp WHERE ename LIKE '%T';

SELECT ename FROM emp WHERE ename LIKE '_M%';
```

## IS NOT NULL, IS NULL
```sql
/* Oracle
   - 비교 연산자를 사용하지 말기 0 과 NULL은 다름 */
SELECT ename, comm FROM emp WHERE comm is NOT NULL;
```

## IN 연산자
```sql
/* Oracle */
SELECT ename, sal FROM emp WHERE job NOT IN ('SALESMAN', 'ANALYST', 'MANAGER');
```