import java.io.*;

public class Main {

    public static String raw;
    public static String trigger;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("censor.in"));

        raw = br.readLine();
        trigger = br.readLine();

        StringBuilder result = new StringBuilder();
        StringBuilder buffer = new StringBuilder();

        for(int i=0; i < raw.length(); i++) {

            buffer.append(raw.charAt(i));

            if(buffer.length() < trigger.length()) continue;

            if(trigger.equals(buffer.toString())) {

                buffer = new StringBuilder();

                int temp = result.length();

                for(int j=0; j < Math.min(temp, trigger.length() - 1); j++) {
                    buffer.insert(0, result.charAt(result.length() - 1));
                    result.deleteCharAt(result.length() - 1);
                }

            } else {
                result.append(buffer.charAt(0));
                buffer.deleteCharAt(0);
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("censor.out")));

        pw.print(result.toString());
        pw.println(buffer.toString());

        pw.close();

    }

}
