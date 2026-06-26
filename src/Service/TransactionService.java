/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;
import Repository.FileRepository;
import model.Transaction;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author GK
 */
public class TransactionService {
    private final FileRepository repo = new FileRepository();
    private final List<Transaction> cache = new ArrayList<>();

    public void addIncome(String user, double amount, String cat) {
        Transaction t = new Transaction(user, "INCOME", amount, cat);
        repo.saveTransaction(t);
        cache.add(t);
    }

    public void addExpense(String user, double amount, String cat) {
        Transaction t = new Transaction(user, "EXPENSE", amount, cat);
        repo.saveTransaction(t);
        cache.add(t);
    }

    public List<Transaction> getAll() {
        return cache;
    }
    public void showTransactions() {

    if (cache.isEmpty()) {
        System.out.println("No transactions found.");
        return;
    }

    for (Transaction t : cache) {

        System.out.println(
                t.getDate() + " | " +
                t.getType() + " | " +
                t.getCategory() + " | " +
                t.getAmount()
        );
    }
}
    public void loadFromFile() {
cache.clear();
    List<String> lines = repo.loadTransactions();

    for (String line : lines) {

        String[] p = line.split("\\|");

        Transaction t = new Transaction(
                p[0], // user
                p[1], // type
                Double.parseDouble(p[2]), // amount
                p[3]  // category
        );

        cache.add(t);
    }
}
}
