package budget;

class Context {
    private SortingStrategy sortingStrategy;

    public void setMethod(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void sort(BudgetManager bm, boolean reversed, Category category) {
        this.sortingStrategy.sort(bm, reversed, category);
    }
}
