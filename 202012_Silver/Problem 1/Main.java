import java.util.*;
import java.io.*;

public class Main {
    
    public static class FastReader {

        private BufferedReader br;
        private StringTokenizer st;

        public FastReader() {br = new BufferedReader(new InputStreamReader(System.in));}

        public FastReader(String fileName) {
            try {br = new BufferedReader(new FileReader(fileName));}
            catch(FileNotFoundException e) {e.printStackTrace();}
        }

        private String next() {
            while(st == null || !st.hasMoreElements()) {
                try {st = new StringTokenizer(br.readLine());}
                catch(IOException e) {e.printStackTrace();}
            }
            return st.nextToken();
        }

        public int nextInt() {return Integer.parseInt(next());}

        public long nextLong() {return Long.parseLong(next());}

        public double nextDouble() {return Double.parseDouble(next());}

        public String nextString() {return next();}

        public String nextLine() {
            String str = "";
            try {str = br.readLine();}
            catch(IOException e) {e.printStackTrace();}
            return str;
        }

    }

    public static void main(String[] args) {

        FastReader fr = new FastReader();

        int n = fr.nextInt(), res = n - 1;
        Set<Integer>[] adj = new Set[n];
        for(int i=0; i < n; i++) adj[i] = new HashSet<>();

        for(int i=0; i < n-1 ; i++) {
            int a = fr.nextInt() - 1, b = fr.nextInt() - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        Queue<Integer> q = new LinkedList<Integer>();
        q.offer(0);

        while(!q.isEmpty()) {

            int curr = q.poll();
            res += (int)Math.ceil(Math.log(adj[curr].size() + 1) / Math.log(2));

            for(int nbor: adj[curr]) {
                adj[nbor].remove(curr);
                q.offer(nbor);
            }

        }

        System.out.println(res);

    }

}
