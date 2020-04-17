import java.io.*;
import java.util.*;

public class Main {

    public static int dimension;
    public static int numCows;
    public static int numFences;
    public static ArrayList<Integer>[][] adjacencyList;
    public static int[][] cows;
    public static int[][] colonies;
    public static boolean[][] searched;

    public static void addFence(int x1, int y1, int x2, int y2) {

        if(x1 == x2) {

            if(y1 > y2) {
                adjacencyList[x1][y1].remove(Integer.valueOf(3));
                adjacencyList[x2][y2].remove(Integer.valueOf(1));
            } else {
                adjacencyList[x1][y1].remove(Integer.valueOf(1));
                adjacencyList[x2][y2].remove(Integer.valueOf(3));
            }

        } else {

            if(x1 > x2) {
                adjacencyList[x1][y1].remove(Integer.valueOf(2));
                adjacencyList[x2][y2].remove(Integer.valueOf(0));
            } else {
                adjacencyList[x1][y1].remove(Integer.valueOf(0));
                adjacencyList[x2][y2].remove(Integer.valueOf(2));
            }

        }

    }

    public static void search(int x, int y) {

        if(searched[x][y]) return;
        searched[x][y] = true;

        for(int direction: adjacencyList[x][y]) {

            switch(direction) {
                case 0:
                    if(!searched[x + 1][y]) {
                        colonies[x + 1][y] = colonies[x][y];
                        search(x + 1, y);
                    }
                    break;
                case 1:
                    if(!searched[x][y + 1]) {
                        colonies[x][y + 1] = colonies[x][y];
                        search(x, y + 1);
                    }
                    break;
                case 2:
                    if(!searched[x - 1][y]) {
                        colonies[x - 1][y] = colonies[x][y];
                        search(x - 1, y);
                    }
                    break;
                case 3:
                    if(!searched[x][y - 1]) {
                        colonies[x][y - 1] = colonies[x][y];
                        search(x, y - 1);
                    }
                    break;
            }

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("countcross.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        dimension = Integer.parseInt(st.nextToken());
        numCows = Integer.parseInt(st.nextToken());
        numFences = Integer.parseInt(st.nextToken());

        adjacencyList = new ArrayList[dimension][dimension];

        for(int i=0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) {

                adjacencyList[i][j] = new ArrayList<>();
                if(i != 0) adjacencyList[i][j].add(2);
                if(j != 0) adjacencyList[i][j].add(3);
                if(i != dimension - 1) adjacencyList[i][j].add(0);
                if(j != dimension - 1) adjacencyList[i][j].add(1);

            }
        }

        for(int i=0; i < numFences; i++) {

            st = new StringTokenizer(br.readLine());

            addFence(Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1);

        }

        cows = new int[numCows][2];

        for(int i=0; i < numCows; i++) {

            st = new StringTokenizer(br.readLine());
            cows[i] = new int[]{Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1};

        }

        br.close();

        colonies = new int[dimension][dimension];
        searched = new boolean[dimension][dimension];

        for(int i=0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) colonies[i][j] = i + j * dimension;
        }


        for(int i=0; i < dimension; i++) {
            for(int j=0; j < dimension; j++) search(i, j);
        }

        HashMap<Integer, Integer> groups = new HashMap<>();

        for(int[] cow: cows) {

            if(groups.containsKey(colonies[cow[0]][cow[1]])) {
                groups.replace(colonies[cow[0]][cow[1]], groups.get(colonies[cow[0]][cow[1]]) + 1);
            } else {
                groups.put(colonies[cow[0]][cow[1]], 1);
            }

        }

        int total = 0;

        for(int i: groups.keySet()) {
            for(int j: groups.keySet()) if(i != j) total += groups.get(i) * groups.get(j);
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));

        pw.println(total / 2);

        pw.close();

    }

}
