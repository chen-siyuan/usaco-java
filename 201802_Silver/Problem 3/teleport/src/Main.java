import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numPairs;
    public static int numLeftPairs;
    public static int numRightPairs;
    public static int[][] pairs;
    public static int[][] leftPairs;
    public static int[][] rightPairs;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("teleport.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPairs = Integer.parseInt(st.nextToken());

        numLeftPairs = 0;
        numRightPairs = 0;
        pairs = new int[numPairs][2];

        for(int i=0; i < numPairs; i++) {

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pairs[i][0] = Math.min(x, y);
            pairs[i][1] = Math.max(x, y);

            if(pairs[i][0] < 0) numLeftPairs++;
            if(pairs[i][1] > 0) numRightPairs++;

        }

        br.close();

        leftPairs = new int[numLeftPairs][2];
        rightPairs = new int[numRightPairs][2];

        int countLeftPairs = 0;
        int countRightPairs = 0;

        long leftSum = 0;
        long rightSum = 0;

        for(int i=0; i < numPairs; i++) {

            if(pairs[i][0] < 0) {

                leftPairs[countLeftPairs][0] = pairs[i][0];
                leftPairs[countLeftPairs][1] = Math.min(0, pairs[i][1]);

                leftSum += leftPairs[countLeftPairs][1] - leftPairs[countLeftPairs++][0];

            }

            if(pairs[i][1] > 0) {

                rightPairs[countRightPairs][0] = Math.max(0, pairs[i][0]);
                rightPairs[countRightPairs][1] = pairs[i][1];

                rightSum += rightPairs[countRightPairs][1] - rightPairs[countRightPairs++][0];

            }

        }

        long sum = Long.MAX_VALUE;

        for(int i=0; i < numPairs * 2; i++) {

            if(pairs[i / 2][i % 2] == 0) continue;

            long tempSum = 0;

            if(pairs[i / 2][i % 2] < 0) {

                for(int j=0; j < numLeftPairs; j++) {
                    tempSum += Math.min(leftPairs[j][1] - leftPairs[j][0], -leftPairs[j][1] + Math.abs(pairs[i / 2][i % 2] - leftPairs[j][0]));
                }

                sum = Math.min(sum, tempSum + rightSum);

            } else {

                for(int j=0; j < numRightPairs; j++) {
                    tempSum += Math.min(rightPairs[j][1] - rightPairs[j][0], rightPairs[j][0] + Math.abs(pairs[i / 2][i % 2] - rightPairs[j][1]));
                }

                sum = Math.min(sum, tempSum + leftSum);

            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teleport.out")));

        System.out.println(sum);

        pw.close();

    }

}
