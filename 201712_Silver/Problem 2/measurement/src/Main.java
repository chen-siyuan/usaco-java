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
    public static int maxChange;

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
        maxChange = 0;

        Arrays.sort(lines);

        int count = 0;

        for(int i=0; i < numLines; i++) {

            int cow = lines[i].getCow();

            boolean flag = record.containsKey(cow);

            if(!flag) record.put(cow, 0);

            int change = record.get(cow) + lines[i].getChange();

            if(change != 0) record.put(cow, change);

            if(maxCows.contains(cow) || (maxCows.size() == 1 && maxCows.contains(0) && !flag)) {

                if(lines[i].getChange() > 0) {

                    if(maxCows.size() == 1 && maxCows.contains(cow)) {
                        maxChange = change;
                    } else {

                        maxChange = change;
                        maxCows.clear();
                        maxCows.add(cow);
                        count++;

                    }

                } else {

                    if(maxCows.size() == 1) {

                        if(maxCows.contains(0)) {
                            count++;
                        } else {

                            maxCows.clear();
                            maxChange = 0;

                            for(int key: record.keySet()) {

                                if(maxChange < record.get(key)) {
                                    maxChange = record.get(key);
                                    maxCows.clear();
                                }

                                if(maxChange == record.get(key)) maxCows.add(key);

                            }

                            count++;
                        }

                    } else {
                        maxCows.remove(cow);
                        count++;
                    }

                }

            } else {

                if(lines[i].getChange() > 0) {

                    if(change > maxChange) {

                        maxCows.clear();
                        maxCows.add(cow);
                        maxChange = change;

                        count++;

                    } else if(change == maxChange) {
                        if(change != 0) maxCows.add(cow);
                        count++;
                    }

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
