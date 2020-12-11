import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        int n = 0, q = 0;
        int[][] l = null;
        int[][] qs = null;

        try(BufferedReader br = new BufferedReader(new FileReader("threesum.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            l = new int[n][2];
            for(int i=0; i < n; i++) l[i][0] = Integer.parseInt(st.nextToken());
            for(int i=0; i < n; i++) l[i][1] = i;

            qs = new int[q][2];
            for(int i=0; i < q; i++) {
                st = new StringTokenizer(br.readLine());
                qs[i][0] = Integer.parseInt(st.nextToken()) - 1;
                qs[i][1] = Integer.parseInt(st.nextToken()) - 1;
            }
            
        } catch(Exception e) {
            System.out.println(e);
        }

        Arrays.sort(l, (a, b) -> (a[0] - b[0]));

        long res = 0;
        long[][] dp = new long[n][n];
        for(int i=0; i < n - 2; i++) {

            int h = i + 1;
            int t = n - 1;
            while(h < t) if(l[i][0] + l[h][0] + l[t][0] == 0) {
                int temp = h;
                while(temp < t && l[temp][0] == l[h][0]) dp[Math.min(l[i][1], Math.min(l[temp][1], l[t][1]))][Math.max(l[i][1], Math.max(l[temp++][1], l[t][1]))]++;
                t--;
            } else if(l[i][0] + l[h][0] + l[t][0] < 0) h++;
            else t--;
                
        }

        for(int i=3; i < n; i++) for(int j=0; j < n - i; j++)
            dp[j][j + i] += dp[j][j + i - 1] + dp[j + 1][j + i] - dp[j + 1][j + i - 1];

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")))) {
            for(int i=0; i < q; i++) pw.println(dp[qs[i][0]][qs[i][1]]);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
