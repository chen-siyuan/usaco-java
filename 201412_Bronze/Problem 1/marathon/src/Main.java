import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static Point[] points;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("marathon.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());

        points = new Point[numPoints];

        for(int i=0; i < numPoints; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        int totalTime = 0;

        for(int i=0; i < numPoints - 1; i++) totalTime += Point.distance(points[i], points[i + 1]);

        int maxTimeSaved = Integer.MIN_VALUE;

        for(int i=0; i < numPoints - 2; i++) maxTimeSaved = Math.max(maxTimeSaved,
                Point.distance(points[i], points[i + 1]) + Point.distance(points[i + 1], points[i + 2]) - Point.distance(points[i], points[i + 2]));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));

        pw.println(totalTime - maxTimeSaved);

        pw.close();

    }

}

class Point {

    public static int distance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("%d %d", x, y);
    }

}
