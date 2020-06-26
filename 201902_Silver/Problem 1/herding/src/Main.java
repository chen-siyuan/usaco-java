import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] points;

    public static int min() {

        if((points[n - 2] - points[0] == n - 2 && points[n - 1] - points[n - 2] > 2)
                || (points[n - 1] - points[1] == n - 2 && points[1] - points[0] > 2)) return 2;

        int j=0;
        int res = Integer.MIN_VALUE;

        for(int i=0; i < n; i++) {
            while(j + 1 < n && points[j + 1] < points[i] + n) j++;
            res = Math.max(res, j - i + 1);
        }

        return n - res;
    }

    public static int max() {
        return points[n - 1] - points[0] - Math.min(points[1] - points[0], points[n - 1] - points[n - 2]) - n + 2;
    }

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("herding.in"));

        n = Integer.parseInt(br.readLine());
        points = new int[n];

        for(int i=0; i < n; i++) points[i] = Integer.parseInt(br.readLine());

        br.close();

        Arrays.sort(points);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("herding.out")));

        pw.println(min());
        pw.println(max());

        pw.close();
    
    }

}
