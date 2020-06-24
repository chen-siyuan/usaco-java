import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static Segment[] segments;
    public static Point[] points;
    public static int[] pair;

    public static int[] search() {

        List<Segment> list = new ArrayList<>();
    
        for(Point point: points) if(point.type) {

            int index = -1 - Collections.binarySearch(list, segments[point.ord],
                    (Segment s1, Segment s2) -> (Double.valueOf(s1.currY(point.pos)).compareTo(s2.currY(point.pos))));

            if(index != 0 && index != list.size() && Segment.intersect(list.get(index - 1), list.get(index)))
                return new int[]{list.get(index - 1).ord, list.get(index).ord};

            list.add(index, segments[point.ord]);
        
        } else {

            int index = list.indexOf(segments[point.ord]);

            if(index > 0 && Segment.intersect(list.get(index - 1), list.get(index)))
                return new int[]{list.get(index - 1).ord, list.get(index).ord};

            if(index < list.size() - 1 && Segment.intersect(list.get(index), list.get(index + 1)))
                return new int[]{list.get(index).ord, list.get(index + 1).ord};

            if(index > 0 && index < list.size() - 1 && Segment.intersect(list.get(index - 1), list.get(index + 1)))
                return new int[]{list.get(index - 1).ord, list.get(index + 1).ord};

            list.remove(index);
        
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        segments = new Segment[n];
        points = new Point[2 * n];

        for(int i=0; i < n; i++) {

            st = new StringTokenizer(br.readLine());

            segments[i] = new Segment(i,
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            points[i * 2] = new Point(i, Math.min(segments[i].x1, segments[i].x2), true);
            points[i * 2 + 1] = new Point(i, Math.max(segments[i].x1, segments[i].x2), false);

        }

        br.close();

        Arrays.sort(points, (Point p1, Point p2) -> (p1.pos == p2.pos
                    ? (p1.type == p2.type
                        ? p1.ord - p2.ord
                        : (p1.type ? -1 : 1))
                    : p1.pos - p2.pos));

        int[] pair = search();
        Arrays.sort(pair);

        int res = -1;

        for(int i=0; i < n; i++) if(res == -1 && i != pair[0] && i != pair[1] && Segment.intersect(segments[i], segments[pair[1]])) res = pair[1];
        if(res == -1) res = pair[0];

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));

        pw.println(res + 1);

        pw.close();
    
    }

}

class Segment {

    public static boolean intersect(Segment s1, Segment s2) {
    
        long u1 = (s2.x1 - s1.x1) * (s2.y2 - s1.y1) - (s2.y1 - s1.y1) * (s2.x2 - s1.x1);
        long v1 = (s2.x1 - s1.x2) * (s2.y2 - s1.y2) - (s2.y1 - s1.y2) * (s2.x2 - s1.x2);

        long u2 = (s1.x1 - s2.x1) * (s1.y2 - s2.y1) - (s1.y1 - s2.y1) * (s1.x2 - s2.x1);
        long v2 = (s1.x1 - s2.x2) * (s1.y2 - s2.y2) - (s1.y1 - s2.y2) * (s1.x2 - s2.x2);

        return u1 * v1 <= 0 && u2 * v2 <= 0;
    }

    public final int ord;
    public final int x1, y1, x2, y2;

    public Segment(int _ord, int _x1, int _y1, int _x2, int _y2) {
    
        ord = _ord;

        x1 = _x1;
        y1 = _y1;
        x2 = _x2;
        y2 = _y2;
    
    }

    public double currY(int currX) {
        return ((currX - x1) * y2 + (x2 - currX) * y1 + 0.) / (x2 - x1);
    }

}

class Point {

    public final int ord;
    public final int pos;
    public final boolean type;

    public Point(int _ord, int _pos, boolean _type) {

        ord = _ord;
        pos = _pos;
        type = _type;

    }

}
