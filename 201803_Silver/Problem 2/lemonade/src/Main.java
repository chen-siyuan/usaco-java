import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int[] cows;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("lemonade.in"));

        numCows = Integer.parseInt(br.readLine());

        cows = new int[numCows];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i < numCows; i++) cows[i] = Integer.parseInt(st.nextToken());

        br.close();

        Arrays.sort(cows);

        int count = 0;

        while(count < numCows && cows[numCows - count - 1] >= count) count++;

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lemonade.out")));

        pw.println(count);

        pw.close();

    }

}
