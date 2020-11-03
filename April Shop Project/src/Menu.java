//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Menu {
    int min = 1;
    int max = 3;
    String menu = "";
    String errMessage = "";
    private final String option1 = "1. Customer Menu";
    private final String option2 = "2. Staff Menu";
    private final String option3 = "3. Exit";


    final String prompt = "Please select one of the options above";

    public Menu() {

        this.displayMenu();
    }

    public String displayMenu() {
        this.min = 1;
        this.menu = "1. Customer Menu \n2. Staff Menu \n3. Exit Flights\n\nPlease select one of the options above";
        this.max = 3;
        this.errMessage = "Please enter in range " + this.min + " to " + this.max;
        return this.menu;
    }

    public String displayMenu(String[] list) {
        String menu = "";
        this.min = 1;
        this.max = list.length;
        for(int i = 0; i< this.max; i++) {
            menu += i + list[i] + "\n";
        }
        menu += "\n";
        menu += "Select Product Option: ";
        this.errMessage = "Please enter number in the range of" + this.min + " to " + this.max;
        return menu;
    }

    public String getMenu() {
        return this.menu;
    }

    public String[] getTopMenu(){
        String[] topMenu = new String[3];
        topMenu[0] = "1. Staff Menu";
        topMenu[1] = "2. Customer Menu";
        topMenu[2] = "3. Exit Shop";


        return topMenu;
    }
    public String[] getStaffMenu(){
        String[] staffMenu = new String[5];
        staffMenu[0] = "1. Add Food";
        staffMenu[1] = "2. Add Drink";
        staffMenu[2] = "3. Add Stock";
        staffMenu[3] = "4. Transaction Report";
        staffMenu[4] = "5. Return To Main Menu";

        return staffMenu;

    }
    public String[] getCustomerMenu() {
        String[] customerMenu = new String[6];
        customerMenu[0] = "1. Display Food";
        customerMenu[1] = "2. Display Drink";
        customerMenu[2] = "3. Add Item To Cart";
        customerMenu[3] = "4. Check-out";
        customerMenu[4] = "5. Manage Cart";
        customerMenu[5] = "6. Return To Main Menu";

        return customerMenu;
    }
    public String[] getManageCartMenu(){
        String[] manageCart = new String[5];
        manageCart[0] = "1. Remove Item";
        manageCart[1] = "2. Change Quantity";
        manageCart[2] = "3. Clear Cart";
        manageCart[3] = "4. Show Cart";

        manageCart[4] = "5. Return To Customer Menu";

        return manageCart;
    }
}
