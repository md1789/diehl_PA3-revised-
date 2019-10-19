

public class SavingsAccount {
    static double annualInterestRate = 0.0;
    private double savingsBalance;

    public SavingsAccount(){
        savingsBalance = 0.0;
    }
    public SavingsAccount(double rate) {
        savingsBalance = rate;
    }

    public double calculateMonthlyInterest() {
        return savingsBalance += ((savingsBalance * annualInterestRate) / 12.0);
    }

    public static void modifyInterestRate(double rate) {
        SavingsAccount.annualInterestRate = rate;
    }

    public void setSavingsBalance(double balance) {
        savingsBalance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }
    public static void main (String[] arg){
        SavingsAccount saver1 = new SavingsAccount();
        SavingsAccount saver2 = new SavingsAccount();
        saver1.setSavingsBalance(2000.00);
        saver2.setSavingsBalance(3000.00);
        double interestRate1 = 0.04;
        modifyInterestRate(interestRate1);
        for (int i = 1; i <= 12; i++){
            saver1.savingsBalance = saver1.calculateMonthlyInterest();
            System.out.println("Month " + i + " 's balance with 4 percent interest starting with $2000.00 is " + Math.round(saver1.savingsBalance * 100.00) / 100.00);
        }
        for (int i = 1; i <= 12; i++){
            saver2.savingsBalance = saver2.calculateMonthlyInterest();
            System.out.println("Month " + i + " 's balance with 4 percent interest starting with $3000.00 is " + Math.round(saver2.savingsBalance * 100.00) / 100.00);
        }
        double interestRate2 = 0.05;
        modifyInterestRate(interestRate2);
        for (int i = 1; i <= 12; i++){
            saver1.savingsBalance = saver1.calculateMonthlyInterest();
            System.out.println("Month " + i + " 's balance with 5 percent interest starting with $2000.00 is " + Math.round(saver1.savingsBalance * 100.00) / 100.00);
        }
        for (int i = 1; i <= 12; i++){
            saver2.savingsBalance = saver2.calculateMonthlyInterest();
            System.out.println("Month " + i + " 's balance with 5 percent interest starting with $3000.00 is " + Math.round(saver2.savingsBalance * 100.00) / 100.00);
        }
    }

}