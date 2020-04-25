import java.io.*;
import java.util.Arrays;

public class Main {

    public static int numPoints;
    public static int[] points;

    public static int change(int min) {

        int total = 0;

        if(points[0] < min) {
            int pointer = 0;
            while(pointer < numPoints && points[pointer] < min) total += Math.pow(points[pointer++] - min, 2);
        }

        if(points[numPoints - 1] > min + 17) {
            int pointer = numPoints - 1;
            while(pointer >= 0 && points[pointer] > min + 17) total += Math.pow(points[pointer--] - min - 17, 2);
        }

        return total;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("skidesign.in"));

        numPoints = Integer.parseInt(br.readLine());

        points = new int[numPoints];

        for(int i=0; i < numPoints; i++) points[i] = Integer.parseInt(br.readLine());

        br.close();

        Arrays.sort(points);

        int min = Integer.MAX_VALUE;

        for(int i=0; i < 100; i++) min = Math.min(min, change(i));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));

        pw.println(min);

        pw.close();

    }

}
