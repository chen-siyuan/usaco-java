import java.io.*;
import java.util.*;

public class Test {
    
    public static int n;
    public static List<Integer>[] al;
    public static int[] dp;
    public static Queue<Integer> queue;

    public static int count(int curr) {
    
        int res = 0;
        for(int i=0; i < n; i++) if((curr & (1 << i)) != 0) for(int neighbor: al[i]) if((curr & (1 << neighbor)) == 0) res++; 

        return res;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("haywire.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        al = new ArrayList[n];

        for(int i=0; i < n; i++) {

            al[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());

            for(int j=0; j < 3; j++) al[i].add(Integer.parseInt(st.nextToken()) - 1);
        
        }

        br.close();

        dp = new int[1 << n];
        for(int i=0; i < dp.length; i++) dp[i] = Integer.MAX_VALUE;
        dp[0] = 0;

        queue = new LinkedList<Integer>();
        queue.offer(0);

        while(queue.size() != 0) {
        
            int curr = queue.poll();

            for(int i=0; i < n; i++) if((curr & (1 << i)) == 0) {

                int temp = curr + (1 << i);

                dp[temp] = Math.min(dp[temp], dp[curr] + count(curr));
                queue.offer(temp);
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haywire.out")));

        pw.println(dp[dp.length - 1]);

        pw.close();

    }

}
