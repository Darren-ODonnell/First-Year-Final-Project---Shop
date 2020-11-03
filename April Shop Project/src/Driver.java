import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Driver {
    static FileHandler fh = new FileHandler();
    static ManageItems mi = new ManageItems();
    static Menu menu = new Menu();
    static Input input = new Input();
    static Display window = new Display();
    static ShoppingCart sc = new ShoppingCart();

    public Driver() {

        loadData();

        //System.out.println( input.option(menu.getManageCartMenu(),0));

        //System.exit(0);

        Integer option = null;
        do {
            option = input.menuOption(menu.getTopMenu());
            if(option == null) {
                option = 3;
            }
            switch (option) {
                case 1:
                    staffMenu();
                    break;
                case 2:
                    customerMenu();
                    break;

            }
        } while (option < 3);

        saveData();

        exit();

    }

    // menus ------------------------------------------------------
    public void staffMenu(){
        //Add Food
        //Add Drink
        //Add Stock
        //Transaction Report
        //Exit to Menu
        Integer option = 0;
        do {
            option = input.menuOption(menu.getStaffMenu());
            switch (option) {
                case 1:
                    addFood();
                    break;
                case 2:
                    addDrink();
                    break;
                case 3:
                    addStock();
                    break;
                case 4:
                    transactionReport();
                    break;
            }
        } while (option < 5);



    }
    public void customerMenu() {
        //Display Food
        //Display Drinks
        //Purchase Food
        //Purchase Drinks
        //Exit to Menu
        Integer option = 0;
        do {
            option = input.menuOption(menu.getCustomerMenu());
            switch (option) {
                case 1:
                    displayFood();
                    break;
                case 2:
                    displayDrink();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    checkOut();
                    break;
                case 5:
                    manageCartMenu();
                    break;
            }
        } while (option < 6);

    }
    public void manageCartMenu() {
        Integer option = 0;
        do {
            option = input.menuOption(menu.getManageCartMenu());
            switch (option) {
                case 1:
                    removeItem();
                    break;
                case 2:
                    changeQuantity();
                    break;
                case 3:
                    clearCart();
                    break;
                case 4:
                    showCart();
            }
        } while (option < 5);
    }

    public void exit(){
        window.showMessage("Thank You for using the Shop System");
    }
    //Import data from csv files into arrays
    public void loadData(){
        mi.loadItems(fh.read("Drink.csv"), new Drink());
        mi.loadItems(fh.read("Food.csv"), new Food());
        sc.loadItems(fh.read("ShoppingCart.csv"),  mi);
    }
    //Export data from arrays to csv files
    public void saveData(){
        fh.write("Drink.csv", mi.itemsToCSV(new Drink()));
        fh.write("Food.csv", mi.itemsToCSV(new Food()));
        fh.write("ShoppingCart.csv", sc.itemsToCSV());
    }

    // staff Menu Actions -----------------------------------------
    public void addFood(){
        mi.addFood();

    }
    public void addDrink(){
        mi.addDrink();

    }
    public void addStock(){
        mi.addStock();

    }
    public void transactionReport(){
        //read in file
        List<String> list = fh.read("Transactions.csv");



        //parse into hashmap
        HashMap<String, Integer> report = new HashMap<>();

        for(int i = 0; i < list.size(); i++){

            String[] pairs = list.get(i).split(",");

            for(int x = 0; x < pairs.length; x+=2) {
                String key = pairs[x];
                int value = Integer.parseInt(pairs[x+1]);
                if (report.size() > 0) {
                    if (report.containsKey(key)) {//Product already exists in hashmap
                        int total = report.get(key) + value;
                        report.replace(key, total);
                    } else {//new Product to be added to hashmap
                        report.put(key, value);
                    }
                } else {//new Product to be added to hashmap
                    report.put(key, value);
                }
            }
        }


        //run report
        List<String> rep = new ArrayList<>();
        for(String key: report.keySet()){
            int value = report.get(key);
            String str = String.format("Product: %-15s Quantity Sold: %5d",key,value);
            rep.add(str);
        }
        window.showMessage(rep);

    }

    // Customer Menu actions ---------------------------------------
    public void displayFood(){
        window.showMessage(mi.listItems(new Food()));
        //Name
        //Price
    }
    public void displayDrink(){
        window.showMessage(mi.listItems(new Drink()));

    }
    public void addToCart(){
        sc.addItemToCart(mi);

    }

    // Manage Cart menu actions ------------------------------------
    private void removeItem() {
        if(sc.cartItems.size()==0)
            window.showErrorMessage("No more items in Cart");
         else
            sc.removeItem(mi);
    }
    private void clearCart() {
        if(sc.cartItems.size()==0)
            window.showErrorMessage("No items in Cart to clear");
         else
            sc.clearCart(mi);

    }
    private void changeQuantity() {
        if(sc.cartItems.size()==0)
            window.showErrorMessage("No items in Cart to change quantity");
         else
            sc.changeQuantity(mi);
    }
    private void checkOut() {
        if(sc.cartItems.size()==0)
            window.showErrorMessage("Nothing to checkout - no items in Cart");
         else
            sc.checkOut(mi);


    }
    private void showCart() {
        if(sc.cartItems.size()==0)
            window.showErrorMessage("No items in Cart to show");
         else
            sc.showCart();

    }
    public static void main(String[] args) {
        new Driver();
    }

}
