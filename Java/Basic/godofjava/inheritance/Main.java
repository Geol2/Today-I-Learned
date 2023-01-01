public class Main {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.eatFood();
        animal.sound();

        Animal cat = new Cat();
        cat.eatFood();
        cat.sound();
        cat.move();
        cat.toString();

        Animal dog = new Dog();
        dog.eatFood();
        dog.sound();
        dog.move();
        dog.toString();
    }
}
