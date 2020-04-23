import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numHaybales;
    public static boolean[][] occupied;
    public static int perimeter;
    public static boolean[][] searched;

    public static void search(int x, int y) {

        if(searched[x][y]) return;
        searched[x][y] = true;

        if(x > 0) {
            if(occupied[x - 1][y]) {
                perimeter++;
            } else {
                if(!searched[x - 1][y]) search(x - 1, y);
            }
        }

        if(y > 0) {
            if(occupied[x][y - 1]) {
                perimeter++;
            } else {
                if(!searched[x][y - 1]) search(x, y - 1);
            }
        }

        if(x < 101) {
            if(occupied[x + 1][y]) {
                perimeter++;
            } else {
                if(!searched[x + 1][y]) search(x + 1, y);
            }
        }

        if(y < 101) {
            if(occupied[x][y + 1]) {
                perimeter++;
            } else {
                if(!searched[x][y + 1]) search(x, y + 1);
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numHaybales = Integer.parseInt(st.nextToken());

        occupied = new boolean[102][102];
        searched = new boolean[102][102];

        for(int i=0; i < numHaybales; i++) {
            st = new StringTokenizer(br.readLine());
            occupied[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
        }

        br.close();

        search(0, 0);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("perimeter.out")));

        pw.println(perimeter);

        pw.close();

    }

}
