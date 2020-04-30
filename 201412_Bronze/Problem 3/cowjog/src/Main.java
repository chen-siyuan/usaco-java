import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int[] cows;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cowjog.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());

        cows = new int[numCows];

        for(int i=0; i < numCows; i++) {

            st = new StringTokenizer(br.readLine());
            st.nextToken();
            cows[numCows - 1 - i] = Integer.parseInt(st.nextToken());

        }

        br.close();

        int min = Integer.MAX_VALUE;
        int count = 0;

        for(int i=0; i < numCows; i++) if(cows[i] <= min) {
            min = cows[i];
            count++;
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjog.out")));

        pw.println(count);

        pw.close();

    }

}
