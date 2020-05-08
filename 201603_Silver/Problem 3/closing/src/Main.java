import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static int numPaths;
    public static ArrayList<Integer>[] paths;
    public static boolean[] searched;
    public static boolean[] removed;

    public static boolean connected() {

        int count = 0;
        searched = new boolean[numPoints];

        for(int i=0; i < numPoints; i++) if(!removed[i] && !searched[i]) {
            count++;
            search(i);
        }

        return count == 1;
    }

    public static void search(int index) {

        if(searched[index]) return;
        searched[index] = true;

        for(int neighbor: paths[index]) search(neighbor);

    }

    public static void remove(int index) {

        removed[index] = true;

        for(int i=0; i < numPoints; i++) {
            int pointer = 0;
            while(pointer < paths[i].size()) if(paths[i].get(pointer++) == index) paths[i].remove(--pointer);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("closing.in"));

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

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));

        removed = new boolean[numPoints];

        for(int i=0; i < numPoints; i++) {
            pw.println(connected() ? "YES" : "NO");
            remove(Integer.parseInt(br.readLine()) - 1);
        }

        br.close();

        pw.close();

    }

}
