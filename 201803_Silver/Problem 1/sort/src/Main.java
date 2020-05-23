import java.io.*;
import java.util.Arrays;

public class Main {

    public static int num;
    public static Pair[] array;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("sort.in"));

        num = Integer.parseInt(br.readLine());

        array = new Pair[num];

        for(int i=0; i < num; i++) array[i] = new Pair(Integer.parseInt(br.readLine()), i);

        br.close();

        Arrays.sort(array);

        int max = 0;

        for(int i=0; i < num; i++) max = Math.max(max, array[i].getIndex() - i);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));

        pw.println(max + 1);

        pw.close();

    }

}

class Pair implements Comparable<Pair> {

    public final int value;
    public final int index;

    public Pair(int value, int index) {
        this.value = value;
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Pair pair) {

        if(value == pair.value) {
            return index - pair.index;
        } else {
            return value - pair.value;
        }

    }

    @Override
    public String toString() {
        return String.format("%d %d", value, index);
    }

}
