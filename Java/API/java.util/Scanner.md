# print()

- 출력할 때 사용하기.

```java
System.out.printf("");
```

# Scanner

- 입력하기 위한 객체.

```java
import java.util.*;

Scanner sc = new Scanner(System.in);

String input = sc.nextLine();
int convInt = Integer.parseInt(input);
float convFloat = Float.parseFloat(input);
double convDouble = Double.parseDouble(input);

System.out.println(input);
System.out.println(convInt);
System.out.println(convFloat);
System.out.println(convDouble);

```

## hasNextLine()

- Boolean 타입으로 반환이 되며, 아래 예제를 쓴다면 항상 true 값을 반환한다.
- 위의 커맨드값을 주면 false를 반환해서 while문을 탈출시킬 수 있다.

## EOF

- WINDOWS
  - `CTRL` + `Z`
- Mac
  - `CTRL` + `D`

```java
while ( sc.hasNextLine() ) {
  String str = sc.nextLine();
  for(int i = 0; i < str.length(); i++) {
      // ...
  }
}
```
