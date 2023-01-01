public class InterestManager {
    
    public double calculateAmount(int day, long amount) {
        double InterstRate = getInterestRate(day);
        double totalAmount = 0;
        
        totalAmount = amount + amount * ( InterstRate / 100.0 );
        
        return totalAmount;
    }
    
    public double getInterestRate(int day) {
        double rate = 0;

        if (day <= 90) {
            rate = 0.5;
        } else if (day >= 91 && day <= 180) {
            rate = 1.0;
        } else if (day >= 181 && day <= 364) {
            rate = 2.0;
        } else {
            rate = 5.6;
        }

        return rate;
    }
    
    public static void main(String[] args) {
        InterestManager interestManager = new InterestManager();

        for(int i = 10; i <= 365; i+=10) {
            System.out.println( interestManager.calculateAmount(i, 1_000_000) );
        }
    }
}