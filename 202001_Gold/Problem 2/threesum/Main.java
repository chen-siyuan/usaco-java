import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        try(BufferedReader br = new BufferedReader(new FileReader("threesum.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] l = new int[n];
            for(int i=0; i < n; i++) l[i] = Integer.parseInt(st.nextToken());
            
            long[][] dp = new long[n][n];
            int[] memo = new int[2000001]; // i -> i + 1000000
            for(int i=0; i < n - 2; i++) {
                
                memo[l[i + 1] + 1000000]++;

                for(int j=i + 2; j < n; j++) {
                    int target = -l[i] - l[j];
                    if(-1000000 <= target && target <= 1000000) dp[i][j] = memo[target + 1000000];
                    memo[l[j] + 1000000]++;
                }

                for(int j=i + 1; j < n; j++) memo[l[j] + 1000000]--;

            }
            
            for(int i=3; i < n; i++) for(int j=0; j < n - i; j++)
                dp[j][j + i] += dp[j][j + i - 1] + dp[j + 1][j + i] - dp[j + 1][j + i - 1];

            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("threesum.out")))) {

                for(int i=0; i < q; i++) {
                    st = new StringTokenizer(br.readLine());
                    int a = Integer.parseInt(st.nextToken()) - 1;
                    int b = Integer.parseInt(st.nextToken()) - 1;
                    pw.println(dp[a][b]);
                }
                
            } catch(Exception e) {
                System.out.println(e);
            }

        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
