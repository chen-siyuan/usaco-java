import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int numTiles;
    public static int numBoots;
    public static int[] tiles;
    public static Boot[] boots;
    public static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("snowboots.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numTiles = Integer.parseInt(st.nextToken());
        numBoots = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        tiles = new int[numTiles];

        for(int i=0; i < numTiles; i++) tiles[i] = Integer.parseInt(st.nextToken());

        boots = new Boot[numBoots];

        for(int i=0; i < numBoots; i++) {
            st = new StringTokenizer(br.readLine());
            boots[i] = new Boot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        dp = new int[numTiles];
        dp[0] = 0;
        for(int i=1; i < numTiles; i++) dp[i] = numBoots;

        for(int i=1; i < numTiles; i++) for(int j=0; j < i; j++) for(int k=dp[j]; k < numBoots; k++)
            if(i - j <= boots[k].getStride() && Math.max(tiles[i], tiles[j]) <= boots[k].getDepth())
                dp[i] = Math.min(dp[i], k);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));

        pw.println(dp[numTiles - 1]);

        pw.close();

    }

}

class Boot {

    private final int depth;
    private final int stride;

    public Boot(int depth, int stride) {
        this.depth = depth;
        this.stride = stride;
    }

    public int getDepth() {
        return depth;
    }

    public int getStride() {
        return stride;
    }

}
