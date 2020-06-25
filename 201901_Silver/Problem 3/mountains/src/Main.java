import java.io.*;
import java.util.*;

public class Main {

    public static int n, res, max;
    public static Mountain[] mountains;
    public static boolean[] obscured;

    public static void main(String[] args) throws IOException {
    
        BufferedReader br = new BufferedReader(new FileReader("mountains.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        mountains = new Mountain[n];

        for(int i=0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            mountains[i] = new Mountain(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        Arrays.sort(mountains, (Mountain m1, Mountain m2) -> (m1.x - m2.x));

        obscured = new boolean[n];

        max = 0;

        for(int i=0; i < n; i++) {

            if(max >= mountains[i].x + mountains[i].y) {
                obscured[i] = true;
            } else {
                max = mountains[i].x + mountains[i].y;
            }
        
        }
        
        max = Integer.MIN_VALUE;

        for(int i=n-1; i >= 0; i--) {
        
            if(max >= mountains[i].y - mountains[i].x) {
                obscured[i] = true;
            } else {
                max = mountains[i].y - mountains[i].x;
            }
        }
        
        res = 0;
        for(int i=0; i < n; i++) if(!obscured[i]) res++;

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mountains.out")));

        pw.println(res);

        pw.close();
    
    }

}

class Mountain {

    public final int x;
    public final int y;

    public Mountain(int _x, int _y) {
        x = _x;
        y = _y;
    }

}
