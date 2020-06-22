import java.util.*;
import java.io.*;

public class Main2 {

    public static int n;
    public static int[][] map;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));
    
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        br.close();

        System.out.println(Arrays.deepToString(map));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));

        pw.close();

    }

}
