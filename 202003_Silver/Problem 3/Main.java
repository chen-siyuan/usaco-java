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

        FastReader fr = new FastReader("moop.in");

        int n = fr.nextInt();

        int[][] C = new int[n][];
        for(int i=0; i < n; i++) C[i] = new int[]{fr.nextInt(), fr.nextInt()};

        Arrays.sort(C, (c1, c2) -> ((c1[0] == c2[0]) ? (c1[1] - c2[1]) : (c1[0] - c2[0])));

        List<int[]> A = new ArrayList<>();

        for(int[] c: C)
            if(A.isEmpty() || c[1] < A.get(0)[0]) A.add(0, new int[]{c[1], c[1]});
            else {
                int tmp = A.get(0)[0];
                while(!A.isEmpty() && A.get(0)[1] < c[1]) A.remove(0);
                if(A.isEmpty() || c[1] < A.get(0)[0]) A.add(0, new int[]{tmp, c[1]});
                else A.add(0, new int[]{tmp, A.remove(0)[1]});
            }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")))) {
            pw.println(A.size());
        } catch(Exception ee) {System.out.println(ee);}

    }

}
