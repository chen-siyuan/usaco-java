import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int numLines;
    public static Line[] lines;
    public static HashMap<Integer, Integer> record;
    public static HashSet<Integer> maxCows;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numLines = Integer.parseInt(st.nextToken());

        lines = new Line[numLines];

        for(int i=0; i < numLines; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        record = new HashMap<>();
        record.put(0, 0);
        maxCows = new HashSet<>();
        maxCows.add(0);

        Arrays.sort(lines);

        int count = 0;

        for(int i=0; i < numLines; i++) {

            if(!record.containsKey(lines[i].getCow())) record.put(lines[i].getCow(), 0);

            record.put(lines[i].getCow(), record.get(lines[i].getCow()) + lines[i].getChange());

            if(lines[i].getChange() > 0 || maxCows.contains(lines[i].getCow())) {

                HashSet<Integer> newMaxCows = new HashSet<>();

                int maxChange = Integer.MIN_VALUE;

                for(int cow: record.keySet()) {

                    if(maxChange < record.get(cow)) {
                        newMaxCows.clear();
                        maxChange = record.get(cow);
                    }

                    if(maxChange == record.get(cow)) newMaxCows.add(cow);

                }

                if(!maxCows.equals(newMaxCows)) {
                    count++;
                    maxCows = newMaxCows;
                }

            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));

        pw.println(count);

        pw.close();

    }

}

class Line implements Comparable<Line> {

    private final int time;
    private final int cow;
    private final int change;

    public Line(int time, int cow, int change) {
        this.time = time;
        this.cow = cow;
        this.change = change;
    }

    public int getCow() {
        return cow;
    }

    public int getChange() {
        return change;
    }

    @Override
    public int compareTo(Line line) {
        return time - line.time;
    }

    @Override
    public String toString() {
        return String.format("%d %d %d", time, cow, change);
    }

}
