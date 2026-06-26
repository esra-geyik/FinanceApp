/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import model.Transaction;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author GK
 */
public class FileRepository {
    private final String USERS_FILE = "users.txt";
    private final String TRANS_FILE = "transactions.txt";

    // USER
    public void saveUser(User u) {
        try (FileWriter fw = new FileWriter(USERS_FILE, true)) {
            fw.write(u.getUsername() + "|" + u.getPasswordHash() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> loadUsers() {
        return readFile(USERS_FILE);
    }

    // TRANSACTION
    public void saveTransaction(Transaction t) {
        try (FileWriter fw = new FileWriter(TRANS_FILE, true)) {
            fw.write(
                t.getUsername() + "|" +
                t.getType() + "|" +
                t.getAmount() + "|" +
                t.getCategory() + "|" +
                t.getDate() + "\n"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> loadTransactions() {
        return readFile(TRANS_FILE);
    }

    private List<String> readFile(String file) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (Exception e) {
            // dosya yoksa ilk çalışmada hata vermesin
        }
        return list;
    }
    
}
