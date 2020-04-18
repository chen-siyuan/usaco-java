import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numPairs;
    public static int[][] pairs;

    public static int convert(String string) {
        return (string.charAt(0) - 'A') * 26 + string.charAt(1) - 'A';
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("citystate.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPairs = Integer.parseInt(st.nextToken());
        pairs = new int[676][676];

        for(int i = 0; i < numPairs; i++) {
            st = new StringTokenizer(br.readLine());
            pairs[convert(st.nextToken())][convert(st.nextToken())]++;
        }

        br.close();

        int total = 0;

        for(int i=0; i < 676; i++) {
            for(int j=i + 1; j < 676; j++) total += pairs[i][j] * pairs[j][i];
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("citystate.out")));

        pw.println(total);

        pw.close();

    }

}
