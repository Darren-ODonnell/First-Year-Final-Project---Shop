import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ManageItems {
    Input input = new Input();

    HashMap<String,Item> items = new HashMap<>();

    /**
     * When Making the array
     * Read file, extract list of items
     * Add list of items to the array list
     */
    public ManageItems(){

    }

    public void add(Item item) {

        items.put(item.getName().toLowerCase(), item);
    }

    public List<String> listItems(Item obj) {

        List<String> newList2 = new ArrayList<>();

        for (String key : items.keySet()) {
            Item it = items.get(key);
            if (it.getClass() == obj.getClass()) {
                newList2.add(it.toString());
            }
        }
        return newList2;
    }

    // collect all food or drink items into a csv List<String> for saving to file
    public List<String> itemsToCSV(Item obj) {
        List<String> newList2 = new ArrayList<>();

        for (String key : items.keySet()) {
            Item item = items.get(key);
            if (item.getClass() == obj.getClass())
                newList2.add(item.toCSV());
        }
        return newList2;
    }

    // data from files are added to items list based on class of obj
    public void loadItems(List<String> data, Item obj) {
        for(String item : data)
            if(obj.getClass() == Drink.class)
                add(new Drink(item));
             else
                add(new Food(item));
    }
    public void addFood() {
        //Add to array
        Food food = new Food();
        String prompt = "Enter a name for this food item";
        String name = input.stringNotInList(prompt, getNames(),2,16,"Length of product should be within 2-16 characters", "Food item already exists");
        if(name != null) {//Cancel not pressed
            name = name.toLowerCase();
            food.setName(name);

            prompt = "Enter a stock value for this food item";
            Integer num = input.number(prompt, 0, 20, "Number out of range 0-20");

            if(num != null) {
                food.setStock(num);

                prompt = "Enter a price for this food item";
                Double price = input.number(prompt, 0.0, 100.0, "Number out of range 0-100");
                if (price != null) {
                    food.setPrice(price);

                    prompt = "Enter a weight in grams for this food item";
                    Integer weight = input.number(prompt, 0, 10000, "Number out of range 0-10000(10kg)");
                    if(weight != null) {
                        food.setWeight(weight);


                        items.put(food.getName(), food);
                    }

                }
            }
        }
    }

    public void addDrink() {
        //Add to array
        Drink drink = new Drink();
        String prompt = "Enter a name for this drink item";
        String name = input.stringNotInList(prompt, getNames(),2,16,"Length of product should be within 2-16 characters", "Drink item already exists");
        if(name != null) {//Cancel not pressed
            name = name.toLowerCase();
            drink.setName(name);

            prompt = "Enter a stock value for this drink item";
            Integer num = input.number(prompt,0,20,"Number out of range 0-20");

            if(num != null) {
                drink.setStock(num);

                prompt = "Enter a price for this drink item";
                Double price =input.number(prompt,0.0,50.0,"Number out of range 0-50");
                if (price != null) {
                    drink.setPrice(price);

                    prompt = "Enter a size in ml for this drink item";
                    Integer size =input.number(prompt,0,3000,"Number out of range 0-3000(3L)");
                    if(size != null) {
                        drink.setSize(size);


                        items.put(drink.getName(), drink);
                    }

                }
            }
        }

//        prompt = "Enter a stock value for this drink item";
//        drink.setStock(input.number(prompt,0,20,"Number out of range 0-20"));
//
//        prompt = "Enter a price for this drink item";
//        drink.setPrice(input.number(prompt,0.0,50.0,"Number out of range 0-50"));
//
//        prompt = "Enter a size in ml for this drink item";
//        drink.setSize(input.number(prompt,0,3000,"Number out of range 0-3000(3L)"));
//
//        items.put(drink.getName(), drink);

    }

    public void addStock() {
        //pick an item
        String prompt = "Enter product name";
        String errMessage = "Invalid name, please enter another";
        String name = (input.stringExists(getNames(), prompt, errMessage)).toLowerCase();

        if(name.length() > 0) {
            prompt = "Stock value to be added to current stock";
            errMessage = "Invalid quantity, please enter another between 0-20";
            Integer stock = input.number(prompt, 0, 20, errMessage);

            if (stock != null) {
                Item item2 = items.get(name);
                item2.addStock(stock);
                items.put(name, item2);

            }
        }
    }


    public String[] getNames(){
        String[] names = new String[items.size()];
        int i = 0;
        for(String name: items.keySet()){
            names[i] = name.toLowerCase();
            i++;
        }


        return names;
    }

    public void returnStock(String name, int quantity) {

        Item item = items.get(name);
        item.addStock(quantity);
        items.replace(item.getName(),item);

    }

    public Item getItem(String name) {
        return items.get(name);
    }

    public int getItemStock(String name) {
        return items.get(name).getStock();

    }

    public void updateStock(String name, int stock) {

        Item item = items.get(name);
        item.addStock(stock);
        items.put(name,item);

    }
}
