import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int numLines;
    public static int[] colonies;
    public static int answer;

    public static void standardize(int standard, int value) {
        for(int i=1; i < numCows + 1; i++) if(Math.abs(colonies[i]) == Math.abs(value))
            colonies[i] = standard * value / colonies[i];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("truth.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        numLines = Integer.parseInt(st.nextToken());

        colonies = new int[numCows + 1];

        answer = 0;

        for(int i=0; i < numLines; i++) {

            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            boolean align = "T".equals(st.nextToken());

            if(colonies[x] == 0) {

                if(colonies[y] == 0) {
                    colonies[x] = x;
                    colonies[y] = x * (align ? 1 : -1);
                } else {
                    colonies[x] = colonies[y] * (align ? 1 : -1);
                }

            } else {

                if(colonies[y] == 0) {
                    colonies[y] = colonies[x] * (align ? 1 : -1);
                } else {

                    if(Math.abs(colonies[x]) == Math.abs(colonies[y])) {
                        if(answer == 0 && align != (colonies[x] == colonies[y])) answer = i;
                    } else {
                        standardize(colonies[x], colonies[y] * (align ? 1 : -1));
                    }

                }

            }

        }

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("truth.out")));

        if(answer == 0) {
            pw.println(numLines);
        } else {
            pw.println(answer);
        }

        pw.close();

    }

}
