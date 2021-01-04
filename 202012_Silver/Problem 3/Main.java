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

        FastReader fr = new FastReader();

        int n = fr.nextInt();

        int[] xs = new int[n], ys = new int[n];
        List<Integer> es = new ArrayList<>(), ns = new ArrayList<>();
        for(int i=0; i < n; i++) if(fr.nextString().equals("E")) {
            es.add(i);
            xs[i] = fr.nextInt();
            ys[i] = fr.nextInt();
        } else {
            ns.add(i);
            xs[i] = fr.nextInt();
            ys[i] = fr.nextInt();
        }

        es.sort((a, b) -> (ys[a] - ys[b]));
        ns.sort((a, b) -> (xs[a] - xs[b]));

        boolean[] dead = new boolean[n];
        int[] ks = new int[n];

        for(int i: es) for(int j: ns)
            if(!dead[i] && !dead[j] && xs[j] > xs[i] && ys[j] < ys[i] && xs[i] + ys[i] != xs[j] + ys[j])
                if(xs[i] + ys[i] > xs[j] + ys[j]) {
                    dead[j] = true;
                    ks[i] += ks[j] + 1;
                } else {
                    dead[i] = true;
                    ks[j] += ks[i] + 1;
                }

        for(int k: ks) System.out.println(k);

    }

}
