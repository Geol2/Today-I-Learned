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

운영 서버에 해당 `print`를 사용하게 된다면, 애플리케이션 전체에 대기시간이 발생해서 성능저하가 생길 수 있으므로 로깅(log4j) 같은 라이브러리를 사용하는 것을 권장한다.