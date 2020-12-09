import java.util.*;
import java.io.*;

public class Main {

    public static void merge(List<Integer>[] al, int[] par, List<Integer>[] rpar, Queue<Integer> q, int a, int b) {

        a = par[a];
        b = par[b];

        if(rpar[a].size() < rpar[b].size()) {
            int temp = a;
            a = b;
            b = temp;
        }

        for(int t: rpar[b]) {
            par[t] = a;
            rpar[a].add(t);
        }

        al[a].addAll(al[b]);
        al[b].clear();

        if(al[a].size() > 1) q.offer(a);

    }

    
    public static void main(String[] args) {
        
        int n = 0, m = 0;
        List<Integer>[] al = null;

        try(BufferedReader br = new BufferedReader(new FileReader("fcolor.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            al = new ArrayList[n];
            for(int i=0; i < n; i++) al[i] = new ArrayList<>();

            for(int i=0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                al[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        int[] par = new int[n];
        List<Integer>[] rpar = new ArrayList[n];
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i < n; i++) {

            par[i] = i;
            rpar[i] = new ArrayList<>();
            rpar[i].add(i);
            if(al[i].size() > 1) q.offer(i);

        }

        while(!q.isEmpty()) {

            int curr = q.peek();
            if(al[curr].size() < 2) {
                q.poll();
                continue;
            }

            int rer = al[curr].remove(0);
            if(par[rer] == par[al[curr].get(0)]) continue;

            merge(al, par, rpar, q, rer, al[curr].get(0));

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fcolor.out")))) {

            int co = 0;
            int[] cnt = new int[n];

            for(int i=0; i < n; i++) {
                if(cnt[par[i]] == 0) cnt[par[i]] = ++co;
                System.out.println(cnt[par[i]]);
            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
