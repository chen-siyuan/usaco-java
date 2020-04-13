import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int dimension;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("lightson.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        dimension = Integer.parseInt(st.nextToken());
        int numKeys = Integer.parseInt(st.nextToken());

        HashMap<Point, ArrayList<Point>> treasures = new HashMap<>();

        for(int i=0; i < numKeys; i++) {

            st = new StringTokenizer(br.readLine());

            Point location = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
            Point key = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

            if(!treasures.containsKey(location)) treasures.put(location, new ArrayList<>());
            treasures.get(location).add(key);

        }

        br.close();

        HashSet<Point> searched = new HashSet<>();
        HashSet<Point> obtained = new HashSet<>();

        ArrayList<Point> queue = new ArrayList<>();
        queue.add(new Point(0, 0));

        while(queue.size() > 0) {

            Point point = queue.remove(0);

            if(searched.contains(point)) continue;
            searched.add(point);

            if(treasures.containsKey(point)) {
                for (Point key : treasures.get(point)) {

                    if (searched.contains(key) || obtained.contains(key)) continue;

                    if ((key.getX() > 0 && searched.contains(new Point(key.getX() - 1, key.getY())))
                            || (key.getY() > 0 && searched.contains(new Point(key.getX(), key.getY() - 1)))
                            || (key.getX() < dimension - 1 && searched.contains(new Point(key.getX() + 1, key.getY())))
                            || (key.getY() < dimension - 1 && searched.contains(new Point(key.getX(), key.getY() + 1)))) {
                        queue.add(key);
                    } else {
                        obtained.add(key);
                    }

                }
            }

            Point temp;

            if(point.getX() > 0) {
                temp = new Point(point.getX() - 1, point.getY());
                if((!searched.contains(temp) && obtained.contains(temp))) queue.add(temp);
            }

            if(point.getY() > 0) {
                temp = new Point(point.getX(), point.getY() - 1);
                if((!searched.contains(temp) && obtained.contains(temp))) queue.add(temp);
            }

            if(point.getX() < dimension - 1) {
                temp = new Point(point.getX() + 1, point.getY());
                if((!searched.contains(temp) && obtained.contains(temp))) queue.add(temp);
            }

            if(point.getY() < dimension - 1) {
                temp = new Point(point.getX(), point.getY() + 1);
                if((!searched.contains(temp) && obtained.contains(temp))) queue.add(temp);
            }

        }

        searched.addAll(obtained);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lightson.out")));

        pw.println(searched.size());

        pw.close();

    }

}

class Point {

    private int x;
    private int y;

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
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        return x == ((Point)obj).x && y == ((Point)obj).y;
    }

    @Override
    public int hashCode() {
        return x * Main.dimension + y;
    }

    @Override
    public String toString() {
        return String.format("%d %d", x + 1, y + 1);
    }

}
