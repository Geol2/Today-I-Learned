class Person {
    public int a = 10;
    public int b = 20;
}
    
public class Person2 {

    public static void swap(Person person) {
        int tmp = person.b;
        person.b = person.a;
        person.a = tmp;
    }

    public static void main(String[] args) {
        Person person = new Person();
        System.out.println("a: " + person.a + ", " + "b: " + person.b);
        swap(person);
        System.out.println("a: " + person.a + ", " + "b: " + person.b);
    }
}
