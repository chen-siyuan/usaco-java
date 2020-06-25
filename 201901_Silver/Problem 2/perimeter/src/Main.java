import java.io.*;
import java.util.*;

public class Main {

    public static int n, currA, currP, maxA, minP;
    public static boolean[][] map, searched;

    public static void search(int x, int y) {
    
        if(x < 0 || x >= n || y < 0 || y >= n || !map[x][y]) {
            currP++;
            return; 
        }
        if(searched[x][y]) return;
        searched[x][y] = true;

        currA++;
        
        search(x + 1, y);
        search(x, y + 1);
        search(x - 1, y);
        search(x, y - 1);
    
    }

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new boolean[n][n];

        for(int i=0; i < n; i++) {
            String raw = br.readLine();
            for(int j=0; j < n; j++) map[i][j] = raw.charAt(j) == '#';
        }

        br.close();

        searched = new boolean[n][n];
        maxA = Integer.MIN_VALUE;
        minP = Integer.MAX_VALUE;

        for(int i=0; i < n; i++) for(int j=0; j < n; j++) if(map[i][j] && !searched[i][j]) {

            currA = 0;
            currP = 0;

            search(i, j);

            if(currA > maxA || (currA == maxA && currP < minP)) {
                maxA = currA;
                minP = currP;
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));

        pw.println(String.format("%d %d", maxA, minP));

        pw.close();

    }

}
