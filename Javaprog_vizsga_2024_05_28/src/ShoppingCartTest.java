public class ShoppingCartTest {

    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.loadProductsFromFile("products.txt");
        shoppingCart.viewCart();

        Product p1 = new ClothingProduct("Clothing 1", 15.00, "XL", "White");
        Product p2 = new ElectronicsProduct("PC", 2500, 24, "Lenovo");
        shoppingCart.addItem(p1, 15);
        shoppingCart.addItem(p2, 1);
        shoppingCart.addItem(p2, 5);
        shoppingCart.viewCart();

        Product p3 = new ElectronicsProduct("PC", 3500, 12, "Acer");

        try {
            shoppingCart.removeItem(p3);
        } catch (ItemNotfoundException e) {
            System.out.println(e.getMessage());
        }

        shoppingCart.viewCart();

        System.out.println("Elektronikai termékek osszértéke: " + shoppingCart.totalPriceElectronicsProducts());
        System.out.printf("Kosár összértéke: %.2f Euró", shoppingCart.totalPriceProducts());
    }
}
