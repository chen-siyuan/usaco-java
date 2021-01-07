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

        FastReader fr = new FastReader("berries.in");
        
        int n = fr.nextInt(), k = fr.nextInt() / 2;

        Integer[] bs = new Integer[n];
        for(int i=0; i < n; i++) bs[i] = fr.nextInt();
        Arrays.sort(bs, Comparator.comparingInt((a) -> (-a)));

        n = Math.min(n, k * 2);

        int res = 0;
        for(int i=1; i <= bs[0]; i++) res = Math.max(res, getRes(bs, n, k, i));

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")))) {
            pw.println(res);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static int getRes(Integer[] bs, int n, int k, int b) {

        int curr = 0;
        for(int i=0; i < n; i++) curr += bs[i] / b;

        if(curr >= k * 2) return b * k;
        
        Integer[] ts = bs.clone();
        Arrays.sort(ts, Comparator.comparingInt((a) -> (-(a % b))));

        int res = b * (curr - k);
        for(int i=0; i < Math.min(2 * k - curr, n); i++) res += ts[i] % b;

        return res;
    }

}
