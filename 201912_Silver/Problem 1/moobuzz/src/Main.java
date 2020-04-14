import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("moobuzz.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int index = Integer.parseInt(st.nextToken()) - 1;
        int[] digits = {1, 2, 4, 7, 8, 11, 13, 14};

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));

        pw.println((index/8) * 15 + digits[index%8]);

        pw.close();

    }

}
