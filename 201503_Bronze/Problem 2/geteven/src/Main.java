import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("geteven.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("geteven.out")));

        pw.close();

    }

}
