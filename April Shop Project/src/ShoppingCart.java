import java.util.HashMap;
import java.util.List;

public class ShoppingCart {
    Input input = new Input();
    Display win = new Display();
    HashMap<String, CartItem> cartItems = new HashMap<>();

    public ShoppingCart() {

    }

    //Get The Total Cost of the Shopping Cart
    public double getTotal() {
        double total = 0;

        for(String key : cartItems.keySet()) {
            // need access to price
            CartItem cartItem = cartItems.get(key);
            total += cartItem.subTotal();
        }
        return total;
    }

    //Load items from csv files and add them into arrays
    public void loadItems(List<String> lines, ManageItems mi) {

        for(String line : lines) {
            if(!line.trim().equals("")) {
                String[] list = line.split(",");

                String name = list[0];
                int quantity = Integer.parseInt(list[1]);

                Item item2 = mi.items.get(name.toLowerCase());
                cartItems.put(name.toLowerCase(), new CartItem(item2, quantity));
            }
        }

    }

    //Changes the cartItems list into a String[]
    public String[] itemsToCSV() {

        String[] itemsStr2 = new String[cartItems.size()];

        int i = 0;
        for(String key : cartItems.keySet()) {
            CartItem cartItem = cartItems.get(key);
            itemsStr2[i++] = cartItem.toCSV();
        }

        return itemsStr2;
    }

    public void addItemToCart(ManageItems mi) {
        // get product name
        String prompt = "Enter product name";
        String errMessage = "Invalid name, please enter another";
        String name = input.stringExists(mi.getNames(), prompt, errMessage).toLowerCase();
        //If cancel is pressed it will go back to the menu it came from
        if(name != null && name.length() >1) {

            // get quantity required
            prompt = "Enter quantity of selected item";
            errMessage = "Invalid quantity, please re-enter  between 0-20";
            // Note: max to buy cannot exceed stock level
            Integer quantity = input.number(prompt, 0, mi.getItemStock(name), errMessage);
            if(quantity != null) {

                // if item to add is already in list
                // add new quantity to this entry

                Item item = mi.getItem(name);

                if (cartItems.containsKey(name)) {
                    // item in cart so update qty
                    updateQuantity(name, quantity);

                } else { // Not in cart so add

                    cartItems.put(name, new CartItem(item, quantity));
                }
                // note quantity is negated to reduce stock of item
                mi.updateStock(name, -quantity);
            }
        }

    }
    //Updates the quantity in shopping cart of a specific item
    public void updateQuantity(String name, int quantity) {

        CartItem cartItem= cartItems.get(name);
        int newQuantity = cartItem.getQuantity() + quantity;
        cartItem.setQuantity(newQuantity);

        cartItems.replace(name, cartItem);

    }
    //Deconstuct the items so that they can be displayed in the transactions menu
    public String buildTransaction() {
        String rep="";
        for(String key : cartItems.keySet()) {
            key.toLowerCase();
            rep += key+","+cartItems.get(key).getQuantity()+",";
        }//$ matches the end of the String eg. ,$ will find the last comma in the String
        rep = rep.replaceAll(",$","\n");
        return rep;
    }

    public void checkOut(ManageItems mi) {
        // calculate total spent
        // list products in cart by name,price and quantity bought
        // display cart total price with button to Buy or cancel

        //Button options for check-out display screen
        Integer purchaseComplete = win.optionMessage("Check-Out", "Cancel", getCart());

        if(purchaseComplete == 0){//Check-Out
            // build transaction to send to file
            String transaction = buildTransaction();

            // write to transaction file
            FileHandler fh = new FileHandler();
            fh.append("Transactions.csv", transaction);

            // clear cart
            cartItems.clear();

            // show message to customer
            win.showMessage("Thank You for your order");
        }
    }

    public void changeQuantity(ManageItems mi) {
        //Pick item
        String[] itemStrings = getItemStrings();
        String prompt = "Select Item you wish to change the quantity for";
        String errMessage = "";

        //input new quantity for item
        String option = input.option(prompt, itemStrings);
        // if user hits cancel option will have length = 0
        if(option != null) {//Cancel pressed
            if (option != "") {//Check for empty String input
                prompt = "Enter quantity of selected item";
                errMessage = "Invalid quantity, please re-enter  between 0-20";

                int oldQuantity = cartItems.get(option).getQuantity();
                mi.updateStock(option, oldQuantity);

                int quantity = input.number(prompt, 0, mi.getItemStock(option), errMessage);
                mi.updateStock(option, -quantity);

                CartItem cartItem= cartItems.get(option);
                cartItem.setQuantity(quantity);
                cartItems.replace(option, cartItem);
            }
        }




        //change quantity of item
        //change stock of item
    }

    // remove item from cart with prompts
    public void removeItem(ManageItems mi) {
        //locate item in list
        //return stock of item
        //remove cart item

        String[] itemStrings = getItemStrings();
        String prompt = "Select Item to remove from Cart";
        String option = input.option(prompt, itemStrings);
        // if user hits cancel option will have length = 0
        if(option != null)
            if(option != "")
                removeItem(option, mi);
                cartItems.remove(option);

    }

    // remove item from cart given name
    private void removeItem(String name, ManageItems mi) {

        if(name != null || name.length()!=0) {
            System.out.println(name);
            CartItem cartItem = cartItems.get(name.toLowerCase());
            mi.returnStock(name , cartItem.getQuantity());
            //cartItems.remove(name.toLowerCase());
        }
    }

    private String[] getItemStrings() {

        return cartItems.keySet().toArray(new String[0]);
    }

    public void clearCart(ManageItems mi) {
        for(String key : cartItems.keySet()) {
            removeItem(key, mi);
        }
        cartItems.clear();
    }

    public void showCart() {
        //Display Product, Price, Quantity, Subtotal
        String[] report = getCart();

        win.showMessage(report);
    }
    public String[] getCart(){
        String[] report = new String[cartItems.size()+2];
        int i = 0;
        for(String key: cartItems.keySet()){
            report[i++] = cartItems.get(key).report();
        }
        report[i++] = ""; // line space
        report[i] = String.format("%-54s Total: €%.2f","",  getTotal());
        return report;
    }
}
