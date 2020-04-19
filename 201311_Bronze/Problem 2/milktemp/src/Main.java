import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int coldMilk;
    public static int warmMilk;
    public static int hotMilk;
    public static Point[] points;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("milktemp.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        coldMilk = Integer.parseInt(st.nextToken());
        warmMilk = Integer.parseInt(st.nextToken());
        hotMilk = Integer.parseInt(st.nextToken());

        points = new Point[numCows * 2];

        for(int i=0; i < numCows; i++) {

            st = new StringTokenizer(br.readLine());

            points[i * 2] = new Point(Integer.parseInt(st.nextToken()), false);
            points[i * 2 + 1] = new Point(Integer.parseInt(st.nextToken()), true);

        }

        br.close();

        Arrays.sort(points);

        int total = numCows * coldMilk;
        int max = total;

        for(Point point: points) {
            total += point.isEnd() ? (hotMilk - warmMilk) : (warmMilk - coldMilk);
            max = Math.max(max, total);
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milktemp.out")));

        pw.println(max);

        pw.close();

    }

}

class Point implements Comparable<Point> {

    private final int position;
    private final boolean isEnd;

    public Point(int position, boolean isEnd) {
        this.position = position;
        this.isEnd = isEnd;
    }

    public boolean isEnd() {
        return isEnd;
    }

    @Override
    public int compareTo(Point other) {

        if(position == other.position) {
            return (isEnd ? 1 : 0) - (other.isEnd ? 1 : 0);
        } else {
            return position - other.position;
        }

    }

    @Override
    public String toString() {
        return String.format("%s%d", isEnd ? "E" : "S", position);
    }

}