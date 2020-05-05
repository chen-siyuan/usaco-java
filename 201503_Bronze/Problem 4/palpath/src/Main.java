import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class Main {

    public static int dimension;
    public static int[][] map;
    public static HashSet<Long> answers;
    public static int[] candidate;

    public static long convert() {

        long result = 0;

        for(int i=0; i < dimension; i++) result = (result + candidate[i]) * 26;

        return result;
    }

    public static void solve(int x, int y) {

        if(x + y < dimension - 1) {

            candidate[x + y + 1] = map[x + 1][y];
            if(x + y < dimension - 2 || !answers.contains(convert())) solve(x + 1, y);

            candidate[x + y + 1] = map[x][y + 1];
            if(x + y < dimension - 2 || !answers.contains(convert())) solve(x, y + 1);

        } else {

            if(x + y == dimension * 2 - 2) {
                answers.add(convert());
            } else {

                if(x + 1 < dimension) {
                    candidate[x + y + 1] = map[x + 1][y];
                    if(candidate[x + y + 1] == candidate[dimension * 2 - x - y - 3]) solve(x + 1, y);
                }

                if(y + 1 < dimension) {
                    candidate[x + y + 1] = map[x][y + 1];
                    if(candidate[x + y + 1] == candidate[dimension * 2 - x - y - 3]) solve(x, y + 1);
                }

            }

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("palpath.in"));

        dimension = Integer.parseInt(br.readLine());

        map = new int[dimension][dimension];

        for(int i=0; i < dimension; i++) {
            String temp = br.readLine();
            for(int j=0; j < dimension; j++) map[i][j] = temp.charAt(j) - 'A';
        }

        br.close();

        answers = new HashSet<>();

        candidate = new int[dimension * 2 - 1];
        candidate[0] = map[0][0];

        solve(0, 0);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));

        pw.println(answers.size());

        pw.close();

    }

}
