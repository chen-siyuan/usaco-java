import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static int numCows;
    public static boolean[] isOrigin;
    public static ArrayList<Integer>[] adjacencyList;
    public static boolean[] searched;

    public static void search(int index) {

        if(searched[index]) return;
        searched[index] = true;

        for(int neighbor: adjacencyList[index]) if(!searched[neighbor]) search(neighbor);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("relay.in"));

        numCows = Integer.parseInt(br.readLine());

        isOrigin = new boolean[numCows];
        searched = new boolean[numCows];
        adjacencyList = new ArrayList[numCows];
        for(int i=0; i < numCows; i++) adjacencyList[i] = new ArrayList<>();

        for(int i=0; i < numCows; i++) {

            int temp = Integer.parseInt(br.readLine()) - 1;

            if(temp == -1) {
                isOrigin[i] = true;
            } else {
                adjacencyList[temp].add(i);
            }

        }

        br.close();

        for(int i=0; i < numCows; i++) if(isOrigin[i]) search(i);

        int count = 0;

        for(int i=0; i < numCows; i++) if(searched[i]) count++;

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("relay.out")));

        pw.println(count);

        pw.close();

    }

}
