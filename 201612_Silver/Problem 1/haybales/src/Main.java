import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static int numIntervals;
    public static int[] points;
    public static int min;
    public static int max;

    public static int searchHead(int point, int head, int tail) {

        if(point > max) return numPoints;
        if(head + 1 == tail) return tail;
        int mid = (head + tail) / 2;

        return point <= points[mid] ? searchHead(point, head, mid) : searchHead(point, mid, tail);
    }

    public static int searchTail(int point, int head, int tail) {

        if(point < min) return -1;
        if(head + 1 == tail) return head;
        int mid = (head + tail) / 2;

        return point < points[mid] ? searchTail(point, head, mid) : searchTail(point, mid, tail);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());
        numIntervals = Integer.parseInt(st.nextToken());

        points = new int[numPoints];

        st = new StringTokenizer(br.readLine());

        for(int i=0; i < numPoints; i++) points[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(points);

        min = points[0];
        max = points[numPoints - 1];

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));

        for(int i=0; i < numIntervals; i++) {
            st = new StringTokenizer(br.readLine());
            pw.println(1
                    - searchHead(Integer.parseInt(st.nextToken()), -1, numPoints - 1)
                    + searchTail(Integer.parseInt(st.nextToken()), 0, numPoints));
        }

        br.close();

        pw.close();

    }

}
