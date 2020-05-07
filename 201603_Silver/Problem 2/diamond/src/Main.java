import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numPoints;
    public static int range;
    public static int[] points;
    public static int[] leftContain;
    public static int[] leftContainMax;
    public static ArrayList<Integer> leftInRange;
    public static int[] rightContain;
    public static int[] rightContainMax;
    public static ArrayList<Integer> rightInRange;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("diamond.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPoints = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());

        points = new int[numPoints];

        for(int i=0; i < numPoints; i++) points[i] = Integer.parseInt(br.readLine());

        br.close();

        Arrays.sort(points);

        leftContain = new int[numPoints];
        leftContainMax = new int[numPoints];
        leftInRange = new ArrayList<>();

        for(int i=0; i < numPoints; i++) {

            leftInRange.add(points[i]);

            while(leftInRange.get(0) < points[i] - range) leftInRange.remove(0);

            leftContain[i] = leftInRange.size();
            leftContainMax[i] = i == 0 ? leftContain[i] : Math.max(leftContainMax[i - 1], leftContain[i]);

        }

        rightContain = new int[numPoints];
        rightContainMax = new int[numPoints];
        rightInRange = new ArrayList<>();

        for(int i=numPoints - 1; i >= 0; i--) {

            rightContain[i] = rightInRange.size();
            rightContainMax[i] = i == numPoints - 1 ? rightContain[i] : Math.max(rightContainMax[i + 1], rightContain[i]);

            rightInRange.add(points[i]);

            while(rightInRange.get(0) > points[i] + range) rightInRange.remove(0);

        }

        int maxContain = Integer.MIN_VALUE;

        for(int i=0; i < numPoints; i++) maxContain = Math.max(maxContain, leftContainMax[i] + rightContainMax[i]);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("diamond.out")));

        pw.println(maxContain);

        pw.close();

    }

}
