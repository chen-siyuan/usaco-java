import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numDigits;
    public static int[] farmerDigits;
    public static int[] masterDigits;

    public static int distance(int a, int b) {

        if(a < b) {
            return Math.min(b - a, a + numDigits - b);
        } else {
            return Math.min(a - b, b + numDigits - a);
        }

    }

    public static boolean canUnlock(int x, int y, int z) {
        return (distance(x, farmerDigits[0]) <= 2
                && distance(y, farmerDigits[1]) <= 2
                && distance(z, farmerDigits[2]) <= 2)
                ||
                (distance(x, masterDigits[0]) <= 2
                && distance(y, masterDigits[1]) <= 2
                && distance(z, masterDigits[2]) <= 2);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("combo.in"));

        numDigits = Integer.parseInt(br.readLine());
        farmerDigits = new int[3];
        masterDigits = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i < 3; i++) farmerDigits[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for(int i=0; i < 3; i++) masterDigits[i] = Integer.parseInt(st.nextToken());

        br.close();

        int count = 0;

        for(int i=0; i < numDigits; i++) for(int j=0; j < numDigits; j++) for(int k=0; k < numDigits; k++)
            if(canUnlock(i, j, k)) count++;

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

        pw.println(count);

        pw.close();

    }


}
