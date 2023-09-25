import java.util.Scanner;

class Bank {
    private double balance;

    public Bank(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful. \n CURRENT Balance: $" + balance);
        } else {
            System.out.println("deposit amount Invalid");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. CURRENT balance: $" + balance);
        } else if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            System.out.println("Insufficient balance for withdrawal.");
        }
    }
}
public class atm_software {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double initialBalance = 1000.0; 
        Bank account = new Bank(initialBalance);

        while (true) {
            System.out.println("ATM MECHINE OF MY BANK!");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Balance");
            System.out.println("3. Withdraw Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("CURRENT Balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawalAmount = sc.nextDouble();
                    account.withdraw(withdrawalAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
    }
}