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
        
        FastReader fr = new FastReader("fenceplan.in");

        int n = fr.nextInt(), e = fr.nextInt(), res = Integer.MAX_VALUE;

        int[] xs = new int[n], ys = new int[n], par = new int[n], minxs = new int[n], maxxs = new int[n], minys = new int[n], maxys = new int[n];
        for(int i=0; i < n; i++) {
            xs[i] = fr.nextInt();
            ys[i] = fr.nextInt();
            par[i] = i;
            minxs[i] = xs[i];
            maxxs[i] = xs[i];
            minys[i] = ys[i];
            maxys[i] = ys[i];
        }
        for(int i=0; i < e; i++) union(par, minxs, maxxs, minys, maxys, fr.nextInt() - 1, fr.nextInt() - 1);

        for(int i=0; i < n; i++) {
            int tmp = find(par, i);
            res = Math.min(res, maxys[tmp] - minys[tmp] + maxxs[tmp] - minxs[tmp]);
        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")))) {
            pw.println(res * 2);
        } catch(Exception ee) {System.out.println(ee);}

    }

    public static void union(int[] par, int[] minxs, int[] maxxs, int[] minys, int[] maxys, int a, int b) {
        if((a = find(par, a)) == (b = find(par, b))) return;
        par[b] = a;
        minxs[a] = Math.min(minxs[a], minxs[b]);
        maxxs[a] = Math.max(maxxs[a], maxxs[b]);
        minys[a] = Math.min(minys[a], minys[b]);
        maxys[a] = Math.max(maxys[a], maxys[b]);
    }

    public static int find(int[] par, int a) {
        return (par[a] == a) ? a : (par[a] = find(par, par[a]));
    }

}
