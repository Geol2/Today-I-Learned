# Array 배열
- 같은 타입의 여러 변수를 하나의 묶음으로 다루는 것.

## 선언
- 배열을 다루기 위한 참조변수를 위한 공간이 만들어짐.
  - 타입[] 변수이름;
  - 타입 변수이름[];
```
int[] score;
String[] name;

int score[];
String name[];
```

## 생성
- 배열이 생성되면, 값을 저장할 수 있는 공간이 만들어진다.
  - 타입[] 변수이름;
  - 변수이름 = new 타입[길이];
```
int[] score;
score = new int[5];
```
![배열(1)](../images/배열(1).jpg)