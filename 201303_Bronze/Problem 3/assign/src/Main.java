import java.io.*;
import java.util.*;

public class Main {

    public static int numPoints;
    public static int numEdges;
    public static ArrayList<Integer>[] adjacencyList;
    public static int[] colonies;
    public static boolean[] searched;
    public static ArrayList<int[]> dEdges;
    public static HashMap<Integer, Integer> condensedColonies;
    public static int[] coloring;
    public static int numColonies;
    public static HashSet<Edge> condensedDEdges;

    public static boolean validate() {

        for(Edge edge: condensedDEdges)
            if(coloring[condensedColonies.get(edge.getA())]
                    == coloring[condensedColonies.get(edge.getB())])
                return false;

        return true;
    }

    public static int generate() {

        int count = 0;
        coloring = new int[numColonies];

        for(int i=0; i < Math.pow(3, numColonies - 1); i++) {

            if(validate()) count++;

            coloring[1] += 1;

            int pointer = 1;
            while(pointer + 1 < numColonies && coloring[pointer] == 3) {
                coloring[pointer] = 0;
                coloring[++pointer]++;
            }

        }

        return count * 3;
    }

    public static void search(int index) {

        if(searched[index]) return;
        searched[index] = true;

        for(int neighbor: adjacencyList[index]) if(!searched[neighbor]) {
            colonies[neighbor] = colonies[index];
            search(neighbor);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("assign.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());
        numEdges = Integer.parseInt(st.nextToken());

        adjacencyList = new ArrayList[numPoints];
        for(int i=0; i < numPoints; i++) adjacencyList[i] = new ArrayList<>();
        dEdges = new ArrayList<>();

        for(int i=0; i < numEdges; i++) {

            st = new StringTokenizer(br.readLine());

            if("S".equals(st.nextToken())) {

                int x = Integer.parseInt(st.nextToken()) - 1;
                int y = Integer.parseInt(st.nextToken()) - 1;

                adjacencyList[x].add(y);
                adjacencyList[y].add(x);

            } else {
                dEdges.add(new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1});
            }

        }

        br.close();

        colonies = new int[numPoints];
        for(int i=0; i < numPoints; i++) colonies[i] = i;
        searched = new boolean[numPoints];

        for(int i=0; i < numPoints; i++) search(i);

        // Condense colonies

        TreeSet<Integer> temp = new TreeSet<>();

        condensedColonies = new HashMap<>();

        for(int colony: colonies) temp.add(colony);

        numColonies = 0;

        while(temp.size() > 0) {
            condensedColonies.put(temp.first(), numColonies++);
            temp.remove(temp.first());
        }

        // Condense opposing edges

        condensedDEdges = new HashSet<>();

        for(int[] edge: dEdges) condensedDEdges.add(new Edge(colonies[edge[0]], colonies[edge[1]]));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("assign.out")));

        pw.println(generate());

        pw.close();

    }

}

class Edge {

    private final int a;
    private final int b;

    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge other = (Edge) o;
        return (a == other.a && b == other.b) || (a == other.b && b == other.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

}
