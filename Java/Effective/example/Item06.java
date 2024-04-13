import java.util.regex.Pattern;

class RomanNumerals {
  private static final Pattern ROMAN = Pattern.compile(
    "^(?=.)M*(C[MD]|D?C{0,3})"
      + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

  static boolean isRomanNumeral(String s) {
    return ROMAN.matcher(s).matches();
  }
}

public class Item06 {

  private static final Pattern ROMAN = Pattern.compile(
    "^(?=.)M*(C[MD]|D?C{0,3})"
      + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

  static boolean isRomanNumeral(String s) {
    return ROMAN.matcher(s).matches();
  }

  public static void main(String[] args) {
    long beforeTime = System.nanoTime();

    RomanNumerals.isRomanNumeral("skdjf3kj2khkfjdhkjhdfjhdjfkh3");
    // isRomanNumeral("skdjf3kj2khkfjdhkjhdfjhdjfkh3");

    long afterTime = System.nanoTime();
    long diffTime = afterTime - beforeTime; // 두 개의 실행 시간

    System.out.println("실행 시간(nano): " + diffTime); // 세컨드(초 단위 변환)
  }
}