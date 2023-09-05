package ATMInterface;
import java.util.Scanner;
public class ATM {
    private User currentUser;
    private Account currentAccount;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        User user = authenticateUser(userId, pin);

        if (user != null) {
            currentAccount = new Account(userId);
            currentUser = user;
            showMainMenu(scanner);
        } else {
            System.out.println("Authentication failed. Exiting...");
        }
        
    }

    private User authenticateUser(String userId, String pin) {
        // Implement user authentication logic here (e.g., check against a database).
        // For simplicity, we'll use hardcoded values.
        if ("12345".equals(userId) && "1234".equals(pin)) {
            return new User(userId, pin);
        }
        return null;
    }

    private void showMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    displayTransactionHistory();
                    break;
                case 2:
                    performWithdrawal(scanner);
                    break;
                case 3:
                    performDeposit(scanner);
                    break;
                case 4:
                    performTransfer(scanner);
                    break;
                case 5:
                    System.out.println("Exiting ATM. Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayTransactionHistory() {
        System.out.println("\nTransaction History:");
        for (Transaction transaction : currentAccount.getTransactionHistory()) {
            System.out.println("Date: " + transaction.getDate() + " Amount: " + transaction.getAmount());
        }
    }
    
    

    private void performWithdrawal(Scanner scanner) {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        currentAccount.withdraw(amount);
    }

    private void performDeposit(Scanner scanner) {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        currentAccount.deposit(amount);
    }

    private void performTransfer(Scanner scanner) {
        System.out.print("Enter recipient's User ID: ");
        String recipientUserId = scanner.nextLine();
        Account recipientAccount = new Account(recipientUserId);

        System.out.print("Enter transfer amount: ");
        double amount = scanner.nextDouble();

        currentAccount.transfer(recipientAccount, amount);
    }
}

