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

        FastReader fr = new FastReader("wormsort.in");

        int n = fr.nextInt(), m = fr.nextInt();

        int[] par = new int[n];
        for(int i=0; i < n; i++) par[i] = -1;
        for(int i=0; i < n; i++) union(par, i, fr.nextInt() - 1);
        for(int i=0; i < n; i++) if(par[i] >= 0) par[i] = find(par, i);

        int[][] E = new int[m][3];
        for(int i=0; i < m; i++) E[i] = new int[]{fr.nextInt() - 1, fr.nextInt() - 1, fr.nextInt()};
        Arrays.sort(E, Comparator.comparingInt(e -> -e[2]));

        int[] npar = new int[n];
        for(int i=0; i < n; i++) npar[i] = -1;

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormsort.out")))) {

            if(check(n, par, npar)) pw.println(-1);
            else for(int i=0; i < m; i++) {
                union(npar, E[i][0], E[i][1]);
                if(check(n, par, npar)) {
                    pw.println(E[i][2]);
                    break;
                }
            }

        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static boolean check(int n, int[] par, int[] npar) {
        for(int i=0; i < n; i++) if(par[i] >= 0 && find(npar, i) != find(npar, par[i])) return false;
        return true;
    }

    public static void union(int[] par, int a, int b) {

        if((a = find(par, a)) == (b = find(par, b))) return;

        if(a > b) {
            par[b] += par[a];
            par[a] = b;
        } else {
            par[a] += par[b];
            par[b] = a;
        }

    }

    public static int find(int[] par, int a) {
        return (par[a] < 0) ? a : (par[a] = find(par, par[a]));
    }

}
