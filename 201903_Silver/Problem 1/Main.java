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

        boolean[] std = new boolean[n], a = tb(fr.nextString()), b = tb(fr.nextString());
        Arrays.fill(std, true);

        if(n == 2) {
            res = m(std, a, b) ? "-1" : String.format("%d %d", 1, 1);
        } else {
            if(!m(std, a, b)) res = (m(std, a, tb(fr.nextString())))
                ? String.format("%d %d", 2, col(a, b) + 1)
                : String.format("%d %d", 1, col(b, a) + 1);
            else {
                int row = 2;
                boolean[] c;
                res = "-1";
                for(; row < n; row++) if(!m(std, a, (c = tb(fr.nextString())))) {
                    res = String.format("%d %d", row + 1, col(a, c) + 1);
                    break;
                }
            }

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out")))) {
            pw.println(res);
        } catch(Exception ee) {System.out.println(ee);}

    }

    public static boolean eq(boolean[] a, boolean[] b) {
        return Arrays.equals(a, b);
    }

    public static boolean[] xor(boolean[] a, boolean[] b) {
        boolean[] res = new boolean[a.length];
        for(int i=0; i < a.length; i++) res[i] = a[i] ^ b[i];
        return res;
    }

    // To binary
    public static boolean[] tb(String str) {
        boolean[] res = new boolean[str.length()];
        for(int i=0; i < res.length; i++) res[i] = str.charAt(i) == 'R';
        return res;
    }

    // Matches
    public static boolean m(boolean[] std, boolean[] a, boolean[] b) {
        return eq(a, b) || eq(xor(a, b), std);
    }

    public static int col(boolean[] a, boolean[] b) {
        boolean[] tmp = xor(a, b);
        if(tmp[0] == tmp[1]) {for(int i=2; i < a.length; i++) if(tmp[0] != tmp[i]) return i;}
        else return (tmp[0] == tmp[2]) ? 1 : 0;
        return -1;
    }

}
