
public class Animal {
    public String name;

    public String kind;

    public int legCount = 4;

    public int iq;

    public boolean hasWing = false;

    public Animal() {
        System.out.println("Animal Constructor");
    }

    public Animal(String name, String kind, int iq) {
        this.name = name;
        this.kind = kind;
        this.iq = iq;
    }

    public void move(){
        System.out.println("Animal move");
    }

    public void eatFood() {
        System.out.println("Animal eatFood");
    }

    public void sound() {
        System.out.println("Animal sound");
    }

    public String toString() {
        System.out.println( name + " " + kind + " " + legCount + " " + iq + " " + hasWing );
        return name + " " + kind + " " + legCount + " " + iq + " " + hasWing;
    }
}
