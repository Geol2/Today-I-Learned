public class Calculator {
    public static void main(String args[]) {
        Calculator calc = new Calculator();
        try{
            calc.printDivide(1, 2);
            calc.printDivide(1, 0);
        } catch (ZeroException ze) {
            ze.printStackTrace();
            // System.out.println( ze.getMessage() );
        }
    }

    public void printDivide(double d1, double d2) throws ZeroException {
        double result = d1/d2;
        if(d2 == 0) {
            throw new ZeroException("Second value can\'t be Zero");
        }

        System.out.println(result);
    }
}

class ZeroException extends Exception {
    public ZeroException() {
        super();
    }

    public ZeroException(String message) {
        super(message);
    }
}