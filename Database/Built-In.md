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