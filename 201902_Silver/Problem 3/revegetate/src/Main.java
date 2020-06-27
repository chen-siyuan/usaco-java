import java.io.*;
import java.util.*;

public class Main {

    public static int n, k, res;
    public static int[] parent;
    public static Set<List<Integer>> edges;
    public static Map<Integer, Integer> record;
    public static Set<Integer>[] al;
    public static int[] colors;
    public static boolean flag;

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

        record = new HashMap<>();

        for(int i=0; i < n; i++) {
            parent[i] = find(i);
            if(!record.containsKey(parent[i])) record.put(parent[i], record.size());
        }

        al = new HashSet[record.size()];
        for(int i=0; i < record.size(); i++) al[i] = new HashSet<>();

        for(List<Integer> edge: edges) {
            al[record.get(parent[edge.get(0)])].add(record.get(parent[edge.get(1)]));
            al[record.get(parent[edge.get(1)])].add(record.get(parent[edge.get(0)]));
        }

        flag = false;
        colors = new int[record.size()];
        res = 0;

        for(int i=0; i < record.size(); i++) if(colors[i] == 0) {
            res++;
            search(i, 1);
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("revegetate.out")));

        if(flag) {
            pw.println(0);
        } else {

            pw.print('1');
            for(int i=0; i < res; i++) pw.print('0');
            pw.println();

        }

        pw.close();
    
    }

    public static int find(int val) {
        return parent[val] == val ? val : find(parent[val]);
    }

    public static void union(int val1, int val2) {
        parent[find(val2)] = find(val1);
    }

    public static void search(int colony, int color) {

        if(flag) return;
        if(colors[colony] == 0) {
            colors[colony] = color;
        } else {
            if(colors[colony] != color) flag = true;
            return;
        }

        for(int neighbor: al[colony]) search(neighbor, -color);
    
    }

}
