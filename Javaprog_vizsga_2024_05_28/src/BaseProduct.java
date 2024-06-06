abstract class BaseProduct implements Product {
    String name;
    double price;
    Category category;

    public BaseProduct(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
