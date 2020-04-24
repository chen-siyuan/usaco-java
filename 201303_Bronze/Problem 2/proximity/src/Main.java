import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

    public static int numCows;
    public static int maxDistance;
    public static TreeMap<Integer, TreeSet<Integer>> indexLists;

    public static int maxIndex() {

        while(indexLists.size() > 0) {

            int key = indexLists.lastKey();
            TreeSet<Integer> indices = indexLists.get(key);
            indexLists.remove(key);

            if(indices.size() == 1) continue;

            int previousIndex = indices.first();
            indices.remove(previousIndex);

            while(indices.size() > 0) {

                int thisIndex = indices.first();
                indices.remove(thisIndex);

                if(thisIndex - previousIndex <= maxDistance) return key;

                previousIndex = thisIndex;

            }

        }

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("proximity.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        maxDistance = Integer.parseInt(st.nextToken());
        indexLists = new TreeMap<>();

        for(int i=0; i < numCows; i++) {

            int temp = Integer.parseInt(br.readLine());

            if(!indexLists.containsKey(temp)) indexLists.put(temp, new TreeSet<>());
            indexLists.get(temp).add(i);

        }

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("proximity.out")));

        pw.println(maxIndex());

        pw.close();

    }

}
