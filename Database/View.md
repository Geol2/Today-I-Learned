# VIEW

일부 데이터만 보게 하기 위해서 사용

```sql
/* 사원테이블에서 직업이 SALESMAN인 사원들의 사원번호, 사원이름, 
   월급, 직업, 관리자번호, 부서번호만 출력하는 뷰를 생성 */
CREATE VIEW emp_view
AS
SELECT empno, ename, sal, job, deptno
FROM emp
WHERE job = 'SALESMAN';

SELECT * FROM emp_view;
```

```sql
-- 뷰가 변경이 되면 테이블의 데이터도 변경이 된다

/* 사원테이블에서 부서번호가 20번 사원들의 사원번호, 사원이름, 
   월급, 직업, 관리자번호, 부서번호만 출력하는 뷰를 생성 */
CREATE VIEW emp_view2
AS
SELECT empno, ename, sal, job, deptno
FROM emp
WHERE deptno = 20;

SELECT * FROM emp_view2;
```

```sql
/* 사원 테이블에서 부서번호별 평균 월급을 출력해주는 뷰를 생성 */
CREATE VIEW emp_view3
AS
SELECT deptno, round(avg(sal)) AS avgsal -- 그룹함수의 별칭이 꼭 필요
FROM emp
GROUP BY deptno;

SELECT * FROM emp_view3;
```