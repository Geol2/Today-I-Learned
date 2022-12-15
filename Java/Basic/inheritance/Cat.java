
public class Cat extends Animal {

    public Cat() {
        super("cat", "african wildcat", 50);
        System.out.println("Cat Constructor");
    }

    public void eatFood() {
        System.out.println("고양이 밥먹기");
    }

    public void sound() {
        System.out.println("고양이 냐옹");
    }

}
