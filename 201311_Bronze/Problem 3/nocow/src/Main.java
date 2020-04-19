import java.io.*;
import java.util.*;

public class Main {

    public static int numLines;
    public static int index;
    public static int numAdjectives;
    public static ArrayList<String>[] adjectives;
    public static int[] weights;
    public static String[] inputStrings;
    public static int[] inputIndexes;

    public static int stringToIndex(String string) {

        StringTokenizer st = new StringTokenizer(string);
        if(st.countTokens() > numAdjectives) for(int i=0; i < 4; i++) st.nextToken();

        int index = 0;
        for(int i=0; i < numAdjectives; i++) index += weights[i] * adjectives[i].indexOf(st.nextToken());

        return index;
    }

    public static String indexToString(int index) {

        StringJoiner sj = new StringJoiner(" ");

        for(int i=0; i < numAdjectives; i++) {
            sj.add(adjectives[i].get(index / weights[i]));
            index %= weights[i];
        }

        return sj.toString();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("nocow.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numLines = Integer.parseInt(st.nextToken());
        index = Integer.parseInt(st.nextToken()) - 1;

        inputStrings = new String[numLines];
        inputStrings[0] = br.readLine();
        inputIndexes = new int[numLines];

        st = new StringTokenizer(inputStrings[0]);

        numAdjectives = st.countTokens() - 5;
        TreeSet<String>[] temp = new TreeSet[numAdjectives];
        for(int i=0; i < numAdjectives; i++) temp[i] = new TreeSet<>();

        for(int i=0; i < 4; i++) st.nextToken();

        for(int i=0; i < numAdjectives; i++) temp[i].add(st.nextToken());

        for(int i=0; i < numLines - 1; i++) {

            inputStrings[i + 1] = br.readLine();

            st = new StringTokenizer(inputStrings[i + 1]);

            for(int j=0; j < 4; j++) st.nextToken();

            for(int j=0; j < numAdjectives; j++) temp[j].add(st.nextToken());

        }

        br.close();

        adjectives = new ArrayList[numAdjectives];
        for(int i=0; i < numAdjectives; i++) adjectives[i] = new ArrayList<>(temp[i]);

        weights = new int[numAdjectives];
        for(int i=numAdjectives - 1; i >= 0; i--) weights[i] = (i == numAdjectives - 1 ? 1 : adjectives[i + 1].size() * weights[i + 1]);

        for(int i=0; i < numLines; i++) inputIndexes[i] = stringToIndex(inputStrings[i]);

        Arrays.sort(inputIndexes);

        int pointer = 0;
        while(pointer < numLines && inputIndexes[pointer] < index) {
            pointer++;
            index++;
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("nocow.out")));

        pw.println(indexToString(index));

        pw.close();

    }

}
