import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static long curr, min, grad, pos;
    public static List<Point> points;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("teleport.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        curr = 0;
        points = new ArrayList<>();

        for(int i=0; i < n; i++) {

            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            curr += Math.abs(a - b);

            if(Math.abs(a) >= Math.abs(a - b)) continue;

            if(a == 0 || (a < 0 && b > 0) || (a > 0 && b < 0)) {

                points.add(new Point(0, -1));
                points.add(new Point(b, 2));
                points.add(new Point(2 * b, -1));

            } else {
                
                points.add(new Point(2 * b - 2 * a, -1));
                points.add(new Point(b, 2));
                points.add(new Point(2 * a, -1));
            
            }
            
        }

        br.close();

        Collections.sort(points);

        min = curr;
        grad = 0;
        pos = 0;

        for(Point point: points) {
        
            curr += grad * (point.getPos() - pos);
            min = Math.min(min, curr);
            grad += point.getGrad();
            pos = point.getPos();
        
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));

        pw.println(min);

        pw.close();
    
    }

}

class Point implements Comparable<Point> {

    private final int pos;
    private final int grad;

    public Point(int pos, int grad) {
        this.pos = pos;
        this.grad = grad;
    }

    public int getPos() {return pos;}
    
    public int getGrad() {return grad;}

    public int compareTo(Point other) {
        return pos - other.pos;
    }

}
