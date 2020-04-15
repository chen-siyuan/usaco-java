import java.io.*;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Integer> lengths;

    public static boolean solve(int num, int level) {
        if(num < 3) return num == 0;
        if(num >= lengths.get(level)) return solve(num, level + 1);
        if(num < lengths.get(level - 1)) return solve(num, level - 1);
        if(num < lengths.get(level - 1) + level + 3) return num == lengths.get(level - 1);
        return solve(num - lengths.get(level - 1) - level - 3, level - 1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("moo.in"));

        int index = Integer.parseInt(br.readLine());

        lengths = new ArrayList<>();
        int count = 0;
        int start = 3;

        lengths.add(3);
        while(start < index) {
            start = start*2 + ++count + 3;
            lengths.add(start);
        }

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moo.out")));

        pw.println(solve(index - 1, 0) ? "m" : "o");

        pw.close();

    }

}
