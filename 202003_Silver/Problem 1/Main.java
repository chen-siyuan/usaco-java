import java.util.*;
import java.io.*;

public class Main {
    
    public static class FastReader {

        private BufferedReader br;
        private StringTokenizer st;

        public FastReader() {br = new BufferedReader(new InputStreamReader(System.in));}

        public FastReader(String fileName) {
            try {br = new BufferedReader(new FileReader(fileName));}
            catch(FileNotFoundException ee) {ee.printStackTrace();}
        }

        private String next() {
            while(st == null || !st.hasMoreElements())
                try {st = new StringTokenizer(br.readLine());}
                catch(IOException ee) {ee.printStackTrace();}
            return st.nextToken();
        }

        public int nextInt() {return Integer.parseInt(next());}

        public long nextLong() {return Long.parseLong(next());}

        public double nextDouble() {return Double.parseDouble(next());}

        public String nextString() {return next();}

        public String nextLine() {
            String str = "";
            try {str = br.readLine();}
            catch(IOException ee) {ee.printStackTrace();}
            return str;
        }

    }

    public static void main(String[] args) {
        
        FastReader fr = new FastReader("socdist.in");

        int n = fr.nextInt(), m = fr.nextInt();
        
        long[][] I = new long[m][2];
        for(int i=0; i < m; i++) {
            I[i][0] = fr.nextLong();
            I[i][1] = fr.nextLong();
        }

        Arrays.sort(I, Comparator.comparingLong(i -> i[0]));

        long l = 1, r = I[m - 1][1] + 1;
        while(l < r)
            if(check(n, m, I, (l + r + 1) / 2)) l = (l + r + 1) / 2;
            else r = (l + r + 1) / 2 - 1;

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")))) {
            pw.println(l);
        } catch(Exception ee) {System.out.println(ee);}

    }

    public static boolean check(int n, int m, long[][] I, long k) {

        int cnt = 0;
        long pos = -k;
        for(int i=0; i < m; i++) if(pos + k <= I[i][1]) {

            pos = Math.max(pos + k, I[i][0]);

            int tmp = (int)((I[i][1] - pos) / k);
            cnt += tmp + 1;
            pos += tmp * k;

            if(cnt >= n) return true;
        }

        return false;
    }

}
