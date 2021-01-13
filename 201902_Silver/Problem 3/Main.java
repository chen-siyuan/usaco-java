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

        FastReader fr = new FastReader("revegetate.in");

        int n = fr.nextInt(), m = fr.nextInt(), e = 0, cnt = 0, res = 0;

        int[] par = new int[n];
        Arrays.fill(par, -1);

        int[][] E = new int[m][2];
        for(int i=0; i < m; i++)
            if(fr.nextString().equals("S")) union(par, fr.nextInt() - 1, fr.nextInt() - 1);
            else E[e++] = new int[]{fr.nextInt() - 1, fr.nextInt() - 1};

        for(int i=0; i < n; i++) if(par[i] < 0) par[i] = -cnt++ - 1;

        int[] apar = new int[cnt];
        for(int i=0; i < cnt; i++) apar[i] = i;

        for(int i=0; i < e; i++)
            if(aunion(apar, -1 - par[find(par, E[i][0])], -1 - par[find(par, E[i][1])]))
                try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")))) {
                    pw.println("0");
                    return;
                } catch(Exception ee) {System.out.println(ee);}

        for(int i=0; i < cnt; i++) if(apar[i] == i) res++;

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")))) {
            pw.print(1);
            for(int i=0; i < res; i++) pw.print(0);
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

    public static boolean aunion(int[] apar, int a, int b) {
        if((a = afind(apar, a)) == (b = afind(apar, b))) return true;
        if(a + b == -1) return false;
        if(b >= 0) apar[b] = -1 - a;
        else apar[-1 - b] = a;
        return false;
    }

    public static int afind(int[] apar, int a) {
        return (apar[a] == a) ? a : ((apar[a] >= 0)
                ? (apar[a] = afind(apar, apar[a]))
                : (apar[a] = -1 - afind(apar, -1 - apar[a])));
    }

}
