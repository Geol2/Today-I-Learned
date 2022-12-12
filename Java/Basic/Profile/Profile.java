package Profile;
class Profile {
    String name;
    int age;
    
    public void setName(String str) {
        this.name = str;
    }

    public void setAge(int val) {
        this.age = val;
    }

    public void printName() {
        System.out.println("My name is " + this.name);
    }

    public void printAge() {
        System.out.println("My age is " + this.age);
    }

    public static void main(String[] args) {
        String name = "Min";

        Profile profile = new Profile();
        profile.setName(name);
        profile.setAge(20);

        profile.printName();
        profile.printAge();
    }
}