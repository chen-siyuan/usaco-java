import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numRows;
    public static int numCols;
    public static boolean[][] map;

    public static boolean check(int x, int y) {
        return (!map[x][y])
                && (((x == 0 || map[x - 1][y]) && (x < numRows - 2) && (!map[x + 1][y]) && (!map[x + 2][y]))
                || ((y == 0 || map[x][y - 1]) && (y < numCols - 2) && (!map[x][y + 1]) && (!map[x][y + 2])));
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("crosswords.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numRows = Integer.parseInt(st.nextToken());
        numCols = Integer.parseInt(st.nextToken());

        map = new boolean[numRows][numCols];

        for(int i=0; i < numRows; i++) {
            String temp = br.readLine();
            for(int j=0; j < numCols; j++) map[i][j] = '#' == temp.charAt(j);
        }

        br.close();

        int count = 0;
        StringBuilder ans = new StringBuilder();

        for(int i=0; i < numRows; i++) for(int j=0; j < numCols; j++) if(check(i, j)) {
            count++;
            ans.append(String.format("%d %d\n", i + 1, j + 1));
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crosswords.out")));

        pw.println(count);
        pw.print(ans.toString());

        pw.close();

    }

}
