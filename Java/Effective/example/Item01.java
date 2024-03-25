package Java.Effective.example;

interface Person {
  public String hello(); 
}

class PersonStatic implements Person {
  public static String name;

  public static int age;

  public PersonStatic() {}

  public PersonStatic(String name) {
    PersonStatic.name = name;
  }

  public PersonStatic(int age) {
    PersonStatic.age = age;
  }

  public static PersonStatic createName(String name) {
    return new PersonStatic(name);
  }

  public static PersonStatic createAge(int age) {
    return new PersonStatic(age);
  }

  public String hello() {
    return "난 일반 사람이야!";
  }
}

class PolicePersonStatic implements Person {
  public static String name;

  public static int age;

  public PolicePersonStatic() { }
  
  public PolicePersonStatic(String name) {
    PolicePersonStatic.name = name;
  }

  public PolicePersonStatic(int age) {
    PolicePersonStatic.age = age;
  }

  public static PolicePersonStatic createName(String name) {
    return new PolicePersonStatic(name);
  }

  public static PolicePersonStatic createAge(int age) {
    return new PolicePersonStatic(age);
  }

  public String hello() {
    return "난 경찰이야!";
  }
}

class PersonFactory {
  public static Person of(String kind) {
    if(kind.equals("person")) {
      return new PersonStatic();
    } else if(kind.equals("police")) {
      return new PolicePersonStatic();
    }
    return null;
  }
}

public class Item01 {
  public static void main(String[] args) {
    PersonStatic.createAge(20);
    PersonStatic.createName("백!");
    System.out.println(PersonStatic.age);
    System.out.println(PersonStatic.name);

    Person person = PersonFactory.of("person");
    System.out.println(person.hello());
  }
}