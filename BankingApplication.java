import java.util.ArrayList;
import java.util.Scanner;

class BankAccount {
    int accountNumber;
    String accountHolder;
    double balance;

    public BankAccount(int accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New Balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New Balance: $" + balance);
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();
        int accountCounter = 1000;

        while (true) {
            System.out.println("\n1. Create Account\n2. Deposit\n3. Withdraw\n4. Balance Inquiry\n5. Exit");
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter Account Holder Name: ");
                sc.nextLine(); // Clear buffer
                String name = sc.nextLine();
                System.out.print("Enter Initial Balance: ");
                double balance = sc.nextDouble();
                accounts.add(new BankAccount(accountCounter++, name, balance));
                System.out.println("Account Created. Account Number: " + (accountCounter - 1));
            } else if (choice == 2 || choice == 3 || choice == 4) {
                System.out.print("Enter Account Number: ");
                int accNum = sc.nextInt();
                BankAccount acc = accounts.stream()
                        .filter(a -> a.accountNumber == accNum)
                        .findFirst()
                        .orElse(null);

                if (acc == null) {
                    System.out.println("Account not found.");
                } else if (choice == 2) {
                    System.out.print("Enter Deposit Amount: ");
                    acc.deposit(sc.nextDouble());
                } else if (choice == 3) {
                    System.out.print("Enter Withdrawal Amount: ");
                    acc.withdraw(sc.nextDouble());
                } else {
                    acc.checkBalance();
                }
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}