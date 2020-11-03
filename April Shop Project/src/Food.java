public class Food extends Item {
    int weight;

    public Food(){
        super();
        weight = 0;

    }

    public Food(String name, int stock, double price, int weight) {
        super(stock, price, name);
        this.weight = weight;

    }
    public Food(String f) {
        String[] list = f.split(",");
        // skip over blank lines
        if(list.length > 0) {
            setName(list[0]);
            setStock(Integer.parseInt(list[1]));
            setPrice(Double.parseDouble(list[2]));
            this.weight = Integer.parseInt(list[3]);
        }
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    @Override
    public String toString(){
        //% symbols in format are replace with variables in order following the formatting
        String rep = String.format("Product: %-17s Stock: %2d Price: €%5.2f Weight: %4dg",
                getName(),getStock(),getPrice(),getWeight());

        return rep;
    }
    @Override
    public String toCSV(){
        String csv = getName() + "," + getStock() + "," + getPrice() + "," + this.weight;

        return csv;
    }

    @Override
    public double subTotal() {
        return 0;
    }
}
