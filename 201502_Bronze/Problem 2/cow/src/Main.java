import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cow.in"));

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cow.out")));

        pw.close();

    }

}
