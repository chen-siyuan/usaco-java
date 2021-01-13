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

        FastReader fr = new FastReader("paintbarn.in");

        int n = fr.nextInt(), k = fr.nextInt(), m = 1000, res = 0;

        int[][] H = new int[m][m + 1];
        for(int i=0; i < n; i++) {
            int x1 = fr.nextInt(), y1 = fr.nextInt(), x2 = fr.nextInt(), y2 = fr.nextInt();
            for(int j=x1; j < x2; j++) {H[j][y1]++; H[j][y2]--;}
        }

        int[] dp = new int[m];
        for(int i=0; i < m; i++) {
            if((dp[0] = H[i][0]) == k) res++;
            for(int j=1; j < m; j++) if((dp[j] = dp[j - 1] + H[i][j]) == k) res++;
        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")))) {
            pw.println(res);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
