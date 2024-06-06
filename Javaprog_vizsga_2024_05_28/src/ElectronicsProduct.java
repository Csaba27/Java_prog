public class ElectronicsProduct extends BaseProduct {
    int warrantyPeriod;
    String brand;

    public ElectronicsProduct(String name, double price, int warrantyPeriod, String brand) {
        super(name, price, Category.ELECTRONICS);
        this.warrantyPeriod = warrantyPeriod;
        this.brand = brand;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ElectronicsProduct{" +
                "warrantyPeriod=" + warrantyPeriod +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                "} " + super.toString();
    }
}
