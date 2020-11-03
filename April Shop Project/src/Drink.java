public class Drink extends Item {
    int size;

    public Drink() {
        size = 0;

    }

    public Drink(String name, int stock, double price, int size) {
        super(stock, price, name);
        this.size = size;
    }


    public Drink(String d) {
        String[] list = d.split(",");
        if(list.length > 0 ) {
            setName(list[0]);
            setStock(Integer.parseInt(list[1]));
            setPrice(Double.parseDouble(list[2]));
            setSize(Integer.parseInt(list[3]));
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        String str = "Name: " + getName() + "   Stock: " + getStock() + "   Price: " + getPrice() + "   Size: " + this.size + "ml";
        String rep = String.format("Product: %-17s Stock: %2d Price: €%5.2f Size: %4dml",
                getName(),getStock(),getPrice(),getSize());

        return rep;
    }

    @Override
    public String toCSV() {
        String csv = getName() + "," + getStock() + "," + getPrice() + "," + this.size;
        return csv;
    }

    @Override
    public double subTotal() {
        return 0;
    }
}


