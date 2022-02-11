package budget;

public enum Category {
    FOOD("Food", 1),
    CLOTHES("Clothes", 2),
    ENTERTAINMENT("Entertainment", 3),
    OTHER("Other", 4),
    ALL("All", 5);

    String name;
    int id;

    Category(String name, int id) {
        this.name = name;
        this.id = id;
    }

    String getName() {
        return name;
    }

    public static Category findValueById(int sections) {
        for (Category value: values()) {
            if (value.id == sections) {
                return value;
            }
        }
        return null;
    }
}
