import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Main {

    public static HashSet<Integer> bisquaresSet;
    public static ArrayList<Integer> bisquaresList;
    public static int numTerms;

    public static boolean isValid(int start, int difference, int totalTerms) {
        return (totalTerms == 0) || (bisquaresSet.contains(start) && isValid(start + difference, difference, totalTerms - 1));
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));

        numTerms = Integer.parseInt(br.readLine());
        int limit = Integer.parseInt(br.readLine());

        br.close();

        bisquaresSet = new HashSet<>();
        for(int i=0; i < limit + 1; i++) {
            for(int j=i; j < limit + 1; j++) {
                bisquaresSet.add((int)(Math.pow(i, 2) + Math.pow(j, 2)));
            }
        }

        bisquaresList = new ArrayList<>(bisquaresSet);
        Collections.sort(bisquaresList);

        int maxDifference = limit * limit * 2 / (numTerms - 1);
        ArrayList<Pair> answers = new ArrayList<>();

        for(int i=0; i <= bisquaresList.size() - numTerms; i++) {
            for(int j=i+1; j <= bisquaresList.size() - numTerms + 1; j++) {

                int x = bisquaresList.get(i);
                int y = bisquaresList.get(j);

                if(y - x <= maxDifference && isValid(y, y - x, numTerms - 1)) answers.add(new Pair(x, y - x));
            }

        }

        Collections.sort(answers);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

        if(answers.size() == 0) {
            pw.println("NONE");
        } else {
            for(Pair p: answers) {
                pw.println(p);
            }
        }

        pw.close();

    }

}

class Pair implements Comparable<Pair> {

    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public int compareTo(Pair other) {

        if(this.y == other.y) {
            return this.x - other.x;
        } else {
            return this.y - other.y;
        }

    }

    @Override
    public String toString() {
        return String.format("%d %d", x, y);
    }

}
