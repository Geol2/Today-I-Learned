public class ProfilePrint {
    private byte age;
    private String name;
    private boolean isMarried;

    public void setAge(byte age) {
        this.age = age;
    }

    public byte getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setMarried(boolean flag) {
        this.isMarried = flag;
    }

    public boolean getMarried() {
        return this.isMarried;
    }

    public static void main(String[] args) {
        ProfilePrint profilePrint = new ProfilePrint();
        profilePrint.setAge( (byte)29 );
        profilePrint.setName("IN-GEOL BAEK");
        profilePrint.setMarried(false);

        System.out.println( profilePrint.getAge() );
        System.out.println( profilePrint.getName() );
        System.out.println( profilePrint.getMarried() );

    }
}
