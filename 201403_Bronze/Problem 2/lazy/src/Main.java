import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numPiles;
    public static int reach;
    public static Pile[] piles;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("lazy.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numPiles = Integer.parseInt(st.nextToken());
        reach = Integer.parseInt(st.nextToken());

        piles = new Pile[numPiles];

        for(int i=0; i < numPiles; i++){
            st = new StringTokenizer(br.readLine());
            piles[i] = new Pile(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        br.close();

        Arrays.sort(piles);

        int j= -1;
        int total = 0;

        while(j + 1 < numPiles && piles[0].getPosition() + 2 * reach >= piles[j + 1].getPosition())
            total += piles[++j].getAmount();

        int max = total;

        for(int i=1; i < numPiles; i++) {

            total -= piles[i - 1].getAmount();

            while(j + 1 < numPiles && piles[i].getPosition() + 2 * reach >= piles[j + 1].getPosition())
                total += piles[++j].getAmount();

            max = Math.max(max, total);

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("lazy.out")));

        pw.println(max);

        pw.close();

    }

}

class Pile implements Comparable<Pile> {

    private final int position;
    private final int amount;

    public Pile(int amount, int position) {
        this.position = position;
        this.amount = amount;
    }

    public int getPosition() {
        return position;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Pile pile) {
        return position - pile.position;
    }

    @Override
    public String toString() {
        return String.format("%d %d", position, amount);
    }

}
