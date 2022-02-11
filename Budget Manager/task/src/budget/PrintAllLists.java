package budget;

import java.util.*;
import java.util.stream.Collectors;

class PrintAllLists implements SortingStrategy {

       @Override
    public void sort(BudgetManager bm, boolean reversed, Category category) {

        ArrayList<Purchase> purchases = bm.getPurchases();
        purchases.sort(Comparator.comparing(Purchase::getName));
        purchases.sort(Comparator.comparingDouble(Purchase::getPrice));
        if (purchases.isEmpty() ) {
            System.out.println("Purchase list is empty\n");
        } else {
            if (reversed) {
                Collections.reverse(purchases);
            }
            if (category != Category.ALL) {

                Map<Double, String> sortedByTypeMap = new TreeMap<>();

                for (Category c : Category.values()) {
                    if (c == Category.ALL) {
                        continue;
                    }
                    ArrayList<Purchase> purchaseType = bm.getPurchases().stream().filter(e -> e.getCategory() == c).collect(Collectors.toCollection(ArrayList::new));
                    double sum = purchaseType.stream().mapToDouble(Purchase::getPrice).sum();
                    sortedByTypeMap.put(sum, c.getName());
                }
                List<String> aux = new ArrayList<>();

                sortedByTypeMap.forEach((price, name) -> aux.add(name + String.format(" - $%.2f", price)));
                Collections.reverse(aux);
                aux.forEach(System.out::println);
            } else {
                System.out.println("All:");
                purchases.forEach(System.out::println);
                System.out.printf("Total sum: $%.2f\n%n", bm.getExpenses());
            }
        }
    }

}
