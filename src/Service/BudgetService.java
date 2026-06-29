package Service;

public class BudgetService {

  
    private double limit = 0;
    private double spent = 0;

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public void addExpense(double amount) {
        spent += amount;

        if (limit > 0 && spent > limit) {
            System.out.println("⚠ Budget exceeded!");
        } else if (limit > 0 && spent > limit * 0.8) {
            System.out.println("⚠ 80% budget used!");
        }
    }
}
