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

        int n = fr.nextInt();
        int[] xs = new int[n], ys = new int[n];
        Integer[] cows = new Integer[n];

        for(int i=0; i < n; i++) {
            xs[i] = fr.nextInt();
            ys[i] = fr.nextInt();
            cows[i] = i;
        }

        Arrays.sort(cows, (a, b) -> (xs[a] - xs[b]));
        for(int i=0; i < n; i++) xs[cows[i]] = i;
        Arrays.sort(cows, (a, b) -> (ys[a] - ys[b]));
        for(int i=0; i < n; i++) ys[cows[i]] = i;

        int[][] dp = new int[n][n];

        for(int i=0; i < n; i++) dp[xs[cows[i]]][ys[cows[i]]] = 1;

        for(int i=1; i < n; i++) dp[i][0] += dp[i - 1][0];
        for(int j=1; j < n; j++) dp[0][j] += dp[0][j - 1];
        for(int i=1; i < n; i++) for(int j=1; j < n; j++) dp[i][j] += dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];

        long res = n + 1;

        for(int i=0; i < n - 1; i++) for(int j=i+1; j < n; j++) {
            int minx = Math.min(xs[cows[i]], xs[cows[j]]),
                maxx = Math.max(xs[cows[i]], xs[cows[j]]),
                miny = Math.min(ys[cows[i]], ys[cows[j]]),
                maxy = Math.max(ys[cows[i]], ys[cows[j]]);

            res += (long)numRect(dp, 0, minx, miny, maxy)
                * (long)numRect(dp, maxx, n - 1, miny, maxy);
        }

        System.out.println(res);

    }

    public static int numRect(int[][] dp, int minx, int maxx, int miny, int maxy) {
        int res = dp[maxx][maxy];
        if(minx != 0) res -= dp[minx - 1][maxy];
        if(miny != 0) res -= dp[maxx][miny - 1];
        if(minx != 0 && miny != 0) res += dp[minx - 1][miny - 1];
        return res;
    }

}
