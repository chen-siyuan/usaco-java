import java.util.*;
import java.io.*;

public class Main {

    public static int numCows, numBuses, capacity;
    public static int[] cows;
    public static int globalMin;
    public static int localMax;

    public static void DFS(int bus, int cow) {
    
        if((numBuses - bus) * capacity < numCows - cow) return;
        if((numBuses - bus) > numCows - cow) return;
        if(numBuses == bus) {
            globalMin = Math.min(globalMin, localMax);
            return;
        }

        int temp = localMax;

        for(int i=capacity; i > 1; i--) if(cow + capacity <= numCows) {
            localMax = Math.max(temp, cows[cow + capacity - 1] - cows[cow]);
            DFS(bus + 1, cow + capacity);
        }

        localMax = temp;
    
    }

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

        globalMin = Integer.MAX_VALUE;
        localMax = Integer.MIN_VALUE;

        DFS(0, 0);

        System.out.println(globalMin);
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));

        pw.close();
    
    }


}
