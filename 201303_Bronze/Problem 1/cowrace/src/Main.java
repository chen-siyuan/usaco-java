import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static int[] numSegments;
    public static int[][][] segments;
    public static TreeSet<Integer> points;
    public static int numPoints;

    public static int countChanges(int[] first, int[] second) {

        int count = 0;

        boolean flag = true;
        boolean leader = false;

        for(int i=0; i < numPoints; i++) {

            if(first[i] == second[i]) continue;

            if(flag) {
                leader = first[i] > second[i];
                flag = false;
            } else {
                if(leader != (first[i] > second[i])) count++;
                leader = first[i] > second[i];
            }

        }

        return count;
    }

    public static int[] distances(int index) {

        int pointer = 0;
        int timeElapsed = 0;

        int[] distances = new int[numPoints];
        distances[0] = 0;

        TreeSet<Integer> temp = (TreeSet<Integer>) points.clone();

        int previousTime = temp.first();
        int distance = 0;

        for(int i=1; i < numPoints; i++) {

            temp.remove(previousTime);
            int timeIncrement = temp.first() - previousTime;
            previousTime = temp.first();

            distance += segments[index][pointer][0] * timeIncrement;

            if(timeIncrement + timeElapsed == segments[index][pointer][1]) {
                pointer++;
                timeElapsed = 0;
            } else {
                timeElapsed += timeIncrement;
            }

            distances[i] = distance;

        }

        return distances;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cowrace.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numSegments = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        segments = new int[][][]{new int[numSegments[0]][2], new int[numSegments[1]][2]};

        points = new TreeSet<>();
        points.add(0);

        int timeElapsed;

        for(int i=0; i < 2; i++) {

            timeElapsed = 0;

            for(int j = 0; j < numSegments[i]; j++) {

                st = new StringTokenizer(br.readLine());

                segments[i][j][0] = Integer.parseInt(st.nextToken());
                segments[i][j][1] = Integer.parseInt(st.nextToken());

                timeElapsed += segments[i][j][1];
                points.add(timeElapsed);

            }

        }

        br.close();

        numPoints = points.size();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowrace.out")));

        pw.println(countChanges(distances(0), distances(1)));

        pw.close();

    }

}
