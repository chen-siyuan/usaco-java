import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    public static int numLines;
    public static HashMap<String, Integer> records;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("records.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numLines = Integer.parseInt(st.nextToken());
        records = new HashMap<>();

        for(int i=0; i < numLines; i++) {

            st = new StringTokenizer(br.readLine());

            String[] temp = new String[3];
            for(int j=0; j < 3; j++) temp[j] = st.nextToken();
            Arrays.sort(temp);

            StringJoiner sj = new StringJoiner(" ");
            for(int j=0; j < 3; j++) sj.add(temp[j]);

            if(!records.containsKey(sj.toString())) records.put(sj.toString(), 0);
            records.put(sj.toString(), records.get(sj.toString()) + 1);

        }

        br.close();

        int max = Integer.MIN_VALUE;

        for(String key: records.keySet()) max = Math.max(max, records.get(key));

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("records.out")));

        pw.println(max);

        pw.close();

    }

}
