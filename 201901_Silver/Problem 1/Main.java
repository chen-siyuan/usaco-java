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
        
        FastReader fr = new FastReader("planting.in");

        int n = fr.nextInt(), res = 0;
        int[] cnt = new int[n];

        for(int i=0; i < 2*n - 2; i++) res = Math.max(res, ++cnt[fr.nextInt() - 1]);

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("planting.out")))) {
            pw.println(res + 1);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
