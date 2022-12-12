package SalaryManager;
public class SalaryManager {

    double incomeTax = 12.5;       // 근로소득세
    double nationalPension = 8.1;  // 국민연금
    double healthInsurance = 13.5; // 건강 보혐료

    public double calculateHealthInsurance(double monthlySalary) {
        double calculatedHealthInsurance = 0d;
        calculatedHealthInsurance = monthlySalary * (this.healthInsurance/100) ;

        System.out.println("HealthInsurance : " + calculatedHealthInsurance);
        return calculatedHealthInsurance;
    }

    public double calculateNationalPension(double monthSalary) {
        double calculatedNationPension = 0d;
        calculatedNationPension = monthSalary * (this.nationalPension/100);

        System.out.println("NationalPension : " + calculatedNationPension);
        return calculatedNationPension;
    } 

    public double calculateTax(double monthSalary) {
        
        double calculatedMonthSalary = 0d;
        calculatedMonthSalary = monthSalary * (this.incomeTax/100);

        System.out.println("Income Tax : " + calculatedMonthSalary);
        return calculatedMonthSalary;
    }

    public double getMoneythlySalary(int yearlySalary) {
        double monthlySalary = 0d;
        
        monthlySalary = yearlySalary / 12.0;

        double getIncomeTaxOfMonth = calculateTax(monthlySalary);
        double getNationPensionOfMonth = calculateNationalPension(monthlySalary);
        double getHealthInsuranceOfMonth = calculateHealthInsurance(monthlySalary);

        double totalTax = getIncomeTaxOfMonth + getNationPensionOfMonth + getHealthInsuranceOfMonth;

        monthlySalary -= totalTax;

        return monthlySalary;
    }

    public static void main(String[] args) {
        SalaryManager salary = new SalaryManager();
        double monthlySalary = salary.getMoneythlySalary(20_000_000);
        System.out.println("Total Monthly :" + monthlySalary);
    }
}