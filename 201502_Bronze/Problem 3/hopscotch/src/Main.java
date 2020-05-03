import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numRows;
    public static int numCols;
    public static boolean[][] colors;
    public static int[][] ways;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("hopscotch.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numRows = Integer.parseInt(st.nextToken());
        numCols = Integer.parseInt(st.nextToken());

        colors = new boolean[numRows][numCols];

        for(int i=0; i < numRows; i++) {
            String temp = br.readLine();
            for(int j=0; j < numCols; j++) colors[i][j] = 'R' == temp.charAt(j);
        }

        br.close();

        ways = new int[numRows][numCols];
        ways[0][0] = 1;

        for(int i=1; i < numRows; i++) for(int j=1; j < numCols; j++)
            for(int k=0; k < i; k++) for(int l=0; l < j; l++)
                ways[i][j] += ((colors[i][j] == colors[k][l]) ? 0 : 1) * ways[k][l];

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));

        pw.println(ways[numRows - 1][numCols - 1]);

        pw.close();

    }

}
