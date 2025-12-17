// Gustaf Ripe guri0112
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class InputReader {
    private static ArrayList<InputStream> usedStream = new ArrayList<>();
    private final Scanner input;
    private final InputStream source;

    public InputReader(){
        this(System.in);
    }
    public InputReader(InputStream source){
        if(usedStream.contains(source)){
            throw new IllegalStateException("");
        }
        this.source = source;
        usedStream.add(source);
        this.input = new Scanner(source);
    }
    public int readInteger(String prompt){
        System.out.print(prompt + "?>");
        int readInteger = input.nextInt();
        input.nextLine();
        return readInteger;

    }
    public double readDouble(String prompt){
        System.out.print(prompt + "?>");
        double decimalNumber = input.nextDouble();
        input.nextLine();
        return decimalNumber;
    }

    public String readLine(String prompt){
        System.out.print(prompt + "?>");
        return input.nextLine();
    }
    public void close(){
        input.close();
        usedStream.remove(source);
    }
}
