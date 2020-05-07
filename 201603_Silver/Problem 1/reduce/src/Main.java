import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    public static int[][] COMBINATIONS = new int[][]{
            new int[]{3, 0, 0, 0},
            new int[]{2, 1, 0, 0},
            new int[]{2, 0, 1, 0},
            new int[]{2, 0, 0, 1},
            new int[]{1, 2, 0, 0},
            new int[]{1, 1, 1, 0},
            new int[]{1, 1, 0, 1},
            new int[]{1, 0, 2, 0},
            new int[]{1, 0, 1, 1},
            new int[]{1, 0, 0, 2},
            new int[]{0, 3, 0, 0},
            new int[]{0, 2, 1, 0},
            new int[]{0, 2, 0, 1},
            new int[]{0, 1, 2, 0},
            new int[]{0, 1, 1, 1},
            new int[]{0, 1, 0, 2},
            new int[]{0, 0, 3, 0},
            new int[]{0, 0, 2, 1},
            new int[]{0, 0, 1, 2},
            new int[]{0, 0, 0, 3}};

    public static int numPoints;
    public static Point[] points1;
    public static Point[] points2;
    public static boolean compareY;

    public static Point[] generate(int[] combination) {

        Point[] result = new Point[3];
        int pointer = 0;

        for(int i=0; i < combination[0]; i++) result[pointer++] = new Point(points1[i].getX(), points1[i].getY());

        for(int i=0; i < combination[1]; i++) result[pointer++] = new Point(points1[numPoints - i - 1].getX(),
                points1[numPoints - i - 1].getY());

        for(int i=0; i < combination[2]; i++) result[pointer++] = new Point(points2[i].getX(), points2[i].getY());

        for(int i=0; i < combination[3]; i++) result[pointer++] = new Point(points2[numPoints - i - 1].getX(),
                points2[numPoints - i - 1].getY());

        return result;
    }

    public static boolean isIgnored(Point[] ignored, Point point) {
        for(int i=0; i < 3; i++) if(point.equals(ignored[i])) return true;
        return false;
    }

    public static int area(Point[] ignored) {

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for(int i=0; i < numPoints; i++) {

            if(!isIgnored(ignored, points1[i])) {

                minX = Math.min(minX, points1[i].getX());
                minY = Math.min(minY, points1[i].getY());
                maxX = Math.max(maxX, points1[i].getX());
                maxY = Math.max(maxY, points1[i].getY());

            }

        }

        return (maxX - minX) * (maxY - minY);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("reduce.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());

        points1 = new Point[numPoints];
        points2 = new Point[numPoints];

        for(int i=0; i < numPoints; i++) {

            st = new StringTokenizer(br.readLine());

            points1[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            points2[i] = new Point(points1[i].getX(), points1[i].getY());

        }

        br.close();

        compareY  = false;
        Arrays.sort(points1);

        compareY = true;
        Arrays.sort(points2);

        int answer = Integer.MAX_VALUE;

        for(int i=0; i < 20; i++) answer = Math.min(answer,
                area(generate(COMBINATIONS[i])));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reduce.out")));

        pw.println(answer);

        pw.close();

    }

}

class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    Point(int x, int y) {
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
    public int compareTo(Point point) {
        return Main.compareY ? y - point.y : x - point.x;
    }

    @Override
    public String toString() {
        return String.format("%d %d", x, y);
    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Point point = (Point) object;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
