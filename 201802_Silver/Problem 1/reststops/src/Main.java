import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static long totalDistance;
    public static int numPoints;
    public static long slowCost;
    public static long fastCost;
    public static Point[] rawPoints;
    public static ArrayList<Point> points;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("reststops.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        totalDistance = Integer.parseInt(st.nextToken());
        numPoints = Integer.parseInt(st.nextToken());
        slowCost = Integer.parseInt(st.nextToken());
        fastCost = Integer.parseInt(st.nextToken());

        rawPoints = new Point[numPoints];

        for(int i=0; i < numPoints; i++) {
            st = new StringTokenizer(br.readLine());
            rawPoints[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        points = new ArrayList<>();

        int pointer = numPoints;

        while(pointer-- > 0) if(pointer == numPoints - 1 || rawPoints[pointer].getFactor() > points.get(0).getFactor())
            points.add(0, rawPoints[pointer]);

        long position = 0;
        long answer = 0;

        for(Point point: points) {
            answer += (point.getPosition() - position) * (slowCost - fastCost) * point.getFactor();
            position = point.getPosition();
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("reststops.out")));

        pw.println(answer);

        pw.close();

    }

}

class Point {

    private final long position;
    private final long factor;

    public Point(long position, long factor) {
        this.position = position;
        this.factor = factor;
    }

    public long getPosition() {
        return position;
    }

    public long getFactor() {
        return factor;
    }

    @Override
    public String toString() {
        return String.format("%d %d", position, factor);
    }

}
