import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cow.in"));

        int length = Integer.parseInt(br.readLine());

        char[] letters = br.readLine().toCharArray();

        br.close();

        int cCount = 0;
        int wCount = 0;

        for(int i=0; i < length; i++) if(letters[i] == 'W') wCount++;

        long total = 0;

        for(int i=0; i < length; i++) switch(letters[i]) {
            case 'C':
                cCount++; break;
            case 'O':
                total += cCount * wCount; break;
            case 'W':
                wCount--; break;
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cow.out")));

        pw.println(total);

        pw.close();

    }

}
