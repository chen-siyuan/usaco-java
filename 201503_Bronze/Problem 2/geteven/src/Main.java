import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static String MAP = "BESIGOM";
    public static int numInfo;
    public static int[][] infos;
    public static int[] indices;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("geteven.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numInfo = Integer.parseInt(st.nextToken());

        infos = new int[7][2];

        for(int i=0; i < numInfo; i++) {
            st = new StringTokenizer(br.readLine());
            infos[MAP.indexOf(st.nextToken())][Math.abs(Integer.parseInt(st.nextToken()) % 2)]++;
        }

        br.close();

        indices = new int[]{-1, 0, 0, 0, 0, 0, 0};

        int count = 0;

        for(int i=0; i < 128; i++) {

            indices[0]++;

            int pointer = 0;

            while(indices[pointer] == 2) {
                indices[pointer] = 0;
                indices[++pointer]++;
            }

            if((indices[0] + indices[3]) % 2 == 0 || (indices[6]) % 2 == 0
                    || (indices[1] + indices[2] + indices[4] + indices[5]) % 2 == 0) {

                int temp = 1;
                for(int j=0; j < 7; j++) temp *= infos[j][indices[j]];

                count += temp;

            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("geteven.out")));

        pw.println(count);

        pw.close();

    }

}
