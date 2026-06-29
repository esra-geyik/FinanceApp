package financeapp;

import Service.AnalysisService;
import Service.AuthService;
import Service.BudgetService;
import Service.TransactionService;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FinanceApp {

    private static List<Transaction> getUserTransactions(
            TransactionService ts,
            String user) {

        List<Transaction> result = new ArrayList<>();

        for (Transaction t : ts.getAll()) {

            if (t.getUsername().equalsIgnoreCase(user)) {
                result.add(t);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AuthService auth = new AuthService();
        TransactionService ts = new TransactionService();
        BudgetService budget = new BudgetService();

        System.out.println("1-Register 2-Login");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Username: ");
        String user = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        boolean ok;

        if (choice == 1) {
            ok = auth.register(user, pass);
        } else {
            ok = auth.login(user, pass);
        }

        if (!ok) {
            System.out.println("Login failed!");
            return;
        }

        ts.loadFromFile();

        OUTER:
        while (true) {

            System.out.println(
                    "\n1-Income " +
                    "2-Expense " +
                    "3-Balance " +
                    "4-Show Transactions " +
                    "5-Set Budget " +
                    "6-Report " +
                    "7-Exit"
            );

            int c = sc.nextInt();

            switch (c) {

                case 1 -> {

                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Amount must be positive!");
                        break;
                    }

                    System.out.print(
                            "Category (Salary, Scholarship, Freelance, Investment): "
                    );

                    String category = sc.next();

                    ts.addIncome(user, amount, category);

                    System.out.println("Income added.");
                }

                case 2 -> {

                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();

                    if (amount <= 0) {
                        System.out.println("Amount must be positive!");
                        break;
                    }

                    System.out.print(
                            "Category (Food, Transport, Bills, Education, Health): "
                    );

                    String category = sc.next();

                    ts.addExpense(user, amount, category);

                    budget.addExpense(amount);

                    System.out.println("Expense added.");
                }

                case 3 -> {

                    AnalysisService a =
                            new AnalysisService(
                                    getUserTransactions(ts, user)
                            );

                    System.out.println(
                            "Balance: " + a.balance()
                    );
                }

                case 4 -> {

                    for (Transaction t :
                            getUserTransactions(ts, user)) {

                        System.out.println(
                                t.getDate() + " | " +
                                t.getType() + " | " +
                                t.getCategory() + " | " +
                                t.getAmount()
                        );
                    }
                }

                case 5 -> {

                    System.out.print("Budget Limit: ");
                    double limit = sc.nextDouble();

                    budget.setLimit(limit);

                    System.out.println("Budget set.");
                }

                case 6 -> {

                    AnalysisService a =
                            new AnalysisService(
                                    getUserTransactions(ts, user)
                            );

                    a.report();
                }

                case 7 -> {
                    break OUTER;
                }

                default -> {
                    System.out.println("Invalid option!");
                }
            }
        }

        System.out.println("Program closed.");
    }
}