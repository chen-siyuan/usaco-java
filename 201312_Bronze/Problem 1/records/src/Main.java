import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("records.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("records.out")));

        pw.close();

    }

}
