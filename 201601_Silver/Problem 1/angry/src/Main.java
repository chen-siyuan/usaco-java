import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static int numIntervals;
    public static int[] points;

    public static boolean check(int range) {

        int pointer = 0;
        int numIntervalsLeft = numIntervals - 1;
        int endOfInterval = points[0] + range;

        while(pointer < numPoints) {

            if(points[pointer] <= endOfInterval) {
                pointer++;
            } else {
                if(numIntervalsLeft == 0) return false;
                numIntervalsLeft--;
                endOfInterval = points[pointer] + range;
            }

        }

        return true;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("angry.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());
        numIntervals = Integer.parseInt(st.nextToken());

        points = new int[numPoints];

        for(int i=0; i < numPoints; i++) points[i] = Integer.parseInt(br.readLine());

        br.close();

        Arrays.sort(points);

        int radius = 0;
        while(!check(radius * 2)) radius++;

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));

        pw.println(radius);

        pw.close();

    }

}
