import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numWords;
    public static int numQueries;
    public static Word[] words;

    public static int startIndex(String prefix, int head, int tail) {

        if(prefix.compareTo(words[numWords - 1].getContent()) > 0) return numWords;

        if(head + 1 == tail) return tail;

        int mid = (head + tail) / 2;

        if(prefix.compareTo(words[mid].getContent()) <= 0) {
            return startIndex(prefix, head, mid);
        } else {
            return startIndex(prefix, mid, tail);
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("auto.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numWords = Integer.parseInt(st.nextToken());
        numQueries = Integer.parseInt(st.nextToken());

        words = new Word[numWords];

        for(int i=0; i < numWords; i++) words[i] = new Word(br.readLine(), i);

        Arrays.sort(words);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));

        for(int i=0; i < numQueries; i++) {

            st = new StringTokenizer(br.readLine());

            int index = Integer.parseInt(st.nextToken()) - 1;
            String prefix = st.nextToken();

            int startIndex = startIndex(prefix, -1, numWords - 1);

            if(startIndex == numWords) {
                pw.println(-1);
            } else {

                if(startIndex + index < numWords && words[startIndex + index].getContent().startsWith(prefix)) {
                    pw.println(words[startIndex + index].getIndex() + 1);
                } else {
                    pw.println(-1);
                }

            }

        }

        br.close();

        pw.close();

    }

}

class Word implements Comparable<Word> {

    private final String content;
    private final int index;

    public Word(String content, int index) {
        this.content = content;
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public int compareTo(Word word) {
        return content.compareTo(word.getContent());
    }

}
