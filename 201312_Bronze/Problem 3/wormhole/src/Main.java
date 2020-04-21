import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numHoles;
    public static Hole[] holes;
    public static int[] directedPaths;
    public static int[] pairings;
    public static ArrayList<Integer> unpairedHoles;
    public static boolean[] searched;
    public static int count;

    public static int getPairedHole(int hole) {

        for(int i=0; i < numHoles / 2; i++) {
            if(pairings[i * 2] == hole) return pairings[i * 2 + 1];
            if(pairings[i * 2 + 1] == hole) return pairings[i * 2];
        }

        return -1;
    }

    public static boolean testEntrance(int hole, boolean enteringHole) {

        if(enteringHole) {
            return testEntrance(getPairedHole(hole), false);
        } else {

            int nextHole = directedPaths[hole];
            if(nextHole == -1) return true;

            if(searched[nextHole]) return false;
            searched[nextHole] = true;

            return testEntrance(nextHole, true);
        }

    }

    public static boolean testPairings() {

        for(int i=0; i < numHoles; i++) {
            for(int j=0; j < numHoles; j++) searched[j] = false;
            if(!testEntrance(i, true)) return false;
        }

        return true;
    }

    public static void generatePairings(int pointer) {

        if(pointer == numHoles) {
            if(!testPairings()) count++;
            return;
        }

        pairings[pointer] = unpairedHoles.remove(0);

        for(int i = 0; i < unpairedHoles.size(); i++) {

            pairings[pointer + 1] = unpairedHoles.remove(i);
            generatePairings(pointer + 2);
            unpairedHoles.add(i, pairings[pointer + 1]);

        }

        unpairedHoles.add(0, pairings[pointer]);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("wormhole.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numHoles = Integer.parseInt(st.nextToken());

        holes = new Hole[numHoles];
        directedPaths = new int[numHoles];
        for(int i=0; i < numHoles; i++) directedPaths[i] = -1;
        pairings = new int[numHoles];
        unpairedHoles = new ArrayList<>();
        for(int i=0; i < numHoles; i++) unpairedHoles.add(i);
        searched = new boolean[numHoles];
        count = 0;

        for(int i=0; i < numHoles; i++) {
            st = new StringTokenizer(br.readLine());
            holes[i] = new Hole(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        Arrays.sort(holes);

        for(int i=0; i < numHoles - 1; i++) if(holes[i].getY() == holes[i + 1].getY()) directedPaths[i] = i + 1;

        generatePairings(0);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

        pw.println(count);

        pw.close();

    }

}

class Hole implements Comparable<Hole> {

    private final int x;
    private final int y;

    public Hole(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Hole other) {
        if(y == other.y) {
            return x - other.x;
        } else {
            return y - other.y;
        }
    }

    @Override
    public String toString() {
        return String.format("%d %d", x, y);
    }

}
