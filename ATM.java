 import java.util.Scanner;

class Account {
    private String accountNumber;
    private String pin;
    private double balance;

    public Account(String accountNumber, String pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(String enteredPin) {
        return pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    
    }
    
    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}
public class ATM {
    private Account currentAccount;
    private Scanner scanner;

    public ATM() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        authenticate();
        performTransactions();
        System.out.println("Thank you for using the ATM. Goodbye!");
    }

    private void authenticate() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();
        if (isValidAccount(accountNumber, pin)) {
            System.out.println("Authentication successful.");
        } else {
            System.out.println("Invalid account number or PIN. Please try again.");
            authenticate();
        }
    }

    private boolean isValidAccount(String accountNumber, String pin) {
        // In a real application, validate against a database of accounts
        // For simplicity, hardcoding a single account here
        if (accountNumber.equals("12345") && pin.equals("6789")) {
            currentAccount = new Account(accountNumber, pin, 1000.0); // Example account with initial balance
            return true;
        } else {
            return false;
        }
    }
    private void performTransactions() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left by nextInt()

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
                    
                }
            } while (choice != 4);
        }
    
        private void displayMenu() {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
        }
    
        private void checkBalance() {
            System.out.println("Current Balance: $" + currentAccount.getBalance());
        }
    
        private void deposit() {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            currentAccount.deposit(amount);
            System.out.println("Deposit successful.");
        }
    
        private void withdraw() {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            if (currentAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient balance.");
            }
        }
    
        public static void main(String[] args) {
            ATM atm = new ATM();
            atm.start();
        }
}    
        