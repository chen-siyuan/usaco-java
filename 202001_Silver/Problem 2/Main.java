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

        FastReader fr = new FastReader("loan.in");

        long n = fr.nextLong(), k = fr.nextLong(), m = fr.nextLong(), l = 1, r = n / m + 1;

        while(l + 1 < r) if(check(n, k, m, (l + r) / 2)) l = (l + r) / 2;
        else r = (l + r) / 2;

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("loan.out")))) {
            pw.println(l);
            // System.out.println(l);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static boolean check(long n, long k, long m, long x) {

        if(x * m >= n) return false;

        long a = Math.min((long)Math.ceil((Math.log(n) - Math.log(x) - Math.log(m)) / (Math.log(x) - Math.log(x - 1))), k);

        long res = n - (long)Math.round(n * Math.pow(1 - 1. / x, a)) + (k - a) * m;
        // System.out.println(String.format("%d %d %d", x, a, res));

        return res >= n;
    }

}
