import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int timeLimit;
    public static int[] cows;

    public static boolean attempt(int rows) {

        int[] presentCows = new int[rows];
        System.arraycopy(cows, 0, presentCows, 0, rows);

        Arrays.sort(presentCows);

        for(int i=rows; i < numCows; i++) {

            if(cows[i] + presentCows[0] <= timeLimit) {

                presentCows[0] += cows[i];
                int pointer = 0;
                while(pointer + 1 < rows && presentCows[pointer] > presentCows[pointer + 1]) {
                    int temp = presentCows[pointer];
                    presentCows[pointer] = presentCows[pointer + 1];
                    presentCows[pointer + 1] = temp;
                    pointer++;
                }

            } else {
                return false;
            }

        }

        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cowdance.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        timeLimit = Integer.parseInt(st.nextToken());

        cows = new int[numCows];

        for(int i=0; i < numCows; i++) cows[i] = Integer.parseInt(br.readLine());

        br.close();

        int count = 1;

        while(!attempt(count)) count++;

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));

        pw.println(count);

        pw.close();

    }

}
