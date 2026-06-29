package Service;
import model.Transaction;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
public class AnalysisService {
    private final List<Transaction> list;

    public AnalysisService(List<Transaction> list) {
        this.list = list;
    }

    public double income() {
        return list.stream()
                .filter(t -> t.getType().equals("INCOME"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double expense() {
        return list.stream()
                .filter(t -> t.getType().equals("EXPENSE"))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double balance() {
        return income() - expense();
    }
    public void report() {

    System.out.println("\n===== REPORT =====");

    System.out.println("Income : " + income());

    System.out.println("Expense: " + expense());

    System.out.println("Balance: " + balance());
    System.out.println("Most Expensive Category: " + mostExpensiveCategory());
}
    public String mostExpensiveCategory() {

    Map<String, Double> map = new HashMap<>();

    for (Transaction t : list) {

        if (t.getType().equals("EXPENSE")) {

            map.put(
                t.getCategory(),
                map.getOrDefault(t.getCategory(), 0.0)
                        + t.getAmount()
            );
        }
    }

    String best = "None";
    double max = 0;

    for (String key : map.keySet()) {

        if (map.get(key) > max) {

            max = map.get(key);
            best = key;
        }
    }

    return best + " (" + max + " TL)";
}
}
