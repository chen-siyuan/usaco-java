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
        
        FastReader fr = new FastReader("perimeter.in");

        int n = fr.nextInt(), maxArea = 0, minPeri = Integer.MAX_VALUE;
        boolean[][] map = new boolean[n][n], memo = new boolean[n][n];
        
        for(int i=0; i < n; i++) {
            String tmp = fr.nextLine();
            for(int j=0; j < n; j++) map[i][j] = tmp.charAt(j) == '#';
        }

        for(int i=0; i < n; i++) for(int j=0; j < n; j++) if(map[i][j] && !memo[i][j]) {
            
            int[] currArea = new int[]{0}, currPeri = new int[]{0};

            DFS(n, map, memo, currArea, currPeri, i, j);

            if(maxArea < currArea[0]) {
                maxArea = currArea[0];
                minPeri = currPeri[0];
            } else if(maxArea == currArea[0]) minPeri = Math.min(minPeri, currPeri[0]);

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")))) {
            pw.println(String.format("%d %d", maxArea, minPeri));
        } catch(Exception e) {
            System.out.println(e);
        }

    }

    public static void DFS(int n, boolean[][] map, boolean[][] memo, int[] currArea, int[] currPeri, int x, int y) {

        if(memo[x][y]) return;
        memo[x][y] = true;

        currArea[0]++;

        if(x != 0 && map[x - 1][y]) DFS(n, map, memo, currArea, currPeri, x - 1, y);
        else currPeri[0]++;
        if(x != n - 1 && map[x + 1][y]) DFS(n, map, memo, currArea, currPeri, x + 1, y);
        else currPeri[0]++;
        if(y != 0 && map[x][y - 1]) DFS(n, map, memo, currArea, currPeri, x, y - 1);
        else currPeri[0]++;
        if(y != n - 1 && map[x][y + 1]) DFS(n, map, memo, currArea, currPeri, x, y + 1);
        else currPeri[0]++;

    }
    
}
