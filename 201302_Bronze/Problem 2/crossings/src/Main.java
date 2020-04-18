import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static boolean sortA = true;
    public static Point[] points;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("crossings.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());

        points = new Point[numPoints];
        for(int i=0; i < numPoints; i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        Arrays.sort(points);
        for(int i=0; i < numPoints; i++) points[i].setA(i);
        sortA = false;
        Arrays.sort(points);

        int min = points[0].getA();
        int max = points[0].getA();
        int count = points[0].getA() == 0 ? 1 : 0;

        for(int i=1; i < numPoints; i++) {

            if(min == 0 && max == i - 1 && i == points[i].getA()) {
                max++;
                count++;
            } else {
                min = Math.min(min, points[i].getA());
                max = Math.max(max, points[i].getA());
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crossings.out")));//

        pw.println(count);

        pw.close();

    }

}

class Point implements Comparable<Point> {

    private int a;
    private int b;

    public Point(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public int compareTo(Point other) {

        if(Main.sortA) {
            return a -other.a;
        } else {
            return b - other.b;
        }

    }

    @Override
    public String toString() {
        return String.format("%d %d", a, b);
    }

}