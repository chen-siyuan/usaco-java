import java.io.*;
import java.util.Arrays;

public class Main {

    public static int numCows;
    public static int[] cows;

    public static int searchHead(int num, int head, int tail) {

        if(num > cows[numCows - 1]) return numCows;
        if(head + 1 == tail) return tail;
        int mid = (head + tail) / 2;

        return num <= cows[mid] ? searchHead(num, head, mid) : searchHead(num, mid, tail);
    }

    public static int searchTail(int num, int head, int tail) {

        if(num < cows[0]) return -1;
        if(head + 1 == tail) return head;
        int mid = (head + tail)  / 2;

        return num < cows[mid] ? searchTail(num, head, mid) : searchTail(num, mid, tail);
    }

    public static int cowsBetween(int first, int second) {
        return searchTail(second * 3 - first * 2, -1, numCows)
                - searchHead(second * 2 - first, -1, numCows) + 1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("baseball.in"));

        numCows = Integer.parseInt(br.readLine());
        cows = new int[numCows];

        for(int i=0; i < numCows; i++) cows[i] = Integer.parseInt(br.readLine());

        br.close();

        Arrays.sort(cows);

        int total = 0;

        for(int i=0; i < numCows - 1; i++) for(int j=i + 1; j < numCows; j++) total += cowsBetween(cows[i], cows[j]);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("baseball.out")));

        pw.println(total);

        pw.close();

    }

}
