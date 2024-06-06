import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ShoppingCart {
    ArrayList<CartItem> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();

    }

    public void addItem(Product product, int quantity) {
        if (!items.isEmpty()) {
            for (CartItem item : items) {
                if (item.product.equals(product)) {
                    item.setQuantity(quantity);
                    return;
                }
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(Product product) throws ItemNotfoundException {
        for (CartItem item : items) {
            if (item.product.equals(product)) {
                items.remove(item);
                return;
            }
        }

        throw new ItemNotfoundException("Item not found in the cart");
    }

    public double totalPriceElectronicsProducts() {
        double totalPrice = 0.0;
        for (CartItem item : items) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public double totalPriceProducts() {
        double totalPrice = 0.0;
        for (CartItem item : items) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    public void viewCart() {
        items.sort(Comparator.comparing(o -> o.getProduct().getName()));

        System.out.printf("%10s %15s %15s %15s\n", "Product", "Quantity", "Unit Price", "Total Price");
        System.out.printf("%s\n", "-".repeat(58));
        for (CartItem item : items) {

            Product product = item.getProduct();
            System.out.printf("%10s %15d %15.2f %15.2f\n",
                    product.getName(), item.getQuantity(), product.getPrice(), item.getTotalPrice());
        }
        System.out.printf("%s\n", "-".repeat(58));
    }

    public double checkout() {
        double checkoutPrice = 0.0;
        for (CartItem item : items) {
            checkoutPrice += item.getTotalPrice();
        }
        items.clear();
        return checkoutPrice;
    }

    public void loadProductsFromFile(String fileName) {

        try {
            FileInputStream in = new FileInputStream(fileName);
            Scanner sc = new Scanner(in);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] split = line.split(",");

                Product product = null;
                String name = split[1];
                double price = Double.parseDouble(split[2]);

                if (split[0].equals("1")) {
                    int warrantyPeriod = Integer.parseInt(split[3]);
                    String brand = split[4];
                    product = new ElectronicsProduct(name, price, warrantyPeriod, brand);
                } else if (split[0].equals("2")) {
                    String size = split[3];
                    String color = split[4];
                    product = new ClothingProduct(name, price, size, color);
                }

                addItem(product, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
