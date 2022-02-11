package budget;

import java.util.*;
import java.util.stream.Collectors;

public class PrintTotalOfEachList implements SortingStrategy {
    @Override
    public void sort(BudgetManager bm, boolean reversed, Category category) {

        ArrayList<Purchase> purchases = bm.getPurchases();
        purchases.sort(Comparator.comparing(Purchase::getName));
        purchases.sort(Comparator.comparingDouble(Purchase::getPrice));
        Collections.reverse(purchases);


        // Map<Double, String> totalOfEachListMap = new TreeMap<>();
        HashMap<String, Double> totalOfEachListMap = new HashMap<>();
        for (Category c : Category.values()) {
            if (c == Category.ALL) {
                continue;
            }
            ArrayList<Purchase> purchaseType = bm.getPurchases().stream().filter(e -> e.getCategory() == c).collect(Collectors.toCollection(ArrayList::new));
            double sum = purchaseType.stream()
                    .mapToDouble(Purchase::getPrice)
                    .sum();
            totalOfEachListMap.put(c.getName(), sum);

        }
        List<String> aux = new ArrayList<>();

        sortByValue(totalOfEachListMap).forEach((name, price) -> aux.add(name + String.format(" - $%.2f", price)));
        Collections.reverse(aux);
        System.out.println("Types:");
        aux.forEach(System.out::println);
        System.out.printf("Total sum: $%.2f\n%n", bm.getExpenses());
    }
    public static HashMap<String, Double> sortByValue (HashMap<String, Double> hm){
        // Create a list from elements of HashMap
        List<Map.Entry<String, Double>> list =
                new LinkedList<Map.Entry<String, Double>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, Comparator.comparing(Map.Entry::getValue));


        // put data from sorted list to hashmap
        HashMap<String, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
