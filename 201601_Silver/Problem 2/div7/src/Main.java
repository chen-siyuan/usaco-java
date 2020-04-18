import java.io.*;
import java.util.Arrays;

public class Main {

    public static int numCows;
    public static int[] cows;
    public static int[] min;
    public static int[] max;

    public static int solve() {

        int answer = 0;
        for(int i=0; i < 7; i++) if(min[i] != -1 && max[i] != -1) answer = Math.max(answer, max[i] - min[i]);

        return answer;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("div7.in"));

        numCows = Integer.parseInt(br.readLine());

        cows = new int[numCows];
        min = new int[7];
        max = new int[7];

        for(int i=0; i < 7; i++) {
            min[i] = -1;
            max[i] = -1;
        }

        for(int i=0; i < numCows; i++) {

            cows[i] = (Integer.parseInt(br.readLine()) + (i == 0 ? 0 : cows[i - 1])) % 7;

            if(min[cows[i]] == -1) min[cows[i]] = i;
            max[cows[i]] = i;

        }

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));

        pw.println(solve());

        pw.close();

    }

}
