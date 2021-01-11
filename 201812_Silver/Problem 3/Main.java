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
        
        FastReader fr = new FastReader("mooyomooyo.in");

        int n = fr.nextInt(), k = fr.nextInt();

        int[] B = new int[n * 10], par = new int[n * 10];
        for(int i=0; i < n; i++) {
            String temp = fr.nextLine();
            for(int j=0; j < 10; j++) B[(n - 1 - i) * 10 + j] = temp.charAt(j) - '0';
        }

        boolean flag = true;
        while(flag) {

            flag = false;

            Arrays.fill(par, -1);
            for(int i=0; i < n * 10; i++) if(B[i] != 0) DFS(n, B, par, i, i);

            for(int i=0; i < n * 10; i++) if(-par[find(par, i)] >= k) {
                B[i] = 0;
                flag = true;
            }

            for(int i=0; i < 10; i++) {
                int s = 0;
                for(int f=0; f < n; f++) if(B[f * 10 + i] != 0) B[s++ * 10 + i] = B[f * 10 + i];
                for(; s < n; s++) B[s * 10 + i] = 0;
            }
            
        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")))) {
            for(int i=0; i < n; i++) {
                for(int j=0; j < 10; j++) pw.print(B[(n - 1 - i) * 10 + j]);
                pw.println();
            }
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static void DFS(int n, int[] B, int[] par, int p, int i) {

        if(B[p] != B[i] || par[i] >= 0) return;
        union(par, p, i);

        if(i / 10 > 0 && p != i - 10) DFS(n, B, par, i, i - 10);
        if(i / 10 < n - 1 && p != i + 10) DFS(n, B, par, i, i + 10);
        if(i % 10 > 0 && p != i - 1) DFS(n, B, par, i, i - 1);
        if(i % 10 < 9 && p != i + 1) DFS(n, B, par, i, i + 1);

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
