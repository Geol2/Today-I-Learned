public class Dog extends Animal {

    public Dog() {
        super("dog", "gray wolf", 60);
        System.out.println("Dog Constructor");
    }

    public void eatFood() {
        System.out.println("멍멍이 밥먹기");
    }

    public void sound() {
        System.out.println("멍멍이 왈왈");
    }

    public void move() {
        System.out.println("멍멍이 걷기");
    }
}
