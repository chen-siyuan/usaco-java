import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static boolean check(char first, char second, char third) {
        return first != second && second == third;
    }

    public static int numRows;
    public static int numCols;
    public static char[][] map;
    public static int[][] count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("moocrypt.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numRows = Integer.parseInt(st.nextToken());
        numCols = Integer.parseInt(st.nextToken());

        map = new char[numRows][numCols];

        for(int i=0; i < numRows; i++) {
            String temp = br.readLine();
            for(int j=0; j < numCols; j++) map[i][j] = temp.charAt(j);
        }

        br.close();

        count = new int[26][26];

        for(int i=0; i < numRows; i++) for(int j=0; j < numCols - 2; j++)
            if(check(map[i][j], map[i][j + 1], map[i][j + 2]))
                count[map[i][j] - 'A'][map[i][j + 1] - 'A']++;

        for(int i=0; i < numRows; i++) for(int j=2; j < numCols; j++)
            if(check(map[i][j], map[i][j - 1], map[i][j - 2]))
                count[map[i][j] - 'A'][map[i][j - 1] - 'A']++;

        for(int i=0; i < numRows - 2; i++) for(int j=0; j < numCols; j++)
            if(check(map[i][j], map[i + 1][j], map[i + 2][j]))
                count[map[i][j] - 'A'][map[i + 1][j] - 'A']++;

        for(int i=2; i < numRows; i++) for(int j=0; j < numCols; j++)
            if(check(map[i][j], map[i - 1][j], map[i - 2][j]))
                count[map[i][j] - 'A'][map[i - 1][j] - 'A']++;

        for(int i=0; i < numRows - 2; i++) for(int j=0; j < numCols - 2; j++)
            if(check(map[i][j], map[i + 1][j + 1], map[i + 2][j + 2]))
                count[map[i][j] - 'A'][map[i + 1][j + 1] - 'A']++;

        for(int i=2; i < numRows; i++) for(int j=2; j < numCols; j++)
            if(check(map[i][j], map[i - 1][j - 1], map[i - 2][j - 2]))
                count[map[i][j] - 'A'][map[i - 1][j - 1] - 'A']++;

        for(int i=numRows - 1; i >= 2; i--) for(int j=0; j < numCols - 2; j++)
            if(check(map[i][j], map[i - 1][j + 1], map[i - 2][j + 2]))
                count[map[i][j] - 'A'][map[i - 1][j + 1] - 'A']++;

        for(int i=0; i < numRows - 2; i++) for(int j=numCols - 1; j >= 2; j--)
            if(check(map[i][j], map[i + 1][j - 1], map[i + 2][j - 2]))
                count[map[i][j] - 'A'][map[i + 1][j - 1] - 'A']++;

        int max = Integer.MIN_VALUE;

        for(int i=0; i < 26; i++) for(int j=0; j < 26; j++)
            if(i != 'M' - 'A' && j != 'O' - 'A')
                max = Math.max(max, count[i][j]);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocrypt.out")));

        pw.println(max);

        pw.close();

    }

}
