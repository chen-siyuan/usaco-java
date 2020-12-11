import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        int n = 0, m = 0, c = 0;
        int[] gain = null;
        List<Integer>[] adj = null;

        try(BufferedReader br = new BufferedReader(new FileReader("time.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            gain = new int[n];
            for(int i=0; i < n; i++) gain[i] = Integer.parseInt(st.nextToken());

            adj = new List[n];
            for(int i=0; i < n; i++) adj[i] = new ArrayList<>();
            for(int i=0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                adj[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
            }

        } catch(Exception e) {
            System.out.println(e);
        }

        int res = 0;
        int[] dp = new int[n], prev = new int[n];
        for(int i=1; i < n; i++) prev[i] = Integer.MIN_VALUE;
        for(int i=0; i < 1000; i++) {

            for(int j=0; j < n; j++) dp[j] = Integer.MIN_VALUE;
            for(int j=0; j < n; j++) if(prev[j] != Integer.MIN_VALUE)
                for(int nbor: adj[j]) dp[nbor] = Math.max(dp[nbor], prev[j] + gain[nbor] - (2 * i + 1) * c);
            for(int j=0; j < n; j++) prev[j] = dp[j];

            res = Math.max(res, prev[0]);

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.out")))) {
            pw.println(res);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
