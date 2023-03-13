# 내장 함수

## lower(), upper(), initcap() : 문자열

- lower : 모두 소문자
- upper : 모두 대문자
- initcap : 첫 글자만 대문자, 나머지는 소문자

```sql
/* Oracle */
SELECT ename, sal FROM  emp WHERE lower(ename) = 'scott';

select lower(substr(ename, 1, 1)) from emp;
```

## length(컬럼이나 문자열) : 길이 수
```sql
/* Oracle */
select ename, length(ename) from emp where length(ename) >= 5;
```

## instr(컬럼이나 문자열, 찾을 문자열) : 찾을 문자열의 인덱스 번호
```sql
/* Oracle */
select ename, instr(ename, 'S') from emp where instr(ename, 'S') > 0;
```

## replace(지정할 컬럼이나 문자열, 변경될 문자열, 변경할 문자열) : 문자열
```sql
/* Oracle */
select ename, regexp_replace(sal, '[0-3]', '*') from emp;
```

## lpad(컬럼명, 출력할 전체 자릿수, 왼쪽에 채워넣을 문자), rpad(컬럼명, 출력할 전체 자릿수, 오른쪽에 채워넣을 문자) : 문자열
```sql
/* Oracle */
select ename, rpad(sal, 10, '*') from emp; 
```

## next_day(sysdate, '요일')

오늘부터 돌아올 요일을 출력

## last_day('년도/월/일')

마지막 날의 날짜를 출력

```sql
/* Oracle - 몇 일이 남았는지 확인하고 싶을 때 */
SELECT last_day(sysdate) - sysdate from emp;
```

## to_char(날짜 컬럼명, '날짜 포맷')

- 년도 : RRRR, YYYY, RR, YY
- 달 : MON, YY
- 일 : DD
- 요일 : DAY, DY

## to_date('날짜', '작성한 날짜의 형식')

날짜 포맷으로 출력해줌

해당 날짜까지 포함하기 위해서 `to_date('날짜', '작성한 날짜의 형식') + 1` 까지 나타낼 수 있다

## explain for 쿼리 질의문

실행 계획을 출력해준다

## nvl(컬럼명, 원하는 값)

null 대신 원하는 값으로 변경해주는 내장함수

# 조건

```sql
/* Oracle */
SELECT nvl(comm, 0) FROM emp;
/* 숫자 + null = null 이므로 다음과 같은 표현을 통해 출력할 수 있다. */
```

## decode(컬럼명, 조건1, 변경값1, 조건2, 변경값2, ..., 나머지)

```sql
/* Oracle equals 비교를 위해서 사용 */
SELECT decode(dptno, 10, 300, 20, 400, 0) as 보너스 FROM emp;
```

## CASE WHEN THEN END

```sql
/* Oracle */
SELECT 컬럼명 CASE WHEN 컬럼명 조건1 THEN 변경값1 
                 WHEN 컬럼명 조건2 THEN 변경값2
                 ELSE 나머지값 END;

SELECT ename, sal, CASE WHEN sal >= 3000 THEN 9000
                        WHEN sal >= 2000 THEN 8000
                        ELSE 0 END as 보너스
    FROM emp;
```

# 그룹함수

- 그룹함수의 조건을 이용할 땐 HAVING절을 사용한다
- 그룹함수는 null 값을 무시한다

## MAX(컬럼명), MIN(컬럼명)

```sql
/* Oracle */
SELECT job, max(sal)
FROM emp
WHERE job = 'SALESMAN'
GROUP BY job;

SELECT deptno, min(sal)
FROM emp
GROUP BY deptno;
```

## AVG(컬럼명)

```sql
/* Oracle */
SELECT ename, job, avg(sal) FROM emp GROUP BY sal;
```

## SUM(컬럼명)

```sql
/* Oracle */
SELECT ename, sum(sal) FROM emp HAVING sum(sal) >= 6000
```

## COUNT(컬럼명)

```sql
/* Oracle */
SELECT ename, count(*) 
FROM emp 
WHERE job != 'SALESMAN'
GROUP BY job
HAVING count(*) >= 3;
```

# 데이터 분석 함수

## RANK() OVER (ORDER BY 컬럼명 DESC)

```sql
SELECT ename, deptno, sal, RANK() OVER (ORDER BY sal desc) FROM emp WHERE deptno = 20;
```
1등이 두 명이면, 그 다음이 2등이 안나오고 3등으로 나온다

## DENSE_RANK() OVER (ORDER BY 컬럼명 DESC)

```sql
SELECT ename, deptno, sal, DENSE_RANK() OVER (ORDER BY sal desc) FROM emp WHERE deptno = 20;
```

## DENSE_RANK(기준값) WITHIN GROUP (ORDER BY 컬럼명 DESC)

- 기준값을 기준으로 순위를 표시할 수 있음

```sql
SELECT ename, deptno, sal, DENSE_RANK(2950) WITHIN GROUP (ORDER BY sal desc) FROM emp;
```

## NTILE(등급을 나눌 수) OVER (ORDER BY 기준컬럼 DESC)

```sql
SELECT ename, hiredate, NTILE(5) OVER (ORDER BY hiredate ASC) FROM emp;
```

## CUME_DIST() OVER (ORDER BY 기준컬럼 DESC)

- 비율을 순위로 나타내고자 할 때 사용할 수 있다

```sql
SELECT deptno, ename, hiredate, CUME_DIST() OVER (PARTITION BY deptno ORDER BY sal DESC) FROM emp;
```

## LIST_AGG(컬럼명, '기호') WITHIN GROUP (ORDER BY 기준컬럼)

- 가로로 출력할 수 있게 한다

```sql
/* Oracle */
SELECT deptno, list_agg(ename, ',') WITHIN GROUP (ORDER BY sal DESC) FROM emp GROUP BY job;
```

## LEAD,LAG(컬럼명, 행번호) OVER (ORDER BY 기준 컬럼)

```sql
/* Oracle */
SELECT ename, sal, lag(sal, 1) OVER (ORDER BY sal DESC) 전행 ,
                   lead(sal, 1) OVER (ORDER BY sal DESC) 후행
FROM emp WHERE job IN ('ANALYST', 'MANAGER');

SELECT ename, hiredate - lag(hiredate, 1) OVER (ORDER BY hiredate ASC) 간격일
FROM emp;
```

## ROW 를 COLUMN 으로 출력하기 (DECODE + SUM)

```sql
/* Oracle - 직업과 직업별 토탈월급을 출력 */
SELECT job, sum(sal) FROM emp GROUP BY job;

/* 바꾸기 */
SELECT sum(decode(job, 'ANALYST', sal)) "ANALYST",
       sum(decode(job, 'CLERK', sal)) "CLERK",
       sum(decode(job, 'SALESMAN', sal)) "SALESMAN",
       sum(decode(job, 'PRESIDENT', sal)) "PRESIDENT"
FROM emp;
```

## ROW 를 COLUMN 으로 출력하기 (PIVOT)

