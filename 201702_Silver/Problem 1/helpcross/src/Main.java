import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static int numIntervals;
    public static int[] points;
    public static ArrayList<Interval> intervals;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());
        numIntervals = Integer.parseInt(st.nextToken());

        points = new int[numPoints];
        intervals = new ArrayList<>();

        for(int i=0; i < numPoints; i++) points[i] =  Integer.parseInt(br.readLine());
        for(int i=0; i < numIntervals; i++) {
            st = new StringTokenizer(br.readLine());
            intervals.add(new Interval(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        br.close();

        Arrays.sort(points);
        Collections.sort(intervals);

        int count = 0;

        for(int i=0; i < numPoints; i++) {

            if(intervals.size() == 0) break;

            int temp = 0;
            while(temp < intervals.size()) {

                if(intervals.get(temp).getEnd() < points[i]) {
                    intervals.remove(temp);
                } else {
                    temp++;
                }

            }

            if(intervals.get(0).getStart() > points[i]) continue;

            int pointer = 0;
            int index = -1;
            int minEnd = Integer.MAX_VALUE;

            while(pointer < intervals.size() && intervals.get(pointer).contains(points[i])) {

                if(minEnd > intervals.get(pointer).getEnd()) {
                    minEnd = intervals.get(pointer).getEnd();
                    index = pointer;
                }

                pointer++;

            }

            count++;
            intervals.remove(index);

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));

        pw.println(count);

        pw.close();

    }

}

class Interval implements Comparable<Interval> {

    private final int start;
    private final int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean contains(int point) {
        return (start <= point) && (point <= end);
    }

    @Override
    public String toString() {
        return String.format("%d %d", start, end);
    }

    @Override
    public int compareTo(Interval other) {
        if(start == other.start) {
            return end - other.end;
        } else {
            return start - other.start;
        }
    }

}