package budget;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;


class PrintListByCategory implements SortingStrategy {

    @Override
    public void sort(BudgetManager bm, boolean reversed, Category category) {

        double totalExpenses;
        ArrayList<Purchase> purchases;

        if (category == Category.ALL) {
            purchases = bm.getPurchases();
        } else {
            purchases = bm.getPurchases().stream().filter(e -> e.getCategory() == category).collect(Collectors.toCollection(ArrayList::new));
            purchases.sort(Comparator.comparing(Purchase::getName));
            purchases.sort(Comparator.comparingDouble(Purchase::getPrice));
        }

        if (purchases.isEmpty()) {
            System.out.println("Purchase list is empty\n");

        } else {
            totalExpenses = purchases.stream().mapToDouble(Purchase::getPrice).sum();

            if (reversed) {
                purchases.sort(Comparator.comparingDouble(Purchase::getPrice));
                Collections.reverse(purchases);
            }
            System.out.println(category.getName() + ":");
            purchases.forEach(System.out::println);
            System.out.printf("Total sum: $%.2f\n%n", totalExpenses);
        }

    }
}
