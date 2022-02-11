package budget;


import java.io.Serializable;

public class Purchase implements Serializable {  //, Comparator<Purchase>
    private static final long serialVersionUID = 1L;
    private final String name;
    private final double price;
    private final Category category;

    public Purchase(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return String.format("%s $%.2f", this.name, this.price);
    }
}


