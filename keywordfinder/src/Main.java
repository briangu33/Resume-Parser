import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Brian Gu (brian.gu@addepar.com)
 */
public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            sb.append(scan.nextLine() + "\n");
        }
        String input = sb.toString();

    }

}
