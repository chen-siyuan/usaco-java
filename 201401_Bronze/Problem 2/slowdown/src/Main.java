import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static ArrayList<Double> distancePoints;
    public static ArrayList<Double> timePoints;
    public static int numDistancePoints;
    public static int numTimePoints;

    public static double solve() {

        double total = 0;
        int bonus = 1;
        double prevDistance = 0;

        while(distancePoints.size() > 0) {

            if(timePoints.size() > 0 && total + bonus * (distancePoints.get(0) - prevDistance) > timePoints.get(0)) {
                distancePoints.add(0, (timePoints.get(0) - total) / bonus + prevDistance);
                timePoints.remove(0);
            }

            total += bonus++ * (distancePoints.get(0) - prevDistance);
            prevDistance = distancePoints.remove(0);

        }

        return total;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("slowdown.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());

        timePoints = new ArrayList<>();
        distancePoints = new ArrayList<>();

        for(int i=0; i < numPoints; i++) {

            st = new StringTokenizer(br.readLine());

            if("T".equals(st.nextToken())) {
                timePoints.add(Double.parseDouble(st.nextToken()));
            } else {
                distancePoints.add(Double.parseDouble(st.nextToken()));
            }

        }

        br.close();

        distancePoints.add(1000.);

        numDistancePoints = distancePoints.size();
        numTimePoints = timePoints.size();

        Collections.sort(distancePoints);
        Collections.sort(timePoints);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("slowdown.out")));

        pw.println((int)Math.round(solve()));

        pw.close();

    }


}
