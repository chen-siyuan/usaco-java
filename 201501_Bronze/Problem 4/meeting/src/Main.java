import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static int numPoints;
    public static int numPaths;
    public static ArrayList<Path>[] paths;

    public static ArrayList<Integer> generateTimes(boolean isBTime) {

        ArrayList<Integer>[] times = new ArrayList[numPoints];

        for(int i=numPoints - 1; i >= 0; i--) {

            times[i] = new ArrayList<>();

            if(i == numPoints - 1) {
                times[i].add(0);
                continue;
            }

            for(Path path: paths[i]) for(Integer time: times[path.getDestination()]) times[i].add(time + path.getTime(isBTime));

        }

        return times[0];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("meeting.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());
        numPaths = Integer.parseInt(st.nextToken());

        paths = new ArrayList[numPoints];
        for(int i=0; i < numPoints; i++) paths[i] = new ArrayList<>();

        for(int i=0; i < numPaths; i++) {

            st = new StringTokenizer(br.readLine());

            paths[Integer.parseInt(st.nextToken()) - 1].add(new Path(
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));

        }

        br.close();

        TreeSet<Integer> common = new TreeSet<>(generateTimes(false));
        common.retainAll(new HashSet<>(generateTimes(true)));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meeting.out")));

        pw.println(common.size() == 0 ? "IMPOSSIBLE" : common.first());

        pw.close();

    }

}

class Path {

    private final int destination;
    private final int aTime;
    private final int bTime;

    public Path(int destination, int aTime, int bTime) {
        this.destination = destination;
        this.aTime = aTime;
        this.bTime = bTime;
    }

    public int getDestination() {
        return destination;
    }

    public int getTime(boolean isBTime) {
        return isBTime ? bTime : aTime;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", destination, aTime, bTime);
    }

}
