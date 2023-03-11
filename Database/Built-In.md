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