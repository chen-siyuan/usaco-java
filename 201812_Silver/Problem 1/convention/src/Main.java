import java.util.*;
import java.io.*;

public class Main {

    public static int numCows, numBuses, capacity;
    public static int[] cows;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("convention.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        numBuses = Integer.parseInt(st.nextToken());
        capacity = Integer.parseInt(st.nextToken());

        cows = new int[numCows];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i < numCows; i++) cows[i] = Integer.parseInt(st.nextToken());

        br.close();

        Arrays.sort(cows);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));

        pw.close();
    
    }


}
