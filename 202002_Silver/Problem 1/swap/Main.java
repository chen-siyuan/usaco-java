import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {
        
        int n = 0, m = 0, k = 0;
        int[] l = null;

        try(BufferedReader br = new BufferedReader(new FileReader("swap.in"))) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            l = new int[n];
            for(int i=0; i < n; i++) l[i] = i;

            for(int i=0; i < m; i++) {

                st = new StringTokenizer(br.readLine());

                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;

                for(int j=s; j < (s + e + 1) / 2; j++) {
                    int temp = l[j];
                    l[j] = l[s + e - j];
                    l[s + e - j] = temp;
                }

            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        int[] mod = new int[n];

        for(int i=0; i < n; i++) if(mod[i] == 0) {

            Set<Integer> memo = new HashSet<>();

            int temp = i;
            while(!memo.contains(temp)) {
                memo.add(temp);
                temp = l[temp];
            }

            for(int j: memo) mod[j] = memo.size();

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")))) {

            for(int i=0; i < n; i++) {

                int temp = i;
                for(int j=0; j < k % mod[i]; j++) temp = l[temp];

                pw.println(temp + 1);

            }
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
