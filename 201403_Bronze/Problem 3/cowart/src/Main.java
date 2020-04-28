import java.io.*;
import java.util.Enumeration;
import java.util.HashSet;

public class Main {

    public static int length;
    public static int[][] drawing;
    public static boolean colorBlind;
    public static HashSet<Integer> count;
    public static boolean[][] searched;
    public static int[][] colonies;

    public static boolean sameColor(int x, int y) {

        if(colorBlind) {
            return Math.abs(x) == Math.abs(y);
        } else {
            return x == y;
        }

    }

    public static void search(int x, int y) {

        if(searched[x][y]) return;
        searched[x][y] = true;

        if(x > 0 && sameColor(drawing[x][y], drawing[x - 1][y])) {
            colonies[x - 1][y] = colonies[x][y];
            search(x - 1, y);
        }

        if(y > 0 && sameColor(drawing[x][y], drawing[x][y - 1])) {
            colonies[x][y - 1] = colonies[x][y];
            search(x, y - 1);
        }

        if(x < length - 1 && sameColor(drawing[x][y], drawing[x + 1][y])) {
            colonies[x + 1][y] = colonies[x][y];
            search(x + 1, y);
        }

        if(y < length - 1 && sameColor(drawing[x][y], drawing[x][y + 1])) {
            colonies[x][y + 1] = colonies[x][y];
            search(x, y + 1);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cowart.in"));

        length = Integer.parseInt(br.readLine());

        drawing = new int[length][length];

        for(int i=0; i < length; i++) {

            String temp = br.readLine();

            for(int j=0; j < length; j++) switch(temp.charAt(j)) {
                case 'R':
                    drawing[i][j] = -1;
                    break;
                case 'G':
                    drawing[i][j] = 1;
                    break;
                case 'B':
                    drawing[i][j] = 0;
                    break;
            }

        }

        br.close();

        colorBlind = false;
        count = new HashSet<>();
        searched = new boolean[length][length];
        colonies = new int[length][length];
        for(int i=0; i < length; i++) for(int j=0; j < length; j++) colonies[i][j] = i * length + j;

        for(int i=0; i < length; i++) for(int j=0; j < length; j++) search(i, j);

        for(int i=0; i < length; i++) for(int j=0; j < length; j++) count.add(colonies[i][j]);

        int numColoniesHuman = count.size();

        colorBlind = true;
        count = new HashSet<>();
        searched = new boolean[length][length];
        colonies = new int[length][length];
        for(int i=0; i < length; i++) for(int j=0; j < length; j++) colonies[i][j] = i * length + j;

        for(int i=0; i < length; i++) for(int j=0; j < length; j++) search(i, j);

        for(int i=0; i < length; i++) for(int j=0; j < length; j++) count.add(colonies[i][j]);

        int numColoniesCow = count.size();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowart.out")));

        pw.println(String.format("%d %d", numColoniesHuman, numColoniesCow));

        pw.close();

    }

}
