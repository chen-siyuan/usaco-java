import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cowroute.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));

        pw.close();

    }

}
