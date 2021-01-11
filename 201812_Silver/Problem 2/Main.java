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

        FastReader fr = new FastReader("convention2.in");

        int n = fr.nextInt(), res = 0;

        int[][] C = new int[n][];
        for(int i=0; i < n; i++) C[i] = new int[]{i, fr.nextInt(), fr.nextInt()};
        Arrays.sort(C, Comparator.comparingInt(c -> c[1]));

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(n, Comparator.comparingInt(a -> C[a][0]));

        int c = 0;
        int t = C[0][1];
        while(true) {

            while(c < n && C[c][1] <= t) pq.offer(c++);
            if(pq.isEmpty()) {
                if(c == n) break;
                t = C[c][1];
            } else {
                int curr = pq.poll();
                res = Math.max(res, t - C[curr][1]);
                t += C[curr][2];
            }

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")))) {
            pw.println(res);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
