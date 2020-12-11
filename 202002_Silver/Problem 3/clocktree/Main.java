import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        int n = 0;
        int[] deg = null;
        List<Integer>[] adj = null;

        try(BufferedReader br = new BufferedReader(new FileReader("clocktree.in"))) {
            
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());

            deg = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i < n; i++) deg[i] = Integer.parseInt(st.nextToken());

            adj = new List[n];
            for(int i=0; i < n; i++) adj[i] = new ArrayList<>();
            for(int i=0; i < n - 1; i++) {

                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;

                adj[a].add(b);
                adj[b].add(a);

            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        boolean[] searched = new boolean[n], color = new boolean[n];
        int t = 0, f = 0, diff = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        while(!q.isEmpty()) {

            int curr = 0;
            if(searched[curr = q.poll()]) continue;
            searched[curr] = true;

            if(color[curr]) {
                t += 1;
                diff += deg[curr];
            } else {
                f += 1;
                diff -= deg[curr];
            }

            for(int nbor: adj[curr]) {
                color[nbor] = !color[curr];
                q.offer(nbor);
            }

        }

        diff = (diff % 12 + 12) % 12;

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("clocktree.out")))) {

            switch(diff) {
                case 0:
                    pw.println(t + f);
                    break;
                case 1:
                    pw.println(t);
                    break;
                case 11:
                    pw.println(f);
                    break;
                default:
                    pw.println(0);
                    break;
            }

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
