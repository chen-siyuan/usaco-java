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

        FastReader fr = new FastReader("mountains.in");

        int n = fr.nextInt(), res = 0;

        int[][] V = new int[n][2];
        for(int i=0; i < n; i++) V[i] = new int[]{fr.nextInt(), fr.nextInt()};

        Arrays.sort(V, Comparator.comparingInt(v -> v[0]));

        boolean[] memo = new boolean[n];

        int curr = V[0][0] + V[0][1];
        for(int i=1; i < n; i++)
            if(curr >= V[i][0] + V[i][1]) memo[i] = true;
            else curr = V[i][0] + V[i][1];

        curr = V[n - 1][1] - V[n - 1][0];
        for(int i=n - 2; i >= 0; i--)
            if(curr >= V[i][1] - V[i][0]) memo[i] = true;
            else curr = V[i][1] - V[i][0];

        for(int i=0; i < n; i++) if(!memo[i]) res++;

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")))) {
            pw.println(res);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
