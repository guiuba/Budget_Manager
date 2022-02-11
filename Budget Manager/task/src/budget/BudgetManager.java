package budget;

import java.io.Serializable;
import java.util.ArrayList;

public class BudgetManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private double income;
    private double expenses;
    private final ArrayList<Purchase> purchases;

    public BudgetManager() {
        this.purchases = new ArrayList<>();
        this.income = 0;
        this.expenses = 0;
    }

    public void addIncome(double income) {
        this.income += income;
    }

    public void addPurchase(Purchase purchase) {
        this.purchases.add(purchase);
        this.expenses += purchase.getPrice();
    }

    public ArrayList<Purchase> getPurchases() {
        return this.purchases;
    }

    public double getExpenses() {
        return this.expenses;
    }

    public double getBalance() {
        return this.income - this.expenses;
    }

}
