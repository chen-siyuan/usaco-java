import java.io.*;
import java.util.HashMap;

public class Main {

    public static int numPoints;
    public static int[] points;
    public static int[] colonies;
    public static boolean[] searched;

    public static void search(int index) {

        if(searched[index]) return;
        searched[index] = true;

        colonies[points[index]] = colonies[index];

        search(points[index]);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("reorder.in"));

        numPoints = Integer.parseInt(br.readLine());

        int[] temp = new int[numPoints];
        points = new int[numPoints];

        for(int i=0; i < numPoints; i++) temp[Integer.parseInt(br.readLine()) - 1] = i;
        for(int i=0; i < numPoints; i++) points[i] = temp[Integer.parseInt(br.readLine()) - 1];

        br.close();

        colonies = new int[numPoints];
        for(int i=0; i < numPoints; i++) colonies[i] = i;
        searched = new boolean[numPoints];

        for(int i=0; i < numPoints; i++) search(i);

        HashMap<Integer, Integer> count = new HashMap<>();

        for(int i=0; i < numPoints; i++) {
            if(!count.containsKey(colonies[i])) count.put(colonies[i], 0);
            count.put(colonies[i], count.get(colonies[i]) + 1);
        }

        int numCycles = 0;
        int max = -1;

        for(int key: count.keySet()) if(count.get(key) > 1) {
            numCycles++;
            max = Math.max(max, count.get(key));
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reorder.out")));

        pw.println(String.format("%d %d", numCycles, max));

        pw.close();

    }

}
