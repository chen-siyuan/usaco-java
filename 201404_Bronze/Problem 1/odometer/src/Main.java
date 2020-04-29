import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static long lower;
    public static long upper;
    public static int count = 0;
    public static HashSet<Long> moos;

    public static void check() {
        for(long moo: moos) if(lower <= moo && moo <= upper) count++;
    }

    public static void generate() {
        for(int i=3; i < 18; i++) generateLevel(i);
    }

    public static void generateLevel(int level) {

        for(int i=0; i < 10; i++) {

            for(int j=0; j < 10; j++) if(i != j) {

                long start = (long)(Math.pow(10, level) - 1) / 9 * i - i + j;
                long increment = 9 * (j - i);

                for(int k=0; k < level; k++) {

                    if(start >= (long)Math.pow(10, level - 1)) moos.add(start);

                    start += increment;
                    increment *= 10;

                }

            }

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("odometer.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        lower = Long.parseLong(st.nextToken());
        upper = Long.parseLong(st.nextToken());

        br.close();

        moos = new HashSet<>();
        count = 0;

        generate();
        check();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("odometer.out")));

        pw.println(count);

        pw.close();

    }

}
