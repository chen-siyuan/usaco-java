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
        // FastReader fr = new FastReader("test1.in");

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

        // for(Integer e: E) System.out.printf("%d %d %d\n", e, X[e], Y[e]);

        // for(int i=0; i < n - 1; i++) for(int j=i + 1; j < n; j++) if(check(X, Y, i, j)) {
            // System.out.printf("%d %d\n", i, j);
        // }

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
                // System.out.printf("%d %d\n", e / 2, tmp);
                res0 = Math.min(e / 2, tmp);
                res1 = Math.max(e / 2, tmp);
                break;
            }
            if(index != A.size() - 1 && check(X, Y, e / 2, (tmp = A.get(index + 1) / 2))) {
                // System.out.printf("%d %d\n", e / 2, tmp);
                res0 = Math.min(e / 2, tmp);
                res1 = Math.max(e / 2, tmp);
                break;
            }
            // int tmp1;
            // if(index != 0 && index != A.size() - 1 && check(X, Y, (tmp = A.get(index - 1) / 2), (tmp1 = A.get(index + 1) / 2))) {
                // // System.out.printf("%d %d\n", e / 2, tmp);
                // res0 = Math.min(tmp, tmp1);
                // res1 = Math.max(tmp, tmp1);
                // break;
            // }

            A.remove(index);

        }

        for(int i=0; i < n; i++) if(i != res0 && i != res1) if(check(X, Y, i, res1)) {
            try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")))) {
                // pw.println(res1 + 1);
                System.out.println(res1);
            } catch(Exception ee) {System.out.println(ee);}
            return;
        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")))) {
            // pw.println(res0 + 1);
            System.out.println(res0);
        } catch(Exception ee) {System.out.println(ee);}

    }

    public static void swap(int[] A, int i) {
        int tmp = A[i * 2];
        A[i * 2] = A[i * 2 + 1];
        A[i * 2 + 1] = tmp;
    }

    public static boolean check(int[] X, int[] Y, int a, int b) {

        long[] u = new long[]{X[b * 2] - X[a * 2], Y[b * 2] - Y[a * 2]};
        long[] v = new long[]{X[b * 2 + 1] - X[a * 2], Y[b * 2 + 1] - Y[a * 2]};
        long[] w = new long[]{X[a * 2 + 1] - X[a * 2], Y[a * 2 + 1] - Y[a * 2]};

        double alpha = 1. * (w[0] * v[1] - w[1] * v[0]) / (u[0] * v[1] - u[1] * v[0]);
        double beta = 1. * (-w[0] * u[1] + w[1] * u[0]) / (u[0] * v[1] - u[1] * v[0]);

        // System.out.printf("%.2f %.2f\n", alpha, beta);

        return alpha + beta >= 1 && alpha >= 0 && beta >= 0;
    }

}
