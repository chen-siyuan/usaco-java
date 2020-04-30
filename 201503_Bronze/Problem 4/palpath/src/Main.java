import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("palpath.in"));

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palpath.out")));

        pw.close();

    }

}
