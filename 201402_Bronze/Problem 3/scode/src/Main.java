import java.io.*;

public class Main {

    public static String raw;

    public static int count(String string) {

        if(string.length() % 2 == 0 || string.length() == 1) return 1;

        int count = 1;
        int length = string.length();

        if(string.substring(0, length / 2).equals(string.substring(length / 2 + 1))) {
            count += count(string.substring(length / 2));
            count += count(string.substring(0, length / 2 + 1));
        }

        if(string.substring(0, length / 2).equals(string.substring(length / 2, length - 1)))
            count += count(string.substring(length / 2));

        if(string.substring(1, length / 2 + 1).equals(string.substring(length / 2 + 1)))
            count += count(string.substring(0, length / 2 + 1));

        return count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("scode.in"));

        raw = br.readLine();

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("scode.out")));

        pw.println(count(raw) - 1);

        pw.close();

    }

}
