import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    public static int numPoints;
    public static Point[] points;
    public static ArrayList<Integer> ys;

    public static int minMax(int y) {

        int min = Integer.MAX_VALUE;

        int leftUp = 0;
        int leftDown = 0;
        int rightUp = 0;
        int rightDown = 0;

        for(int i=0; i < numPoints; i++) {

            if(points[i].getY() > y) {
                rightUp++;
            } else {
                rightDown++;
            }

        }

        for(int i=0; i < numPoints; i++) {

            if(points[i].getY() > y) {
                leftUp++;
                rightUp--;
            } else {
                leftDown++;
                rightDown--;
            }

            if(i == numPoints - 1 || points[i].getX() != points[i + 1].getX()) {

                int max = Integer.MIN_VALUE;

                max = Math.max(max, leftUp);
                max = Math.max(max, leftDown);
                max = Math.max(max, rightUp);
                max = Math.max(max, rightDown);

                min = Math.min(min, max);

            }

        }

        return min;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("balancing.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());

        points = new Point[numPoints];
        TreeSet<Integer> temp = new TreeSet<>();

        for(int i=0; i < numPoints; i++) {

            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            temp.add(points[i].getY() + 1);

        }

        br.close();

        Arrays.sort(points);
        ys = new ArrayList<>(temp);
        ys.add(0, ys.get(0) - 2);

        int answer = Integer.MAX_VALUE;

        for(int y: ys) answer = Math.min(answer, minMax(y));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("balancing.out")));

        pw.println(answer);

        pw.close();

    }

}

class Point implements Comparable<Point> {

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
    public int compareTo(Point other) {
        return x - other.x;
    }

    @Override
    public String toString() {
        return String.format("%d %d", x, y);
    }

}