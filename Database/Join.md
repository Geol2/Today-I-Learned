# Join
두 개 이상의 테이블들을 연결해 데이터를 출력하는 것을 조인이라고 한다.

- PK (Primary key 주요키): 학생과 수업의 테이블에서 각 행의 정보들을 식별할 수 있는 정보(학생코드, 수업코드)를 표현. 테이블 행의 여러 정보들 중 행을 식별할 수 있어야 하는 때문에 비어있으면 안되고(NOT NULL) 중복되어서도 안된다(Unique). 그리고 식별을 할 때 테이블의 정보를 최대한 빠르게 검색해야 하므로 간단한 정보

- FK (Foreign key 외래키): 참조하는 테이블과 참조되는 테이블의 관계. 학생-수업 테이블은 학생테이블과 수업테이블의 관계를 1:N 관계로 나타내기위한 테이블이므로 학생테이블과 수업테이블을 참조하여 만들어야 한다.

- 선수 테이블과 팀 테이블이 존재한다면, 선수 테이블을 기준으로 필요한 데이터를 검색하고 이 데이터와 연관된 팀 테이블의 특정 행들을 찾아서 연결하는 것이 조인.

## EQUI JOIN
- 두 테이블 간에 칼럼 값들이 서로 정확하게 일치하는 경우에 사용되는 방법인데 PK <-> FK 의 관계로만 성립하는 것은 아니지만 기본적 기반을 가진다.

```SQL
SELECT 테이블1.칼럼명, 테이블2.칼럼명, ...
FROM 테이블1, 테이블2
WHERE 테이블2.칼럼명 = 테이블1.칼럼명
```

ANSI/SQL
```SQL
SELECT 테이블1.칼럼명, 테이블2.칼럼명, ...
FROM 테이블1, 테이블2
ON 테이블2.칼럼명 = 테이블1.칼럼명
```

Example. Dummy Data
```SQL
- CREATE TABLE PLAYER (
	PLAYER_NAME CHAR(5) NOT NULL,
    BACK_NO INT(2) NOT NULL,
    TEAM_ID CHAR(4)
);
DROP TABLE PLAYER;

- INSERT INTO PLAYER VALUES
('이고르', 21, 'K06'),
('오비나', 26, 'K10'),
('윤원일', 45, 'K02'),
('페르난도', 44, 'K04'),
('레오', 45, 'K03'),
('실바', 45, 'K07'),
('무스타파', 77, 'K04'),
('에디', 7, 'K01'),
('알리송', 14, 'K014'),
('쟈스민', 33, 'K08'),
('디디', 8, 'K06');

- CREATE TABLE TEAM (
	TEAM_ID CHAR(4) NOT NULL,
    TEAM_NAME CHAR(10) NOT NULL,
    REGION_NAME CHAR(4)
);
DROP TABLE TEAM;

- INSERT INTO TEAM VALUES
('K05', '현대모터스', '전북'),
('K08', '일화천마', '성남'),
('K03', '스틸러스', '포항'),
('K07', '드래곤즈', '전남'),
('K09', 'FC서울', '서울'),
('K04', '유나이티드', '인천'),
('K11', '경남FC', '경남'),
('K01', '울산현대', '울산'),
('K10', '시티즌', '대전'),
('K02', '삼성블루윙즈', '수원'),
('K12', '광주상무', '광주'),
('K06', '아이파크', '부산'),
('K13', '강원FC', '강원'),
('K14', '제주유나이티드FC', '재주'),
('K15', '대구FC', '대구');

- SELECT PLAYER_NAME, PLAYER.BACK_NO, PLAYER.TEAM_ID, TEAM.TEAM_NAME, TEAM.REGION_NAME
FROM PLAYER, TEAM
WHERE TEAM.TEAM_ID = PLAYER.TEAM_ID;
```

## Non EQUI JOIN
- 두 개의 테이블 간에 논리적인 연관 관계를 갖고 있으나, 칼럼 값들이 서로 정확하게 일치하지 않는 경우에 사용된다.
- 데이터 모델에 따라 Non EQUI JOIN이 불가능한 경우도 있다.

```SQL
SELECT 테이블1.칼럼명, 테이블2.칼럼명, ...
FROM 테이블1, 테이블2
WHERE 테이블1.칼럼명 BETWEEN 테이블2.칼럼명1 AND 테이블2.칼럼명2;
```


- EMP 테이블

| ENAME  | SAL  | 
|:------:|:----:|
| SMITH  | 800  |
| ALLEN  | 1600 |
| WARD   | 1250 |
| JONES  | 2975 |
| MARTIN | 1250 |
| BLAKE  | 2850 |
| CLARK  | 2450 |
| SCOTT  | 3000 |
| KING   | 5000 |

- SALGRADE 테이블

| GRADE  | LOSAL| HISAL | 
|:------:|:----:|:-----:|
| 1      | 700  | 1200  |
| 2      | 1201 | 1400  |
| 3      | 1401 | 2000  |
| 4      | 2001 | 3000  |
| 5      | 3001 | 9999  |

- 위 두 테이블을 아래 구문을 실행하면,
```SQL
SELECT A.ENAME, A.JOB, B.GRADE
FROM EMP A, SALGRADE B
WHERE A.SAL BETWEEN B.LOCAL AND B.HISAL;
```

| ENAME  | SAL  |  GRADE | LOSAL  | HISAL |
|:------:|:----:|:------:|:------:|:-----:|
| SMITH  | 800  | 1      | 700    | 1200  |
| ALLEN  | 1600 | 3      | 1401   | 2000  |
| WARD   | 1250 | 2      | 1201   | 1400  |
| JONES  | 2975 | 4      | 2001   | 3000  |
| MARTIN | 1250 | 2      | 1201   | 1400  |
| BLAKE  | 2850 | 4      | 2001   | 3000  |
| CLARK  | 2450 | 4      | 2001   | 3000  |
| SCOTT  | 3000 | 4      | 2001   | 3000  |
| KING   | 5000 | 5      | 3001   | 9999  |