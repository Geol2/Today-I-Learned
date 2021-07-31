# CASE ... WHEN
- CASE 표현은 IF-THEN_ELSE 논리와 유사한 방식으로 표현식을 작성해 비교 연산 기능을 보완하는 역할을 한다.

```sql
CASE
  SIMPLE_CASE_EXPRESSION 조건 THEN 절 
  [ELSE 디폴트값]
END
```
```sql
CASE
  or SEARCHED_CASE_EXPRESSION 조건 THEN 절
  [ELSE 디폴트값]
END
```

- example
```sql
SELECT ENAME,
  , CASE
      WHEN SAL >= 3000 THEN 'HIGH'
      WHEN SAL >= 1000 THEN 'MID'
      ELSE 'LOW'
    END AS SALARY_GRADE
  FROM EMP;
```
- output
```
ENAME      SALRARY_GRADE
-----      -------------
SMITH      LOW
ALLEN      MID
JONES      MID
SCOTT      MID
CLARK      LOW
ADAMS      HIGHT
JAMES      LOW
```