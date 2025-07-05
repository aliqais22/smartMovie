public class Snack {
    private String name;
    private double price;
    private String imagePath;
    private int quantity;

    public Snack(String name, double price, String imagePath) {
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.quantity = 0; // يبدأ من 0
    }

    // Getters
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImagePath() { return imagePath; }
    public int getQuantity() { return quantity; }

    // لتعديل الكمية
    public void incrementQuantity() { quantity++; }

    public void decrementQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
}
