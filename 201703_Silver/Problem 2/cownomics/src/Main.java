import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int numDigits;
    public static int[][] cows;

    public static int transform(int a, int b, int c) {
        return a + b * 4 + c * 16;
    }

    public static boolean check(int[] combination) {

        HashSet<Integer> spotted = new HashSet<>();

        for(int i=0; i < numCows; i++)
            spotted.add(transform(cows[i][combination[0]], cows[i][combination[1]], cows[i][combination[2]]));

        for(int i=numCows; i < numCows * 2; i++)
            if(spotted.contains(transform(cows[i][combination[0]], cows[i][combination[1]], cows[i][combination[2]])))
                return false;

        return true;
    }

    public static int[][] generate() {

        int[][] result = new int[numDigits * (numDigits - 1) * (numDigits - 2) / 6][3];
        int pointer = 0;

        for(int i=0; i < numDigits - 2; i++) for(int j=i + 1; j < numDigits - 1; j++) for(int k=j + 1; k < numDigits; k++) {

                result[pointer][0] = i;
                result[pointer][1] = j;
                result[pointer][2] = k;

                pointer++;

        }

        return result;
    }

    public static int convert(char gene) {

        switch(gene) {
            case 'A':
                return 0;
            case 'T':
                return 1;
            case 'C':
                return 2;
            case 'G':
                return 3;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        numDigits = Integer.parseInt(st.nextToken());

        cows = new int[numCows * 2][numDigits];

        for(int i=0; i < numCows * 2; i++) {
            String temp = br.readLine();
            for(int j=0; j < numDigits; j++) cows[i][j] = convert(temp.charAt(j));
        }

        br.close();

        int count = 0;

        for(int[] combination: generate()) if(check(combination)) count++;

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));

        pw.println(count);

        pw.close();

    }

}
