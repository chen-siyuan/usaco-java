import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("bcount.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numCows = Integer.parseInt(st.nextToken());
        int numQueries = Integer.parseInt(st.nextToken());

        int[][] breeds = new int[numCows][3];

        for(int i=0; i < numCows; i++) {
            if(i > 0) {
                breeds[i][0] = breeds[i - 1][0];
                breeds[i][1] = breeds[i - 1][1];
                breeds[i][2] = breeds[i - 1][2];
            }

            breeds[i][Integer.parseInt(br.readLine()) - 1]++;
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));

        for(int i=0; i < numQueries; i++) {

            st = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(st.nextToken()) - 2;
            int k = Integer.parseInt(st.nextToken()) - 1;

            if(j == -1) {
                pw.println(String.format("%d %d %d", breeds[k][0], breeds[k][1], breeds[k][2]));
            } else {
                pw.println(String.format("%d %d %d",
                        breeds[k][0] - breeds[j][0],
                        breeds[k][1] - breeds[j][1],
                        breeds[k][2] - breeds[j][2]));
            }

        }

        br.close();
        pw.close();

    }

}
