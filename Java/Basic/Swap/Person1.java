public class Person1 {
    public static int age1 = 10;
    public static int age2 = 20;

    public static void swap() {
        int tmp = age2;
        age2 = age1;
        age1 = tmp;
    }

    public static void main(String[] args) {
        Person person1 = new Person();
        System.out.println("age1: " + age1 + ", " + "age2: " + age2);
        swap();
        System.out.println("age1: " + age1 + ", " + "age2: " + age2);
    }
}
