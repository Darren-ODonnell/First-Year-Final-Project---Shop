public class CartItem extends Item{
    private Item item;

    private int quantity = 0;

    public CartItem() {

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CartItem(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;

    }

    @Override
    public String toString() {
        return "Name: "+item.getName() + " Quantity: "+getQuantity() +"  Price: €"+getPrice()+ " = subTotal : €"+getPrice()*getQuantity();
    }

    @Override
    public String toCSV() {
        return item.getName()+","+getQuantity();
    }
    public String report(){
        //formatting used to display the report nicely with even spacing
        String rep = String.format("Product: %-15s Quantity: %2d Price: €%.2f Sub Total: €%5.2f",
                getItem().getName(),getQuantity(),getItem().getPrice(),subTotal());

        return rep;
    }

    public double subTotal() {
        return item.getPrice() * quantity;
    }
}
