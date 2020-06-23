import java.util.*;
import java.io.*;

public class Main {

    public static int n;
    public static Cow[] cows;
    public static int curr;
    public static PriorityQueue<Cow> wl;
    public static int max;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("convention2.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        cows = new Cow[n];

        for(int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new Cow(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        Arrays.sort(cows, (Cow cow1, Cow cow2) -> (cow1.pos == cow2.pos ? cow1.ord - cow2.ord : cow1.pos - cow2.pos));

        curr = cows[0].pos + cows[0].time;
        wl = new PriorityQueue<>((Cow cow1, Cow cow2) -> (cow1.ord - cow2.ord));

        int pointer = 1;
        max = Integer.MIN_VALUE;

        while(true) {
        
            while(pointer < n && cows[pointer].pos <= curr) wl.offer(cows[pointer++]);

            if(wl.size() == 0) {
                if(pointer == n) break;
                curr = cows[pointer].pos + cows[pointer++].time;
            } else {
                max = Math.max(max, curr - wl.peek().pos);
                curr += wl.poll().time;
            }
        
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("convention2.out")));

        pw.println(max);

        pw.close();

    }

}

class Cow {

    public final int ord;
    public final int pos;
    public final int time;
    
    public Cow(int _ord, int _pos, int _time) {
        ord = _ord;
        pos = _pos;
        time = _time;
    }

    public String toString() {
        return String.format("%d %d %d", ord, pos, time);
    }

}
