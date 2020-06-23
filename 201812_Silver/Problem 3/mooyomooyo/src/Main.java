import java.util.*;
import java.io.*;

public class Main {

    public static final int[][] DIRS = new int[][]{new int[]{1, 0}, new int[]{0, 1}, new int[]{-1, 0}, new int[]{0, -1}};

    public static int n, k;
    public static int[][] map;
    public static boolean[][] searched;
    public static int curr;
    public static Set<Integer>[] record, cache;
    
    public static void search(int x, int y) {
        
        if(searched[x][y]) return;
        searched[x][y] = true;

        curr++;
        cache[x].add(y);

        for(int[] dir: DIRS) if(x + dir[0] >= 0 && x + dir[0] < 10 && y + dir[1] >= 0 && y + dir[1] < n
                && map[x][y] == map[x + dir[0]][y + dir[1]]) search(x + dir[0], y + dir[1]);

    }

    public static boolean check() {

        boolean flag = false;

        searched = new boolean[10][n];
        record = new HashSet[10];
        for(int i=0; i < 10; i++) record[i] = new HashSet<>();

        for(int i=0; i < 10; i++) for(int j=0; j < n; j++) if(map[i][j] != 0 && !searched[i][j]) {

            curr = 0;
            cache = new HashSet[10];
            for(int k=0; k < 10; k++) cache[k] = new HashSet<>();

            search(i, j);
            if(curr >= k) {
                for(int k=0; k < 10; k++) record[k].addAll(cache[k]);
                flag = true;
            }

        }

        return flag;
    }

    public static int[][] step() {

        int[][] temp = new int[10][n];

        for(int i=0; i < 10; i++) {

            int p1 = 0;
            int p2 = 0;

            while(p1 < n && map[i][p1] != 0) {
                if(record[i].contains(p1++)) continue;
                temp[i][p2++] = map[i][p1 - 1];
            }
        
        }

        return temp;
    }

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
    
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[10][n];

        for(int i=n-1; i >= 0; i--) {
            String temp = br.readLine();
            for(int j=0; j < 10; j++) map[j][i] = temp.charAt(j) - '0';
        }

        br.close();

        while(check()) map = step();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mooyomooyo.out")));

        for(int i=n-1; i >= 0; i--) {
            for(int j=0; j < 10; j++) pw.print(map[j][i]);
            pw.println();
        }

        pw.close();
    
    }

}
