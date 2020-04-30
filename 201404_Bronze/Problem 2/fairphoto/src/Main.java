import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static Point[] points;

    public static int singleMaxSize() {

        int max = 0;
        int head = points[0].getPosition();
        int tail = points[0].getPosition();

        for(int i=1; i < numPoints; i++) {

            if (points[i].getColor() != points[i - 1].getColor()) {
                max = Math.max(max, tail - head);
                head = points[i].getPosition();
            }

            tail = points[i].getPosition();

        }

        return max;
    }

    public static int doubleMaxSize() {

        int[] prefixSums = new int[numPoints + 1];
        prefixSums[0] = 0;

        for(int i=0; i < numPoints; i++) prefixSums[i + 1] = prefixSums[i] + points[i].getColor();

        HashMap<Integer, Integer> heads = new HashMap<>();
        HashMap<Integer, Integer> tails = new HashMap<>();

        for(int i=numPoints-1; i >= 0; i--) heads.put(prefixSums[i], points[i].getPosition());

        for(int i=0; i < numPoints; i++) tails.put(prefixSums[i + 1], points[i].getPosition());

        int max = 0;

        for(int key: tails.keySet()) if(heads.containsKey(key)) max = Math.max(max, tails.get(key) - heads.get(key));

        return max;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("fairphoto.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());

        points = new Point[numPoints];

        for(int i=0; i < numPoints; i++){
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Integer.parseInt(st.nextToken()), "G".equals(st.nextToken()) ? 1 : -1);
        }

        Arrays.sort(points);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fairphoto.out")));

        pw.println(Math.max(singleMaxSize(), doubleMaxSize()));

        pw.close();

    }

}

class Point implements Comparable<Point> {

    private final int position;
    private final int color;

    public Point(int position, int color) {
        this.position = position;
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public int getColor() {
        return color;
    }

    @Override
    public int compareTo(Point point) {
        return position - point.position;
    }

    @Override
    public String toString() {
        return String.format("%d %d", position, color);
    }

}
