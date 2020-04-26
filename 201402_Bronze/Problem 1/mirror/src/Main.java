import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numRows;
    public static int numCols;
    public static boolean[][] map;
    public static int count;
    public static int answer;

    public static void solve() {

        for(int i=0; i < numCols; i++) {

            count = 0;
            search(new int[]{0, i}, 0);
            answer = Math.max(answer, count);

        }

        for(int i=0; i < numRows; i++) {

            count = 0;
            search(new int[]{i, 0}, 1);
            answer = Math.max(answer, count);

        }

        for(int i=0; i < numCols; i++) {

            count = 0;
            search(new int[]{numRows - 1, i}, 2);
            answer = Math.max(answer, count);

        }

        for(int i=0; i < numRows; i++) {

            count = 0;
            search(new int[]{i, numCols - 1}, 3);
            answer = Math.max(answer, count);

        }

    }

    public static void search(int[] position, int direction) {

        if(position[0] < 0 || position[0] >= numRows
                || position[1] < 0 || position[1] >= numCols) return;

        count++;

        if(map[position[0]][position[1]]) {
            switch(direction) {
                case 0:
                    search(new int[]{position[0], position[1] + 1}, 1);
                    break;
                case 1:
                    search(new int[]{position[0] + 1, position[1]}, 0);
                    break;
                case 2:
                    search(new int[]{position[0], position[1] - 1}, 3);
                    break;
                case 3:
                    search(new int[]{position[0] - 1, position[1]}, 2);
                    break;
            }
        } else {
            switch(direction) {
                case 0:
                    search(new int[]{position[0], position[1] - 1}, 3);
                    break;
                case 1:
                    search(new int[]{position[0] - 1, position[1]}, 2);
                    break;
                case 2:
                    search(new int[]{position[0], position[1] + 1}, 1);
                    break;
                case 3:
                    search(new int[]{position[0] + 1, position[1]}, 0);
                    break;
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("mirror.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numRows = Integer.parseInt(st.nextToken());
        numCols = Integer.parseInt(st.nextToken());

        map = new boolean[numRows][numCols];

        for(int i=0; i < numRows; i++) {
            String temp = br.readLine();
            for(int j=0; j < numCols; j++) map[i][j] = '\\' == temp.charAt(j);
        }

        br.close();

        answer = Integer.MIN_VALUE;

        solve();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mirror.out")));

        pw.println(answer);

        pw.close();

    }

}
