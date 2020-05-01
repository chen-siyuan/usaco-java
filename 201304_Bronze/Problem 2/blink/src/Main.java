import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static int numDigits;
    public static long numSteps;
    public static HashMap<Integer, Integer> searched;
    public static boolean[] raw;

    public static int digitsToNumber(boolean[] digits) {

        int result = 0;

        for(int i=0; i < numDigits; i++) if(digits[i]) result += Math.pow(2, numDigits - 1 - i);

        return result;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("blink.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numDigits = Integer.parseInt(st.nextToken());
        numSteps = Long.parseLong(st.nextToken());

        raw = new boolean[numDigits];

        for(int i=0; i < numDigits; i++) raw[i] = "1".equals(br.readLine());

        boolean[] test = raw.clone();

        br.close();

        searched = new HashMap<>();
        int count = 0;

        while(!searched.containsKey(digitsToNumber(test))) {

            searched.put(digitsToNumber(test), count++);

            boolean[] temp = test.clone();

            for(int i=0; i < numDigits; i++) test[i] = temp[i] ^ temp[(i + numDigits - 1) % numDigits];

        }

        numSteps = (numSteps - count) % (count - searched.get(digitsToNumber(test))) + count;

        for(int i=0; i < numSteps; i++) {
            boolean[] temp = raw.clone();
            for(int j=0; j < numDigits; j++) raw[j] = temp[j] ^ temp[(j + numDigits - 1) % numDigits];
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("blink.out")));

        for(int i=0; i < numDigits; i++) pw.println(raw[i] ? "1" : "0");

        pw.close();

    }

}
