import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileHandler {

    public List<String> read(String filename) {

        List<String> list = new ArrayList<>();

        try {

            // Create a file object
            File file = new  File(filename);

            // if file doesnt exists, then create it
            if (!file.exists())
                file.createNewFile();

            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            list = br.lines().collect(Collectors.toList());
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Write string to a file
     * Note: data must be presented with newlines as necessary
     */
    private void write(String filename, String content) {
        try {

            // Create a file object
            File file = new  File(filename);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }


            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Add to existing csv file
    public void append(String filename, String content) {
        try {

            // Create a file object
            File file = new  File(filename);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }


            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String filename, String[] list) {
        String content = stringifyList(list);
        write(filename, content);
    }

    public void write(String filename, List<String> list) {
        String content = stringifyList(list);
        write(filename, content);
    }
    //Convert Array to a long String
    private String stringifyList(String[] list) {
        String content="";
        for(String str : list) {
            content += str+"\n";
        }
        return content;
    }
    //Convert List to a long String
    private String stringifyList(List<String> list) {
        String content="";
        for(String str : list) {
            content += str+"\n";
        }
        return content;
    }


}