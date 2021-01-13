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
        
        FastReader fr = new FastReader("herding.in");

        int n = fr.nextInt();

        int[] A = new int[n];
        for(int i=0; i < n; i++) A[i] = fr.nextInt();

        Arrays.sort(A);

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")))) {
            pw.println(minRes(n, A));
            pw.println(maxRes(n, A));
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static int minRes(int n, int[] A) {

        int l = 0, r = 0, max = 0;
        boolean flag = false;
        for(; l < n; l++) {
            while(r + 1 < n && A[r + 1] <= A[l] + n - 1) r++;
            if(max <= r - l + 1) {
                max = r - l + 1;
                flag |= max == n - 1 && A[r] - A[l] == n - 1;
            }
        }

        if(max == n - 1) return flag ? 1 : 2;
        else return n - max;
    }

    public static int maxRes(int n, int[] A) {
        return A[n - 1] - A[0]
            - Math.min(A[1] - A[0], A[n - 1] - A[n - 2])
            - (n - 1)
            + 1;
    }
    
}
