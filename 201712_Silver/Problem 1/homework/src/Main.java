import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numScores;
    public static int[] scores;
    public static int[] minScores;
    public static int sum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("homework.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numScores = Integer.parseInt(st.nextToken());

        scores = new int[numScores];
        sum = 0;

        st = new StringTokenizer(br.readLine());

        for(int i=0; i < numScores; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
            sum += scores[i];
        }

        br.close();

        minScores = new int[numScores];

        for(int i=numScores - 1; i >= 0; i--) minScores[i] = i == numScores - 1 ? scores[i] : Math.min(scores[i], minScores[i + 1]);

        double maxScore = -1;
        ArrayList<Integer> maxIndices = new ArrayList<>();

        for(int i=0; i < numScores - 2; i++) {

            sum -= scores[i];

            double thisScore = (sum - minScores[i + 1]) / (numScores - i - 2.);

            if(maxScore < thisScore) {
                maxScore = thisScore;
                maxIndices.clear();
            }

            if(maxScore == thisScore) maxIndices.add(i + 1);

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));

        for(int index: maxIndices) pw.println(index);

        pw.close();

    }

}
