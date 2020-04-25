import java.io.*;

public class Main {

    public static int[] values;
    public static int[] indexes;
    public static int min;

    public static boolean canSearch(int position) {

        if(indexes[position] != -1) return false;

        if(position == 0) return true;

        if(position < 4) return indexes[position - 1] != -1;

        return indexes[position - 4] != -1;
    }

    public static void search(int searched) {

        if(searched == 12) {
            min = Math.min(min, difference());
            return;
        }

        for(int i=0; i < 12; i++) if(canSearch(i)) {

            indexes[i] = searched;
            search(searched + 1);
            indexes[i] = -1;

        }

    }

    public static int difference() {

        int[] totals = new int[4];

        for(int i=0; i < 12; i++) totals[i % 4] += values[indexes[i]];

        return Math.max(Math.max(totals[0], totals[1]), Math.max(totals[2], totals[3]))
                - Math.min(Math.min(totals[0], totals[1]), Math.min(totals[2], totals[3]));
    }

    public static void main(String[] args) throws IOException {

        values = new int[12];
        indexes = new int[12];

        BufferedReader br = new BufferedReader(new FileReader("bteams.in"));

        for(int i=0; i < 12; i++) values[i] = Integer.parseInt(br.readLine());

        br.close();

        for(int i=0; i < 12; i++) indexes[i] = -1;

        min = Integer.MAX_VALUE;

        search(0);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bteams.out")));

        pw.println(min);

        pw.close();

    }

}
