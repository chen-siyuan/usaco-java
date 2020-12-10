import java.util.*;
import java.io.*;

public class Main {

    public static long add(long a, long b) {
        return (a + b) % 1000000007;
    }

    public static long mul(long a, long b) {
        return (a * b) % 1000000007;
    }

    public static void main(String[] args) {

        int n = 0;
        int[][] ps = null;
        Map<Integer, List<int[]>> H = new HashMap<>(), V = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader("triangles.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            ps = new int[n][2];

            for(int i=0; i < n; i++) {

                st = new StringTokenizer(br.readLine());

                ps[i][0] = Integer.parseInt(st.nextToken());
                ps[i][1] = Integer.parseInt(st.nextToken());

                if(!H.containsKey(ps[i][0])) H.put(ps[i][0], new ArrayList<>());
                H.get(ps[i][0]).add(new int[]{i, ps[i][1]});

                if(!V.containsKey(ps[i][1])) V.put(ps[i][1], new ArrayList<>());
                V.get(ps[i][1]).add(new int[]{i, ps[i][0]});

            }
        
        } catch(Exception e) {
            System.out.println(e);
        }

        long[] sxs = new long[n], sys = new long[n];

        for(int key: H.keySet()) {

            List<int[]> col = H.get(key);
            int l = col.size();
            if(l == 1) continue;

            Collections.sort(col, (a, b) -> (a[1] - b[1]));

            int[] prev = col.get(0);
            for(int[] p: col) sys[prev[0]] = add(sys[prev[0]], (long)(p[1] - prev[1]));

            int i = -1;
            for(int[] p: col) if(++i != 0) {
                sys[p[0]] = add(sys[prev[0]], (long)(p[1] - prev[1]) * (2 * i - l));
                prev = p;
            }

        }

        for(int key: V.keySet()) {

            List<int[]> row = V.get(key);
            int l = row.size();
            if(l == 1) continue;

            Collections.sort(row, (a, b) -> (a[1] - b[1]));

            int[] prev = row.get(0);
            for(int[] p: row) sxs[prev[0]] = add(sxs[prev[0]], (long)(p[1] - prev[1]));

            int i = -1;
            for(int[] p: row) if(++i != 0) {
                sxs[p[0]] = add(sxs[prev[0]], (long)(p[1] - prev[1]) * (2 * i - l));
                prev = p;
            }

        }

        long res = 0;
        for(int i=0; i < n; i++) res = add(res, mul(sxs[i], sys[i]));

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")))) {
            pw.println(res);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
