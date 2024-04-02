class UtilityClass {
  //public UtilityClass() {
  private UtilityClass() {
    throw new AssertionError();
  }
  // ...
}

public class Item04 {
  public static void main(String[] args) {
    UtilityClass utilityClass = new UtilityClass(); // error! 생성을 막기 위해서 맞다!
  }
}