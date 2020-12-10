import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        int n = 0, m = 0, k = 0;
        int[] p = null;

        try(BufferedReader br = new BufferedReader(new FileReader("swap.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            p = new int[n];
            for(int i=0; i < n; i++) p[i] = i;

            for(int i=0; i < m; i++) {

                st = new StringTokenizer(br.readLine());

                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;

                for(int j=0; j < n; j++) if(l <= p[j] && p[j] <= r) p[j] = l + r - p[j];

            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        int[] par = new int[n];
        int[] pos = new int[n];
        List<List<Integer>> cycles = new ArrayList<>();

        int cnt = 0;
        for(int i=0; i < n; i++) if(par[i] == 0) {

            cnt++;

            List<Integer> cycle = new ArrayList<>();

            int ord = 0;
            int nxt = i;
            boolean flag = true;
            do {
                cycle.add(nxt);
                par[nxt] = cnt;
                pos[nxt] = ord++;
                nxt = p[nxt];
            } while(nxt != i);

            cycles.add(cycle);

        }

        int[] ans = new int[n];
        
        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")))) {

            for(int i=0; i < n; i++) {
                List<Integer> cycle = cycles.get(par[i] - 1);
                ans[cycle.get((pos[i] + k) % cycle.size())] = i;
            }

            for(int i=0; i < n; i++) pw.println(ans[i] + 1);
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
