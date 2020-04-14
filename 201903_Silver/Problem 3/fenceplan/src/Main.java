import java.io.*;
import java.util.*;

public class Main {

    public static int numPoints;
    public static int numSegments;
    public static int[][] coordinates;
    public static ArrayList<Integer>[] adjacencyList;
    public static boolean[] searched;
    public static int[] colonies;
    public static HashMap<Integer, int[]> boundaries;

    public static void search(int point) {

        if(searched[point]) return;
        searched[point] = true;

        for(int neighbor: adjacencyList[point]) {

            if(!searched[neighbor]) {
                colonies[neighbor] = colonies[point];
                search(neighbor);
            }

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());
        numSegments = Integer.parseInt(st.nextToken());
        coordinates = new int[numPoints][2];
        adjacencyList = new ArrayList[numPoints];
        searched = new boolean[numPoints];
        colonies = new int[numPoints];

        for(int i=0; i < numPoints; i++) {

            st = new StringTokenizer(br.readLine());

            coordinates[i][0] = Integer.parseInt(st.nextToken());
            coordinates[i][1] = Integer.parseInt(st.nextToken());

            adjacencyList[i] = new ArrayList<>();
            colonies[i] = i;

        }

        for(int i=0; i < numSegments; i++) {

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            adjacencyList[x].add(y);
            adjacencyList[y].add(x);

        }

        br.close();

        for(int i=0; i < numPoints; i++) search(i);

        boundaries = new HashMap<>();

        for(int i=0; i < numPoints; i++) {

            if(boundaries.containsKey(colonies[i])) {

                int[] temp = boundaries.get(colonies[i]);

                temp[0] = Math.min(temp[0], coordinates[i][0]);
                temp[1] = Math.max(temp[1], coordinates[i][0]);
                temp[2] = Math.min(temp[2], coordinates[i][1]);
                temp[3] = Math.max(temp[3], coordinates[i][1]);

            } else {
                boundaries.put(colonies[i], new int[]{coordinates[i][0],
                        coordinates[i][0],
                        coordinates[i][1],
                        coordinates[i][1]});
            }

        }

        int answer = Integer.MAX_VALUE;

        for(int key: boundaries.keySet()) {

            int[] temp = boundaries.get(key);

            answer = Math.min(answer, temp[1] + temp[3] - temp[0] - temp[2]);

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));

        pw.println(answer * 2);

        pw.close();

    }

}
