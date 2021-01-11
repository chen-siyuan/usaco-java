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

        FastReader fr = new FastReader("convention.in");

        int n = fr.nextInt(), m = fr.nextInt(), c = fr.nextInt();

        int[] T = new int[n];
        for(int i=0; i < n; i++) T[i] = fr.nextInt();
        Arrays.sort(T);

        int l = -1, r = T[n - 1];
        while(l + 1 < r)
            if(check(n, m, c, T, (l + r) / 2)) r = (l + r) / 2;
            else l = (l + r) / 2;

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")))) {
            pw.println(r);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static boolean check(int n, int m, int c, int[] T, int t) {

        if(t < 0) return false;

        int currm = -1, currt = 0, currc = c;

        for(int i=0; i < n; i++) if(currc == c || T[i] - currt > t) {
            if(++currm == m) return false;
            currt = T[i];
            currc = 1;
        } else {
            currc++;
        }

        return true;
    }

}
