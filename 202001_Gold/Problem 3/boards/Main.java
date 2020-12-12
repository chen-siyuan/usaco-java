import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) {
        
        int n = 0, p = 0;
        int[][] E = null;
        Map<Integer, List<int[]>> H = new TreeMap<>(), V = new TreeMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader("boards.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            E = new int[p][4];
            for(int i=0; i < p; i++) {

                st = new StringTokenizer(br.readLine());
                for(int j=0; j < 4; j++) E[i][j] = Integer.parseInt(st.nextToken());

                if(!H.containsKey(E[i][0])) H.put(E[i][0], new ArrayList<>());
                H.get(E[i][0]).add(new int[]{i, 0});

                if(!V.containsKey(E[i][1])) V.put(E[i][1], new ArrayList<>());
                V.get(E[i][1]).add(new int[]{i, 0});

                if(!H.containsKey(E[i][2])) H.put(E[i][2], new ArrayList<>());
                H.get(E[i][2]).add(new int[]{i, 1});

                if(!V.containsKey(E[i][3])) V.put(E[i][3], new ArrayList<>());
                V.get(E[i][3]).add(new int[]{i, 1});

            }
            
        } catch(Exception e) {
            System.out.println(e);
        }

        Map<Integer, Integer> HH = new HashMap<>(), VV = new HashMap<>();
        int cnt = 0;
        for(int key: H.keySet()) HH.put(key, cnt++);
        cnt = 0;
        for(int key: V.keySet()) VV.put(key, cnt++);


        int h = H.size(), v = V.size();
        Map<Integer, int[]> map = new HashMap<>();
        for(int[] e: E) map.put(HH.get(e[2]) * v + VV.get(e[3]), new int[]{HH.get(e[0]), VV.get(e[1]), e[2] + e[3] - e[0] - e[1]});

        int[][] dp = new int[h][v];

        for(int i=0; i < h; i++) for(int j=0; j < v; j++) {

            if(i > 0) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
            if(j > 0) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);

            if(map.containsKey(i * v + j)) {
                int[] e = map.get(i * v + j);
                dp[i][j] = Math.max(dp[i][j], dp[e[0]][e[1]] + e[2]);
            }

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("boards.out")))) {
            pw.println(n + n - dp[h - 1][v - 1]);
        } catch(Exception e) {
            System.out.println(e);
        }

    }

}
