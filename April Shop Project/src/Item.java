public abstract class Item {
    private int stock;
    private double price;
    private int quantity;//How many are being bought

    private String name;

    public Item(){
        stock = 0;
        price = 0;
        quantity = 0;
        name = "";
    }

    public Item(int stock, double price, String name) {
        this.stock = stock;
        this.price = price;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addStock(int quantity) {
        setStock(getStock() + quantity);
    }

    //Stubs of methods which will be used in subclasses
    public abstract String toString();
    public abstract String toCSV();
    public abstract double subTotal();
}
