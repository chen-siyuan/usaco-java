import java.io.*;
import java.util.*;

public class Main {

    public static int n, res;
    public static int[] count;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("planting.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        res = Integer.MIN_VALUE;
        count = new int[n];

        for(int i=0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < 2; j++) count[Integer.parseInt(st.nextToken()) - 1]++;
        }

        br.close();

        for(int i=0; i < n; i++) res = Math.max(res, count[i]);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")));

        pw.println(res + 1);

        pw.close();
    
    }

}
