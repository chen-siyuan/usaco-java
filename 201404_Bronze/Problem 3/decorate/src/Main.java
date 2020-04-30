import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static int numPaths;
    public static ArrayList<Integer>[] paths;
    public static boolean[] searched;
    public static int[] colonies;
    public static int numColonies;
    public static int[] count;
    public static boolean flag;
    public static boolean[] colors;

    public static void search(int index, int colony, boolean color) {

        if(searched[index]) return;
        searched[index] = true;

        colonies[index] = colony;
        colors[index] = color;

        count[color ? 1 : 0]++;

        for(int neighbor: paths[index]) {
            if(searched[neighbor] && colors[neighbor] == colors[index]) flag = true;
            search(neighbor, colony, !color);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("decorate.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());
        numPaths = Integer.parseInt(st.nextToken());

        paths = new ArrayList[numPoints];
        for(int i=0; i < numPoints; i++) paths[i] = new ArrayList<>();

        for(int i=0; i < numPaths; i++) {

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            paths[x].add(y);
            paths[y].add(x);

        }

        br.close();

        searched = new boolean[numPoints];
        colonies = new int[numPoints];
        numColonies = 0;
        colors = new boolean[numPoints];

        int answer = 0;

        for(int i=0; i < numPoints; i++) if(!searched[i]) {

            count = new int[2];
            flag = false;

            search(i, numColonies++, true);

            answer += Math.max(count[0], count[1]);

        }

        if(flag) answer = -1;

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("decorate.out")));

        pw.println(answer);

        pw.close();

    }

}
