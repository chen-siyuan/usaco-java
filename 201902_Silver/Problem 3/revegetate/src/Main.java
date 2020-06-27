import java.io.*;
import java.util.*;

public class Main {

    public static int n, k;
    public static int[] parent;
    public static Set<List<Integer>> edges;
    public static boolean flag;
    public static List<Integer>[] al;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("revegetate.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for(int i=0; i < n; i++) parent[i] = i;
        edges = new HashSet<>();

        for(int i=0; i < k; i++) {
        
            st = new StringTokenizer(br.readLine());

            if("S".equals(st.nextToken())) {
                union(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            } else {
                edges.add(Arrays.asList(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
            }
        
        }

        br.close();

        for(int i=0; i < n; i++) parent[i] = find(i);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));

        pw.close();
    
    }

    public static int find(int val) {
        return parent[val] == val ? val : find(parent[val]);
    }

    public static void union(int val1, int val2) {
        parent[find(val2)] = find(val1);
    }

}
