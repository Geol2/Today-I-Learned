# print()
- 출력할 때 사용하기.
```java
System.out.printf("");
```

# Scanner
- 입력하기 위한 객체.
```java
import java.util.*;

Scanner scanner = new Scanner(System.in); // Scanner 객체를 생성.

String input = scanner.nextLine(); // 입력받은 내용을 input에 저장.
int num = Integer.parseInt(input); // 입력받은 내용을 int로 변환.
```