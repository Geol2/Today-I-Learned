# System

시스템 클래스는 인스턴스화할 수 없다

표준 입력과 출력, 오류 출력 스트림이 존재한다.

## System.out.print 를 권장하지 않는 이유?

PrintStream 클래스로 이동이 되는데, java.io 패키지

```java
public class PrintStream extends FilterOutputStream implements Appendable, Closeable
```

print, println 함수내부를 들여다보면 다음과 같다

```java
public void print(String s) {
    write(String.valueOf(s));
}

public void println() {
    newLine();
}

public void println(boolean x) {
    synchronized (this) {
        print(x);
        newLine();
    }
}
```

write, newline 함수 내부를 보자

```java
private void write(String s) {
    try {
        synchronized (this) {
            ensureOpen();
            textOut.write(s);
            textOut.flushBuffer();
            charOut.flushBuffer();
            if (autoFlush && (s.indexOf('\n') >= 0))
                out.flush();
        }
    }
    catch (InterruptedIOException x) {
        Thread.currentThread().interrupt();
    }
    catch (IOException x) {
        trouble = true;
    }
}

private void newLine() {
    try {
        synchronized (this) {
            ensureOpen();
            textOut.newLine();
            textOut.flushBuffer();
            charOut.flushBuffer();
            if (autoFlush)
                out.flush();
        }
    }
    catch (InterruptedIOException x) {
        Thread.currentThread().interrupt();
    }
    catch (IOException x) {
        trouble = true;
    }
}
```

각 메소드나 메소드 내부에 synchronized를 사용하고 있다.

운영 서버에 해당 `print`를 사용하게 된다면, 

애플리케이션 전체에 대기시간이 발생해서 성능저하가 생길 수 있으므로 로깅(log4j) 같은 라이브러리를 사용하는 것을 권장한다.

시간저하의 원인이 되기도 한다.

## `BufferedWriter`을 사용 VS `System.out.println()`을 사용

  - ArrayList와 int[] 차이에서도 메모리공간이 다른 것이 확인됨
  - 10건의 데이터를 확인해도 이정도의 차이면 수천건, 수만건일 경우 이슈가 발생될 수 있음

```java
import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int number = Integer.parseInt(br.readLine());
        int[] arr = new int[number];
        for(int i = 0; i < number; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        for(int i = 0 ; i < number ; i++) {
            bw.write(arr[i] + "\n");
        }

        br.close();
        bw.flush();
        bw.close();
    }
}
// 약 2600ms 소요시간
// 2148624 byte 메모리공간 소요
```

ArrayList와 컬렉션에서 sort() 함수를 사용하였다.

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int number;
        int input;
        ArrayList<Integer> arr = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        number = sc.nextInt();
        for(int i = 0; i < number; i++) {
            input = sc.nextInt();
            arr.add(input);
        }

        Collections.sort(arr);
        for (Integer integer : arr) {
            System.out.println(integer);
        }
    }
}

// 약 3000ms 초과 (시간 제한으로 인한 Fail)
// 2370696 byte 메모리공간 소요
```

위 코드의 내용은 결과는 동일하지만, 시간과 메모리의 차이가 존재한다.

