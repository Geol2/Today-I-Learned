# ORDER BY 문장

- 다양한 목적에 맞게 특정 칼럼을 기준으로 정렬, 출력하는데 사용한다.
- 컬럼 명 대신 별칭이나 정수로 사용할 수 있다.
- 기본적으로 오름차순(ASC) 형태로 적용이 되며 아래와 같이 마지막에 넣어준다.

```
5. SELECT 칼럼명 [ALIAS 이름]
1. FROM 테이블명
2. [WHERE 조건식]
3. [GROUP BY 칼럼이나 표현식]
4. [HAVING 그룹조건식]
6. [ORDER BY 칼럼이나 표현식 [ASC | DESC]]
```
- 문장 실행 순서에 대해선 알아두면 도움이 된다.
  - FROM - WHERE - GROUPBY - HAVING - SELECT - ORDERBY 
