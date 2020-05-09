import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {

    public static int dimension;
    public static int[][] map;
    public static boolean[][] searched;
    public static int[] thisCombination;
    public static ArrayList<int[]> answers;

    public static boolean meaningful() {

        for(int[] answer: answers)
            if(thisCombination[0] >= answer[0]
                    && thisCombination[1] <= answer[1]
                    && thisCombination[2] >= answer[2]
                    && thisCombination[3] <= answer[3]) return false;

        return true;
    }

    public static void search(int x, int y) {

        if(searched[x - thisCombination[0]][y - thisCombination[2]]) return;
        searched[x - thisCombination[0]][y - thisCombination[2]] = true;

        if(x > thisCombination[0] && map[x - 1][y] == map[x][y]) search(x - 1, y);
        if(x < thisCombination[1] - 1 && map[x + 1][y] == map[x][y]) search(x + 1, y);
        if(y > thisCombination[2] && map[x][y - 1] == map[x][y]) search(x, y - 1);
        if(y < thisCombination[3] - 1 && map[x][y + 1] == map[x][y]) search(x, y + 1);

    }

    public static boolean valid() {

        HashSet<Integer> colors = new HashSet<>();

        for(int i = thisCombination[0]; i < thisCombination[1]; i++) for(int j = thisCombination[2]; j < thisCombination[3]; j++) colors.add(map[i][j]);

        if(colors.size() != 2) return false;

        searched = new boolean[thisCombination[1] - thisCombination[0]][thisCombination[3] - thisCombination[2]];

        ArrayList<Integer> mapping = new ArrayList<>(colors);

        int[] count = new int[2];

        for(int i = thisCombination[0]; i < thisCombination[1]; i++) for(int j = thisCombination[2]; j < thisCombination[3]; j++) if(!searched[i - thisCombination[0]][j - thisCombination[2]]) {
            count[mapping.indexOf(map[i][j])]++;
            search(i, j);
        }

        return Math.min(count[0], count[1]) == 1 && Math.max(count[0], count[1]) > 1;
    }

    public static int[][] generate() {

        int[][] result =  new int[(int)(Math.pow(dimension * (dimension + 1), 2) / 4)][4];

        int pointer = 0;

        for(int i=dimension; i > 0; i--) for(int j=0; j <= dimension - i; j++)
            for(int k=dimension; k > 0; k--) for(int l=0; l <= dimension - k; l++) {

                result[pointer][0] = j;
                result[pointer][1] = j + i;
                result[pointer][2] = l;
                result[pointer][3] = l + k;

                pointer++;

        }

        return result;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("where.in"));

        dimension = Integer.parseInt(br.readLine());

        map = new int[dimension][dimension];

        for(int i=0; i < dimension; i++) {
            String temp = br.readLine();
            for(int j=0; j < dimension; j++) map[i][j] = temp.charAt(j) - 'A';
        }

        br.close();

        answers = new ArrayList<>();

        int[][] combinations = generate();

        for(int[] combination: combinations) {

            thisCombination = combination;

            if (!meaningful()) continue;
            if (valid()) answers.add(new int[]{thisCombination[0], thisCombination[1], thisCombination[2], thisCombination[3]});

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("where.out")));

        pw.println(answers.size());

        pw.close();

    }

}
