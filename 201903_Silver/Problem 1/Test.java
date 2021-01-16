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
            while(st == null || !st.hasMoreElements()) {
                try {st = new StringTokenizer(br.readLine());}
                catch(IOException ee) {ee.printStackTrace();}
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
            catch(IOException ee) {ee.printStackTrace();}
            return str;
        }

    }

    public static void main(String[] args) {

        FastReader fr = new FastReader("leftout.in");

        int n = fr.nextInt();
        String res = "";

        if(n == 2) {
            long a = tb(fr.nextString()), b = tb(fr.nextString());
            res = (a == b || a + b == 3) ? "-1" : String.format("%d %d", 1, 1);
        } else {
            long std = (1L << n) - 1, a = tb(fr.nextString()), b = tb(fr.nextString());
            if(!m(std, a, b)) res = (m(std, a, tb(fr.nextString())))
                ? String.format("%d %d", 2, n - col(std, a, b))
                : String.format("%d %d", 1, n - col(std, b, a));
            else {
                int row = 2;
                long c;
                res = "-1";
                for(; row < n; row++) if(!m(std, a, (c = tb(fr.nextString())))) {
                    res = String.format("%d %d", row + 1, n - col(std, a, c));
                    break;
                }
            }

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out")))) {
            System.out.println(res);
            pw.println(res);
        } catch(Exception ee) {System.out.println(ee);}

    }

    // To binary
    public static long tb(String str) {
        long res = 0;
        for(char c: str.toCharArray()) res = (res << 1) + (c == 'R' ? 1 : 0);
        return res;
    }

    // Matches
    public static boolean m(long std, long a, long b) {
        return a == b || a + b == std;
    }

    public static int col(long std, long rt, long wg) {
        if(csb(rt ^ wg) != 1) wg ^= std;
        return (int)Math.round(Math.log(rt ^ wg) / Math.log(2));
    }

    // Count set bits
    public static int csb(long a) {
        int res = 0;
        while(a > 0) {
            a &= a - 1;
            res++;
        }
        return res;
    }

}
