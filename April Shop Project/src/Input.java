import java.awt.*;
import java.util.List;

public class Input {

    Display win = new Display();

    Font font = new Font("Arial", Font.BOLD, 18);

    public Input() {


    }

    // read in string with a defined minimum only
    public String string(String prompt, int length, String errMessage) {
        String inputStr;

        do {
            inputStr = win.input(prompt);
            if (inputStr.length() < length){

                win.showMessage(errMessage);
            }
        } while (inputStr.length() < length);

        return inputStr;
    }

    // read in string with a defined minimum and maximum length
    public String string(String prompt, int min,int max, String errMessage) {
        String inputStr;

        do {
            inputStr = win.input(prompt);
            if(inputStr != null) {
                if (inputStr.length() < min || inputStr.length() > max)
                    win.showMessage(errMessage);
            }
        } while (inputStr != null && (inputStr.length() < min || inputStr.length() > max));

        return inputStr;
    }

    // read in string no no min or max length defined
    public String string(String prompt) {
        return win.input(prompt);
    }

    // simple regex match using code
    public String string(String prompt, String code) {
        String inputStr;
        do{
            inputStr = win.input(prompt);
        }while(!inputStr.matches(code));

        return inputStr;
    }

    // input valid if in list
    public String string(String[] myList, String prompt, String errMessage) {
        String inputStr;
        boolean valid = false;

        do {
            // take in a string
            inputStr = win.input(prompt);


            if  (!listContains(myList, inputStr)) {
                win.showMessage(errMessage);
            } else {
                valid = true;
            }

        } while (!valid);

        return inputStr;
    }

    // input valid if not already in list
    public String stringExists(String[] myList, String prompt, String errMessage) {
        String inputStr;
        boolean valid = false;

        do {
            // take in a string
            inputStr = (win.input(prompt)).toLowerCase();

            if(inputStr != null && inputStr.length() > 0) {
                if (!listContains(myList, inputStr)) {
                    win.showMessage(errMessage);
                } else {
                    valid = true;
                }
            } else { // user pressed cancel - return to previous dialog
                valid = true;
                inputStr = "";
            }

        } while (!valid);

        return inputStr;
    }

    // check if a list contains a value (ignoring case)
    private boolean listContains(String[] list, String item) {
        boolean found = false;

        for(String value : list)
            if(value != null)
                if(value.equalsIgnoreCase(item))
                    found = true;

        return found;
    }

    // int input with min & max values cancel = max , ok with no input = max (ie goes back to previous menu or exits)
    public int numberMenu(String prompt, int min, int max, String errMessage)     {
        int number = min-1;

        String numStr;
        do {
            numStr = win.input(prompt);
            // if cancel is clicked or ok is clicked with no input then numStr will be null
            // this is equal to selecting last option on each menu.

            if(numStr==null || numStr.equals("")) {
                return max;
            }

            try {
                number = Integer.parseInt(numStr);
                if (number < min || number > max)
                    win.showMessage(errMessage);
            } catch (NumberFormatException ex) {
                win.showMessage(errMessage);
            }
        } while (number < min || number > max);

        return number;
    }

    // int input with min & max values
    public Integer number(String prompt, int min, int max, String errMessage)     {
        Integer number = null;
        String numStr;
        do {
            numStr = win.input(prompt);
            if(numStr==null) { // cancel pressed
                number=null; // allow while to break
            } else { // ok pressed with no entry
                if(numStr.length() > 0) {
                    try {
                        number = Integer.parseInt(numStr);

                    } catch (NumberFormatException ex) {
                        win.showMessage(errMessage);
                    }
                } else {
                    win.showMessage(errMessage);
                    number = min-1; // ie stay in loop
                }
            }
        } while (number != null && ( number < min || number > max ));

        return number;
    }

    // double input with min & max values
    public Double number(String prompt, double min, double max, String errMessage)     {
        Double number = min-1;
        String numStr;
        do {
            numStr = win.input(prompt);

            // if cancel clicked then numStr will be null
            if(numStr == null){
                number = null;
            }else {
                try {
                    number = Double.parseDouble(numStr);
                    if (number < min || number > max)
                        win.showMessage(errMessage);
                } catch (NumberFormatException ex) {
                    win.showMessage(errMessage);
                }
            }
        } while (number != null && (number < min || number > max));

        return number;

    }

    // float input with min & max values
    public float number(String prompt, float min, float max, String errMessage)     {
        float number = min-1;

        String numStr = "";
        do {
            numStr = win.input(prompt);

            try {
                number = Float.parseFloat(numStr);
                if (number < min || number > max)
                    win.showMessage(errMessage);
            } catch (NumberFormatException ex) {
                win.showMessage(errMessage);
            }
        } while (number < min || number > max);
        return number;

    }

    // String to double conversion
    public double number(String prompt) {
        double number = 0;
        boolean valid = false;
        String numStr = "";

        do {
            numStr = win.input(prompt);
            try {
                number = Double.parseDouble(numStr);
                valid = true;
            } catch (NumberFormatException ex) {
                win.showMessage("Invalid Entry please try again");
            }
        } while (!valid);
        return number;
    }

    // When selling products check balance and stock and return error if value exceeds them
    public int number(String prompt, int min, int max,
                      double price, double balance,
                      String errMessage, String errMessage2){
        int number = min-1;
        String numStr ="";
        do {
            numStr = win.input(prompt);

            try {
                if(numStr==null|numStr.equals("")) {
                    return max;
                }
                number = Integer.parseInt(numStr);
                if (number < min || number > max){
                    win.showMessage(errMessage);
                }
                if(checkBalance(number, price, balance)){
                    win.showMessage(errMessage2);
                    //Force it to stay in loop
                    number = min - 1;
                }
            } catch (NumberFormatException ex) {
                win.showMessage(errMessage);
            }
        } while (number < min || number > max);

        return number;
    }
    //Gets the total price for the amount of the item selected
    public boolean checkBalance(int quantity, double price, double balance){
        return balance < quantity * price;
    }
    public int menuOption(String[] options) {

        int min = 1; // always 1.
        int max = options.length;
        String prompt = "Select Option";
        String errMessage = "Invalid option, please re-enter a number between " + min +  " and " + max;

        String menu = arrayToString(options);
        menu += "\n\n" + prompt;

        return numberMenu(menu, min, max, errMessage);
    }
    //Changes a String array to a long String to send to a csv file
    private String arrayToString(String[] options) {
        StringBuilder optionsStr = new StringBuilder();
        for(String option : options) {
            optionsStr.append(option).append("\n");
        }
        return optionsStr.toString();
    }

    //Convert ArrayList to String
    private String arrayToString(List<String> options) {
        StringBuilder optionsStr = new StringBuilder();

        for(String option : options) {
            optionsStr.append(option).append("\n");
        }
        return optionsStr.toString();
    }

    // display a drop down menu with the first as default
    public String option(String prompt, String[] options) {
        String option = win.option(prompt, options);
        return option;
    }

    public String stringNotInList(String prompt, String[] names, int min, int max, String errMessage, String errMessage2) {
        String inputStr;
        boolean valid = false;

        do{
            inputStr = string(prompt, min, max, errMessage);
            if(listContains(names,inputStr)){
                win.showMessage(errMessage2);
            }else{
                valid = true;
            }
        } while (!valid);

        return inputStr;
    }
}