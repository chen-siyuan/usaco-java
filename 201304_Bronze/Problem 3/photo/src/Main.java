import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static Point[] points;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("photo.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        st.nextToken();
        numPoints = Integer.parseInt(st.nextToken());

        points = new Point[numPoints * 2];

        for(int i=0; i < numPoints; i++) {

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points[i * 2] = new Point(x, x > y, i);
            points[i * 2 + 1] = new Point(y, y > x, i);

        }

        br.close();

        Arrays.sort(points);

        HashSet<Integer> intervals = new HashSet<>();
        int count = 0;

        for(int i=0; i < numPoints * 2; i++) {

            if(points[i].isEnding()) {

                if(intervals.contains(points[i].getPair())) {
                    count++;
                    intervals.clear();
                }

            } else {
                intervals.add(points[i].getPair());
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("photo.out")));

        pw.println(count + 1);

        pw.close();

    }

}

class Point implements Comparable<Point> {

    private final int position;
    private final boolean ending;
    private final int pair;

    public Point(int position, boolean ending, int pair) {
        this.position = position;
        this.ending = ending;
        this.pair = pair;
    }

    public boolean isEnding() {
        return ending;
    }

    public int getPair() {
        return pair;
    }

    @Override
    public int compareTo(Point point) {

        if(position == point.position) {
            return (ending ? 0 : 1) - (point.ending ? 0 : 1);
        } else {
            return position - point.position;
        }

    }

    @Override
    public String toString() {
        return pair + (ending ? " E" : " S");
    }

}
