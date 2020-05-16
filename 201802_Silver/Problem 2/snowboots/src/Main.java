import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static int numTiles;
    public static int numBoots;
    public static int[] tiles;
    public static Boot[] boots;
    public static int min;

    public static void search(int position, int index) {

        while(index < numBoots && boots[index].getDepth() < tiles[position]) index++;

        if(index == numBoots) return;

        if(position == numTiles - 1) min = Math.min(min, index);

        ArrayList<Integer> potentials = new ArrayList<>();

        potentials.add(Integer.MAX_VALUE);

        for(int pointer = Math.min(numTiles - 1, position + boots[index].getStride()); pointer > position; pointer--)
            if(tiles[pointer] <= Math.min(boots[index].getDepth(), potentials.get(0))) potentials.add(0, pointer);

        for(int potential: potentials) if(potential != Integer.MAX_VALUE) search(potential, index);

        search(position, index + 1);
    }

    public static void main(String[] args) throws IOException {

        // TRY DYNAMIC PROGRAMMING

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

        min = Integer.MAX_VALUE;

        search(0, 0);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));

        pw.println(min);

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
