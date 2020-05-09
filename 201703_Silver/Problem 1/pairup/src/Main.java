import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numEntries;
    public static Entry[] entries;
    public static int sum;
    public static int headPointer;
    public static int tailPointer;
    public static int headAmount;
    public static int tailAmount;
    public static int max;

    public static void search() {

        int increase = Math.min(entries[headPointer].getAmount() - headAmount,
                entries[tailPointer - 1].getAmount() - tailAmount);

        headAmount += increase;
        tailAmount += increase;

        max = Math.max(max, entries[headPointer].getValue() + entries[tailPointer - 1].getValue());

        if(headAmount == entries[headPointer].getAmount()) {
            headAmount = 0;
            headPointer++;
        }

        if(tailAmount == entries[tailPointer - 1].getAmount()) {
            tailAmount = 0;
            tailPointer--;
        }

        if(headPointer == tailPointer - 1) {

            if(headAmount + tailAmount != entries[headPointer].getAmount())
                max = Math.max(max, entries[headPointer].getValue() + entries[tailPointer - 1].getValue());

        } else {
            search();
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("pairup.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numEntries = Integer.parseInt(st.nextToken());

        entries = new Entry[numEntries];

        for(int i=0; i < numEntries; i++) {

            st = new StringTokenizer(br.readLine());
            entries[i] = new Entry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sum += entries[i].getAmount();

        }

        br.close();

        Arrays.sort(entries);

        headPointer = 0;
        tailPointer = numEntries;
        headAmount = 0;
        tailAmount = 0;

        max = Integer.MIN_VALUE;

        search();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));

        pw.println(max);

        pw.close();

    }

}

class Entry implements Comparable<Entry> {

    public final int amount;
    public final int value;

    public Entry(int amount, int value) {
        this.amount = amount;
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Entry entry) {
        return value - entry.value;
    }

    @Override
    public String toString() {
        return String.format("%d %d", amount, value);
    }

}
