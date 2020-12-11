import java.util.*;
import java.io.*;

public class Main {

    public static void DFS(int[] gain, List<Integer>[] adj, int pos, int len, int sum, int[] memo, int max) {

        if(pos == 0 && memo[0] < sum) memo[0] = sum;
        if(len * 2 + 1 >= max) return;

        for(int nbor: adj[pos]) DFS(gain, adj, nbor, len + 1, sum + gain[nbor] - len * 2 - 1, memo, max);
    }
    
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

        int max = Integer.MIN_VALUE;
        for(int val: gain) max = Math.max(max, val);

        int[] res = new int[1];
        DFS(gain, adj, 0, 0, 0, res, max);


        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("time.out")))) {
            pw.println(res[0]);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
