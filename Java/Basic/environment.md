# Environment
  - javac.exe : 자바 컴파일러, 자바소스코드(.java)를 바이트코드(.class)로 컴파일.
  - java.exe : 자바 인터프리터, 컴파일러가 생성한 바이트코드를 해석하고 실행.
  - javap.exe : 역어셈블러, 컴파일된 클래스파일을 원래의 소스로 변환.

  - javadoc.exe : 자동문서생성기, 소스파일에 있는 주석을 이용하여 java API 문서과 같은 형식의 문서를 자동으로 생성한다.
  - jar.exe : 압축 프로그램, 클래스파일과 프로그램의 실행에 관련된 파일을 하나의 jar파일로 압축하거나 압축해제한다.

  - 바이트코드? JVM이 이해할 수 있는 언어로 변환된 이진 표현

## JDK과 JRE
  - JDK(자바 개발 도구) : JRE + 개발에 필요한 실행파일 (javac.exe)
  - JRE(자바 실행 환경) : JVM + 클래스 라이브러리 (java API)

## JVM (자바 가상 머신)
  - 운영체제에 관계없이 자바를 구동할 수 있는 가상환경을 제공



## API (Application Programming Interface)
  - 컴퓨터나 프로그램 사이의 연결

## 간단한 프로그램 작성으로 컴파일 이해하기
  1. Profile.java 작성

  ```java
  public class Profile {
    public static void main(String args[]) {
      System.out.println("My name is InGeol Beak");
      System.out.println("My age is 29");
    }
  }
  ```

  2. `cmd` 경로를 탐색 후, `javac Profile.java` 입력
  3. `Profile.class` 생성을 확인
  4. `java Profile` 입력 후, 출력되는 문자들을 확인

  - 리턴 타입, 함수 이름, 함수 내용은 반드시 필요하다.
