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
        
        FastReader fr = new FastReader("milkvisits.in");

        int n = fr.nextInt(), m = fr.nextInt();

        boolean[] C = new boolean[n];
        String tmp = fr.nextString();
        for(int i=0; i < n; i++) C[i] = tmp.charAt(i) == 'G';

        int[] par = new int[n];
        Arrays.fill(par, -1);
        for(int i=0; i < n - 1; i++) {
            int a = fr.nextInt() - 1, b = fr.nextInt() - 1;
            if(C[a] == C[b]) union(par, a, b);
        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")))) {
            for(int i=0; i < m; i++) {
                int a = find(par, fr.nextInt() - 1), b = find(par, fr.nextInt() - 1);
                boolean c = "G".equals(fr.nextString());
                if(a != b || C[a] == c) pw.print(1);
                else pw.print(0);
            }
            pw.println();
        } catch(Exception ee) {System.out.println(ee);}

    }

    public static void union(int[] par, int a, int b) {
        if((a = find(par, a)) == (b = find(par, b))) return;
        par[a] += par[b];
        par[b] = a;
    }

    public static int find(int[] par, int a) {
        return (par[a] < 0) ? a : (par[a] = find(par, par[a]));
    }

}
