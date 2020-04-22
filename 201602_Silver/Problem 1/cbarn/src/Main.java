import java.io.*;
import java.util.Arrays;

public class Main {

    public static int numCows;
    public static int[] origins;
    public static int[] destinations;

    public static int distance(int origin, int destination) {
        return (origin <= destination) ? (destination - origin) : (destination + numCows - origin);
    }

    public static int totalEnergy(int shift) {

        int total = 0;
        for(int i=0; i < numCows; i++) total += Math.pow(distance(origins[i], destinations[(i + shift) % numCows]), 2);

        return total;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cbarn.in"));

        numCows = Integer.parseInt(br.readLine());

        origins = new int[numCows];
        int pointer = 0;
        for(int i=0; i < numCows; i++) {
            int temp = Integer.parseInt(br.readLine());
            for(int j=0; j < temp; j++) origins[pointer++] = i;
        }

        destinations = new int[numCows];
        for(int i=0; i < numCows; i++) destinations[i] = i;

        br.close();

        int min = Integer.MAX_VALUE;

        for(int i=0; i < numCows; i++) min = Math.min(min, totalEnergy(i));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cbarn.out")));

        pw.println(min);

        pw.close();

    }

}
