import java.io.*;
import java.util.*;

public class Main {

    public static final int[][] DIRS = new int[][]{new int[]{1, 0}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{0, -1}};

    public static int n, numColonies;
    public static int[][] colors, colonies;
    public static boolean[][] searchedSingle;
    public static int singleCurr, singleMax, doubleCurr, doubleMax;
    public static int[] weight, colonyColors;
    public static Set<Integer>[] al;
    public static Set<Set<Integer>> pairs;
    public static Set<Integer> pair;
    public static boolean[] searchedDouble;
    public static Map<Integer, Integer> record;

    public static void searchSingle(int x, int y, int color) {
    
        if(x < 0 || x >= n || y < 0 || y >= n || searchedSingle[x][y] || color != colors[x][y]) return;
        searchedSingle[x][y] = true;
        colonies[x][y] = numColonies;

        singleCurr++;
        singleMax = Math.max(singleMax, singleCurr);

        for(int[] dir: DIRS) searchSingle(x + dir[0], y + dir[1], colors[x][y]);

    }

    public static void searchDouble(int colony) {
    
        if(searchedDouble[colony]) return;
        searchedDouble[colony] = true;

        doubleCurr += weight[colony];
        doubleMax = Math.max(doubleMax, doubleCurr);

        for(int neighbor: al[colony]) if(pair.contains(colonyColors[neighbor])) searchDouble(neighbor);
    
    }

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));
    
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        colors = new int[n][n];

        pairs = new HashSet<>();
        record = new HashMap<>();

        for(int i=0; i < n; i++) {

            st = new StringTokenizer(br.readLine());

            for(int j=0; j < n; j++) {

                colors[i][j] = Integer.parseInt(st.nextToken());

                if(i != 0) pairs.add(new HashSet<>(Arrays.asList(colors[i - 1][j], colors[i][j])));
                if(j != 0) pairs.add(new HashSet<>(Arrays.asList(colors[i][j - 1], colors[i][j])));

                if(!record.containsKey(colors[i][j])) record.put(colors[i][j], 0);
                record.put(colors[i][j], record.get(colors[i][j]) + 1);

            }

        }

        br.close();

        searchedSingle = new boolean[n][n];
        colonies = new int[n][n];

        numColonies = 0;
        singleMax = Integer.MIN_VALUE;

        weight = new int[n * n];
        colonyColors = new int[n * n];

        for(int i=0; i < n; i++) for(int j=0; j < n; j++) if(!searchedSingle[i][j]) {

            singleCurr = 0;
            searchSingle(i, j, colors[i][j]);

            weight[numColonies] = singleCurr;
            colonyColors[numColonies] = colors[i][j];

            numColonies++;

        }

        al = new Set[numColonies];
        for(int i=0; i < numColonies; i++) al[i] = new HashSet<>();

        for(int i=0; i < n; i++) for(int j=0; j < n; j++) {

            if(i != n - 1) {
                al[colonies[i + 1][j]].add(colonies[i][j]);
                al[colonies[i][j]].add(colonies[i + 1][j]);
            }

            if(j != n - 1) {
                al[colonies[i][j + 1]].add(colonies[i][j]);
                al[colonies[i][j]].add(colonies[i][j + 1]);
            }

        }

        for(int i=0; i < numColonies; i++) al[i].remove(i);

        doubleMax = singleMax;

        for(Set<Integer> temp: pairs) {
            
            if(temp.size() == 1) continue;

            int sum = 0;

            for(int color: temp) sum += record.get(color);

            if(sum <= doubleMax) continue;
        
            pair = temp;

            searchedDouble = new boolean[numColonies];

            for(int i=0; i < numColonies; i++) if(pair.contains(colonyColors[i]) && !searchedDouble[i]) {
                doubleCurr = 0;
                searchDouble(i);
            }
        
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));

        pw.println(singleMax);
        pw.println(doubleMax);

        pw.close();

    }

}
