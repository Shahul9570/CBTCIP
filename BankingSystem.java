import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdraw successfull");
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}

public class BankingSystem {
    private Map<String, Account> accounts;

    public BankingSystem() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber, String accountHolderName) {
        if (!accounts.containsKey(accountNumber)) {
            Account account = new Account(accountNumber, accountHolderName);
            accounts.put(accountNumber, account);
            System.out.println("Account created successfully.");
        } else {
            System.out.println("Account already exists.");
        }
    }

    public void deposit(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            accounts.get(accountNumber).deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        if (accounts.containsKey(accountNumber)) {
            accounts.get(accountNumber).withdraw(amount);
        } else {
            System.out.println("Account does not exist.");
        }
    }
    public void checkBalance(String accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            double balance = accounts.get(accountNumber).getBalance();
            System.out.println("Balance for account " + accountNumber + ": " + balance);
        } else {
            System.out.println("Account does not exist.");
        }
    }
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        if (accounts.containsKey(fromAccountNumber) && accounts.containsKey(toAccountNumber)) {
            Account fromAccount = accounts.get(fromAccountNumber);
            Account toAccount = accounts.get(toAccountNumber);
            if (fromAccount.getBalance() >= amount) {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Transfer successful.");
            } else {
                System.out.println("Insufficient funds for transfer.");
            }
        } else {
            System.out.println("One or both accounts do not exist.");
        }
    }

    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        Scanner scanner = new Scanner(System.in);
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\nBanking System Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("6. Balance enquiry");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character
            
            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String accNumber = scanner.nextLine();
                    System.out.print("Enter account holder name: ");
                    String accHolderName = scanner.nextLine();
                    bankingSystem.createAccount(accNumber, accHolderName);
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String depositAccNumber = scanner.nextLine();
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    bankingSystem.deposit(depositAccNumber, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String withdrawAccNumber = scanner.nextLine();
                    System.out.print("Enter withdraw amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    bankingSystem.withdraw(withdrawAccNumber, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter from account number: ");
                    String fromAccNumber = scanner.nextLine();
                    System.out.print("Enter to account number: ");
                    String toAccNumber = scanner.nextLine();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    bankingSystem.transfer(fromAccNumber, toAccNumber, transferAmount);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                    case 6:
                    System.out.print("Enter account number: ");
                    String checkBalanceAccNumber = scanner.nextLine();
                    bankingSystem.checkBalance(checkBalanceAccNumber);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
        
        scanner.close();
    }
}
