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
        
        FastReader fr = new FastReader("meetings.in");

        int n = fr.nextInt(), l = fr.nextInt(), tot = 0, cnt;

        int[] W = new int[n], X = new int[n], D = new int[n];
        List<Integer> L = new ArrayList<>(n), R = new ArrayList<>(n);
        for(int i=0; i < n; i++) {
            tot += (W[i] = fr.nextInt());
            X[i] = fr.nextInt();
            if((D[i] = fr.nextInt()) == 1) R.add(l - X[i]);
            else L.add(X[i]);
        }

        cnt = R.size();

        Integer[] P = new Integer[n];
        for(int i=0; i < n; i++) P[i] = i;

        Arrays.sort(P, Comparator.comparingInt(p -> X[p]));
        Collections.sort(L);
        Collections.sort(R);

        int il = 0, ir = 0, w = 0, t = 0;
        while(w * 2 < tot) if(il == n - cnt || ir != cnt && L.get(il) >= R.get(ir)) {
            t = R.get(ir);
            w += W[P[n - 1 - ir++]];
        } else {
            t = L.get(il);
            w += W[P[il++]];
        }

        Arrays.sort(P, Comparator.comparingInt(p -> D[p]));

        int res = 0;
        for(int i=0; i < n - cnt; i++) {

            int jl = n - cnt - 1, jr = n - 1, tmp;
            while(jl < jr)
                if(X[P[(jl + jr + 1) / 2]] <= X[P[i]]) jl = (jl + jr + 1) / 2;
                else jr = (jl + jr + 1) / 2 - 1;

            tmp = jl;

            jl = n - cnt - 1;
            while(jl < jr)
                if(X[P[(jl + jr + 1) / 2]] + t * 2 < X[P[i]]) jl = (jl + jr + 1) / 2;
                else jr = (jl + jr + 1) / 2 - 1;

            res += tmp - jl;

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")))) {
            pw.println(res);
        } catch(Exception ee) {System.out.println(ee);}

    }

}
