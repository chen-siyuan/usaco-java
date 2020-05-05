import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static Point[] points;

    public static boolean couldEscape(int index) {

        int left = index;
        int right = index + 1;

        while(true) {

            if(points[right].getPosition() - points[left].getPosition() > points[left].getMass()) {
                if(--left == -1) return true;
                continue;
            }

            if(points[right].getPosition() - points[left].getPosition() > points[right].getMass()) {
                if(++right == numPoints) return true;
                continue;
            }

            return false;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("trapped.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());

        points = new Point[numPoints];

        for(int i=0; i < numPoints; i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        Arrays.sort(points);

        int count = 0;

        for(int i=0; i < numPoints - 1; i++) if(!couldEscape(i))
            count += points[i + 1].getPosition() - points[i].getPosition();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("trapped.out")));

        pw.println(count);

        pw.close();

    }

}

class Point implements Comparable<Point> {

    private final int mass;
    private final int position;

    public Point(int mass, int position) {
        this.mass = mass;
        this.position = position;
    }

    public int getMass() {
        return mass;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public int compareTo(Point point) {
        return position - point.position;
    }

    @Override
    public String toString() {
        return String.format("%d %d", mass, position);
    }

}
