import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numLines;

    public static int numberInBase(int[] digits, int base) {
        return digits[0] * base * base + digits[1] * base + digits[2];
    }

    public static String solve(int x, int y) {

        int[] xDigits = new int[3];
        int[] yDigits = new int[3];

        for(int i=0; i < 3; i++) {

            xDigits[2 - i] = x % 10;
            yDigits[2 - i] = y % 10;

            x /= 10;
            y /= 10;

        }

        int j = 10;

        for(int i=10; i < 15001; i++) {
            while(numberInBase(yDigits, j) < numberInBase(xDigits, i)) j++;
            if(numberInBase(yDigits, j) == numberInBase(xDigits,  i)) return String.format("%d %d", i, j);
        }

        return null;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("whatbase.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numLines = Integer.parseInt(st.nextToken());

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("whatbase.out")));

        for(int i=0; i < numLines; i++) {
            st = new StringTokenizer(br.readLine());
            pw.println(solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        br.close();

        pw.close();

    }

}
