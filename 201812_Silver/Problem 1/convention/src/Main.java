import java.util.*;
import java.io.*;

public class Main {

    public static int n, numBuses, capacity;
    public static int[] times;

    public static boolean check(int waitingTime) {

        int currBuses = 0;
        int currCapacity = 0;
        int currTime = times[0];
        int currCow = 0;

        while(currCow < n) {

            if(currCapacity == capacity || times[currCow] - currTime > waitingTime) {

                currBuses++;
                currCapacity = 0;

                currTime = times[currCow];

            }

            if(currBuses == numBuses) return false;

            currCapacity++;
            currCow++;

        }

        return true;
    }

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("convention.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        numBuses = Integer.parseInt(st.nextToken());
        capacity = Integer.parseInt(st.nextToken());

        times = new int[n];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i < n; i++) times[i] = Integer.parseInt(st.nextToken());

        br.close();

        Arrays.sort(times);

        int head = 0;
        int tail = times[n - 1] - times[0];

        while(head + 1 < tail) {

            int mid = (head + tail) / 2;

            if(check(mid)) {
                tail = mid;
            } else {
                head = mid;
            }
        
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));

        pw.println(head + 1);

        pw.close();
    
    }


}
