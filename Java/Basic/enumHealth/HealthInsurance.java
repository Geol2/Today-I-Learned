
public enum HealthInsurance {
    LEVEL_ONE(10000000, 1.0),
    LEVEL_TWO(20000000, 2.0),
    LEVEL_THREE(30000000, 3.2),
    LEVEL_FOUR(40000000, 4.5),
    LEVEL_FIVE(50000000, 5.6),
    LEVEL_SIX(60000000, 7.1);

    private final int maxSalary;
    private final double ratio;

    HealthInsurance(int maxSalary, double ratio) {
        this.maxSalary = maxSalary;
        this.ratio = ratio;
    }

    public double getRatio() {
        return this.ratio;
    }

    public static HealthInsurance getHealthInsurance(int salary) {
        if(salary < 10000000) {
            return LEVEL_ONE;
        } else if(salary >= 10000000 && salary < 20000000 ) {
            return LEVEL_TWO;
        } else if(salary >= 20000000 && salary < 30000000 ) {
            return LEVEL_THREE;
        } else if(salary >= 30000000 && salary < 40000000 ) {
            return LEVEL_FOUR;
        } else if(salary >= 40000000 && salary < 50000000 ) {
            return LEVEL_FIVE;
        } else if(salary >= 50000000 && salary < 60000000 ) {
            return LEVEL_SIX;
        } else {
            return LEVEL_ONE;
        }
    }

    public static void main(String[] args) {
        int salaryArray[] = new int[]{15000000, 55000000, 80000000};
        HealthInsurance[] insurances = new HealthInsurance[3];
        insurances[0] = HealthInsurance.getHealthInsurance(salaryArray[0]);
        insurances[1] = HealthInsurance.getHealthInsurance(salaryArray[1]);
        insurances[2] = HealthInsurance.getHealthInsurance(salaryArray[2]);

        for(int loop = 0; loop < 3; loop++) {
            System.out.println(salaryArray[loop] + " = " + insurances[loop] + ", " + insurances[loop].getRatio() );
        }
    }
}
