import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static Point[] points;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("paint.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());

        points = new Point[numPoints * 2];
        int current = 0;

        for(int i=0; i < numPoints; i++) {

            st = new StringTokenizer(br.readLine());

            int temp = current + Integer.parseInt(st.nextToken()) * ("R".equals(st.nextToken()) ? 1 : -1);

            if(current < temp) {
                points[i * 2] = new Point(current, true);
                points[i * 2 + 1] = new Point(temp, false);
            } else {
                points[i * 2] = new Point(temp, true);
                points[i * 2 + 1] = new Point(current, false);
            }

            current = temp;

        }

        br.close();

        Arrays.sort(points);

        int count = 0;
        int prev = 0;
        int sum = 0;

        for(int i=0; i < numPoints * 2; i++) {

            if(points[i].getType()) {
                count++;
                if(count == 2) prev = points[i].getX();
            } else {
                count--;
                if(count == 1) sum += points[i].getX() - prev;
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paint.out")));

        pw.println(sum);

        pw.close();

    }

}

class Point implements Comparable<Point> {

    private final int x;
    private final boolean type; // true: start

    public Point(int x, boolean type) {
        this.x = x;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public boolean getType() {
        return type;
    }

    @Override
    public int compareTo(Point other) {

        if(x == other.x) {
            return - (type ? 0 : 1) + (other.type ? 0 : 1);
        } else {
            return x - other.x;
        }

    }

    @Override
    public String toString() {
        return String.format("%d ", x) + (type ? "S" : "E");
    }
}
