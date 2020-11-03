// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;

public class Display {
    Font font = new Font( "Courier New", 1, 24);

    public Display() {
        UIManager.put("OptionPane.messageFont", this.font);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog((Component)null, message);
    }

    public void showMessage(List<String> myList){
        String str = "";
        for(String s: myList) {
            str += s + "\n";
        }
        //Calls Original Show Message method to display
        showMessage(str);
    }
    // take in values for the 2 buttons
    public Integer optionMessage(String leftButton, String rightButton, String[] list){
        //UIManager.put("OptionPane.cancelButtonText", rightButton);
        //UIManager.put("OptionPane.okButtonText", leftButton);
        Object[] choices = {leftButton, rightButton};
        Object defaultChoice = choices[0];
        String strList = "";
        for(String str: list){
            strList += str + "\n";
        }
        return JOptionPane.showOptionDialog(null, strList, "Cart Items", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, choices, defaultChoice);
    }
    public void showMessage(String[] myList){
        String str = "";
        for(String s: myList) {
            str += s + "\n";
        }
        //Calls Original Show Message method to display
        showMessage(str);
    }

    public int confirmYesNo(String message, String title) {
        return JOptionPane.showConfirmDialog((Component)null, message, title, 0);
    }

    public String input(String prompt) {
        String opt = JOptionPane.showInputDialog(prompt);
        if(opt==null) {
            return null;
        }
        return opt;

    }

    public String input(String prompt, int option) {
        final int DEFAULT_OPTION        = 0;
        final int OK_OPTION             = 1;
        final int CANCEL_OPTION         = 2;
        final int YES_NO_OPTION         = 3;
        final int OK_CANCEL_OPTION      = 4;
        final int YES_NO_CANCEL_OPTION  = 5;

        int jOption = 0;

        switch (option) {
            case DEFAULT_OPTION:
                jOption = JOptionPane.DEFAULT_OPTION;
                break;
            case OK_OPTION:
                jOption = JOptionPane.OK_OPTION;
                break;
            case CANCEL_OPTION:
                jOption = JOptionPane.CANCEL_OPTION;
                break;
            case YES_NO_OPTION:
                jOption = JOptionPane.YES_NO_OPTION;
                break;
            case OK_CANCEL_OPTION:
                jOption = JOptionPane.OK_CANCEL_OPTION;
                break;
            case YES_NO_CANCEL_OPTION:
                jOption = JOptionPane.YES_NO_CANCEL_OPTION;
                break;
        }


        return JOptionPane.showInputDialog(prompt,jOption);
    }

    // Show input with drop down selections (returns String in this case)
    public String option(String prompt, String[] options) {
        String option = "";
        if(options.length != 0) {
            option = (String) JOptionPane.showInputDialog(null, prompt, null, JOptionPane.QUESTION_MESSAGE
                    , null, options, options[0]);
        }
        return option;
    }

    public void showTable(String title, String[] headers, String[][] data) {
        JDialog jd = new JDialog();
        jd.setDefaultCloseOperation(2);
        jd.setTitle(title);
        JTable jt = new JTable(data, headers);
        JTableHeader header = jt.getTableHeader();
        header.setFont(this.font);
        jd.add(header, "North");
        int tableWidth = 700;
        int tableHeight = 300;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screen.width / 2 - tableWidth / 2;
        int y = screen.height / 2 - tableHeight / 2;
        jd.setBounds(x, y, tableWidth, tableHeight);
        jt.setBounds(40, 60, 200, 500);
        jt.setFont(this.font);
        jt.setRowHeight(40);
        jd.setLayout(new BorderLayout());
        jd.add(jt, "Center");
        jd.setBounds(500, 250, 600, 300);
        jd.setVisible(true);
        jd.setModal(true);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, null,JOptionPane.ERROR_MESSAGE);
    }
}

