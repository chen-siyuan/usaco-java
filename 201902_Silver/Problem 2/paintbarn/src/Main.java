import java.io.*;
import java.util.*;

public class Main {

    public static int n, k, count, prev, res;
    public static Side[] sides;
    public static int[] record;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        sides = new Side[n * 2];

        for(int i=0; i < n; i++) {

            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sides[i * 2] = new Side(x1, y1, y2, true);
            sides[i * 2 + 1] = new Side(x2, y1, y2, false);

        }

        br.close();

        Arrays.sort(sides);

        count = 0;
        prev = 0;
        res = 0;
        record = new int[1000];

        for(Side side: sides) {

            res += count * (side.pos - prev);
            prev = side.pos;

            for(int i=side.head; i < side.tail; i++) {
            
                if(record[i] == k) count--;
                record[i] += side.type ? 1 : -1;
                if(record[i] == k) count++;
            
            }
        
        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("paintbarn.out")));

        pw.println(res);

        pw.close();
    
    }

}

class Side implements Comparable<Side> {

    public final int pos;
    public final int head, tail;
    public final boolean type;

    public Side(int _pos, int _head, int _tail, boolean _type) {
    
        pos = _pos;
        head = _head;
        tail = _tail;
        type = _type;
    
    }

    public int compareTo(Side other) {
    
        if(pos != other.pos) return pos - other.pos;
        if(type != other.type) return type ? -1 : 1;
        if(head != other.head) return head - other.head;
        return tail - other.tail;
    
    }

}
