public class Main {

    public static void main(String[] args) {
        int inCount = 0;
        int outCount = 1;

        while(outCount <=2 && inCount <= 3) {
            System.out.println(outCount+":" + inCount);
            outCount = outCount+1;
            inCount = inCount + 1;
        }


    }
}
