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

        FastReader fr = new FastReader("cowjump.in");

        int n = fr.nextInt();

        int[] X = new int[n * 2], Y = new int[n * 2];
        for(int i=0; i < n; i++) {

            X[i * 2] = fr.nextInt();
            Y[i * 2] = fr.nextInt();
            X[i * 2 + 1] = fr.nextInt();
            Y[i * 2 + 1] = fr.nextInt();

            if(X[i * 2] > X[i * 2 + 1]) {
                swap(X, i);
                swap(Y, i);
            }

        }

        Integer[] E = new Integer[n * 2];
        for(int i=0; i < n * 2; i++) E[i] = i;
        Arrays.sort(E, Comparator.comparingInt(e -> X[e]));

        int res0 = -1, res1 = -1;
        List<Integer> A = new ArrayList<>();
        for(Integer e: E) if(e % 2 == 0) {

            if(A.isEmpty()) A.add(e);
            else {
                int l = 0, r = A.size();
                while(l < r)
                    if(Y[e] <= Y[A.get((l + r) / 2)]) r = (l + r) / 2;
                    else l = (l + r) / 2 + 1;
                A.add(l, e);
            }

        } else {
            
            int index = A.indexOf(e - 1);
            int tmp;

            if(index != 0 && check(X, Y, e / 2, (tmp = A.get(index - 1) / 2))) {
                res0 = Math.min(e / 2, tmp);
                res1 = Math.max(e / 2, tmp);
                break;
            }
            if(index != A.size() - 1 && check(X, Y, e / 2, (tmp = A.get(index + 1) / 2))) {
                res0 = Math.min(e / 2, tmp);
                res1 = Math.max(e / 2, tmp);
                break;
            }

            A.remove(index);

        }

        for(int i=0; i < n; i++) if(i != res0 && i != res1) if(check(X, Y, i, res1)) {
            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")))) {
                pw.println(res1 + 1);
            } catch(Exception ee) {System.out.println(ee);}
            return;
        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")))) {
            pw.println(res0 + 1);
        } catch(Exception ee) {System.out.println(ee);}

    }

    public static void swap(int[] A, int i) {
        int tmp = A[i * 2];
        A[i * 2] = A[i * 2 + 1];
        A[i * 2 + 1] = tmp;
    }

    // Between
    public static boolean bt(long[] a, long[] b, long[] x) {
        return (a[0] == b[0])
            ? (a[1] <= x[1] && x[1] <= b[1] || b[1] <= x[1] && x[1] <= a[1])
            : (a[0] <= x[0] && x[0] <= b[0] || b[0] <= x[0] && x[0] <= a[0]);
    }

    // Area
    public static long aa(long[] a, long[] b, long[] c) {
        return (b[0] - a[0]) * (c[1] - a[1]) - (b[1] - a[1]) * (c[0] - a[0]);
    }

    public static boolean check(int[] X, int[] Y, int a, int b) {

        long[] o = new long[]{X[a * 2], Y[a * 2]};
        long[] u = new long[]{X[b * 2], Y[b * 2]};
        long[] v = new long[]{X[b * 2 + 1], Y[b * 2 + 1]};
        long[] w = new long[]{X[a * 2 + 1], Y[a * 2 + 1]};

        long alpha = aa(o, u, w);
        long beta = aa(o, w, v);
        long gamma = aa(o, u, v);

        if(gamma > 0) return alpha + beta >= gamma && alpha >= 0 && beta >= 0;
        if(gamma < 0) return alpha + beta <= gamma && alpha <= 0 && beta <= 0;

        if(bt(u, v, o)) return true;
        if(aa(u, w, v) != 0) return false;
        return bt(o, w, u) || bt(o, w, v);
    }

}
