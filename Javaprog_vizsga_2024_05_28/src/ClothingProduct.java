public class ClothingProduct extends BaseProduct {
    String size;
    String color;

    public ClothingProduct(String name, double price, String size, String color) {
        super(name, price, Category.CLOTHING);
        this.size = size;
        this.color = color;
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
        return "ClothingProduct{" +
                "size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                "} " + super.toString();
    }
}
