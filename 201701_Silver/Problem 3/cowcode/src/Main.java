import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int length;
    public static long index;

    public static long getLength(int level) {
        return (long)Math.pow(2, level) * length;
    }

    public static int solve(long num, int level) {
        if(num < length - 1) return (int)num;
        if(num >= getLength(level) - 1) return solve(num, level + 1);
        if(num < getLength(level - 1) - 1) return solve(num, level - 1);
        if(num < getLength(level - 1) + 1) return (length - (level % length)) % length;
        return solve(num - getLength(level - 1) - 1, level - 1);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cowcode.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String source = st.nextToken();
        length = source.length();
        index = Long.parseLong(st.nextToken()) - 1;

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));

        pw.println(source.charAt(solve(index, 0)));

        pw.close();

    }

}
