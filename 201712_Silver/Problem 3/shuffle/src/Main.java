import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int[] mapping;
    public static boolean[] searched;
    public static HashSet<Integer>[] adjacencyList;
    public static ArrayList<Integer> colonies;

    public static int sizeCycle(int index) {

        HashMap<Integer, Integer> record = new HashMap<>();

        int pointer = index;
        int count = 0;

        while(true) {

            if(record.containsKey(pointer)) return count - record.get(pointer);

            record.put(pointer, count++);
            pointer = mapping[pointer];

        }

    }

    public static void search(int index) {

        if(searched[index]) return;
        searched[index] = true;

        for(int neighbor: adjacencyList[index]) search(neighbor);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("shuffle.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());

        mapping = new int[numCows];
        adjacencyList = new HashSet[numCows];
        for(int i=0; i < numCows; i++) adjacencyList[i] = new HashSet<>();

        st = new StringTokenizer(br.readLine());

        for(int i=0; i < numCows; i++) {

            mapping[i] = Integer.parseInt(st.nextToken()) - 1;

            adjacencyList[i].add(mapping[i]);
            adjacencyList[mapping[i]].add(i);

        }

        br.close();

        searched = new boolean[numCows];
        colonies = new ArrayList<>();

        for(int i=0; i < numCows; i++) if(!searched[i]) {
            colonies.add(i);
            search(i);
        }

        int sum = 0;

        for(int colony: colonies) sum += sizeCycle(colony);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));

        pw.println(sum);

        pw.close();

    }

}
