class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }
}

public class equals {
    public static void main(String[] args) {
        String str1 = "test1";
        String str2 = "test1";

        System.out.println(str1 == str2); // true
        System.out.println(str1.equals(str2)); // true

        String str3 = new String("test1");
        String str4 = new String("test1");

        System.out.println(str3 == str4); // false
        System.out.println(str3.equals(str4)); // true

        System.out.println(str1 == str3); // false
        System.out.println(str1.equals(str3)); // true

        System.out.println(str1.hashCode()); // 110251487
        System.out.println(str2.hashCode()); // 110251487
        System.out.println(str3.hashCode()); // 110251487
        System.out.println(str4.hashCode()); // 110251487

        Person p1 = new Person("geol");
        Person p2 = new Person("geol");

        System.out.println(p1.equals(p2)); // false -> name필드에 의해 동등성에 해당되어 equals()를 재정의할 필요가 있음

        System.out.println(p1.hashCode()); // 705927765
        System.out.println(p2.hashCode()); // 366712642
    }
}
