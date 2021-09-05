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