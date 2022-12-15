public class Main1 {
    public static void swap(Integer a, Integer b) {
        Integer tmp = b;
        b = a;
        a = tmp;
    }

    public static void main(String[] args) {
        Integer a = new Integer("10");
        Integer b = new Integer("20");
        swap(a, b);
        System.out.println("a: " + a + ", " + "b: " + b);
    }
}
