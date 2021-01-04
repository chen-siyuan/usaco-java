import java.io.*;
import java.util.*;

public class Main {
    
    public static int n, count;
    public static List<Integer>[] al;
    public static SortedSet<Integer> partition;
    public static int[][] mappings;
    public static int[] mapping;
    public static Set<Integer> cache;

    public static void generate(int index) {

        if(index == n / 2) {
            mappings[count++] = mapping;
            return;
        }


    

    
    
    }

    public static void search() {

        if(partition.size() == n / 2) {



            
            
            return;
        }

        for(int i=(partition.isEmpty() ? 0 : partition.last() + 1); i < n; i++) {

            partition.add(i);
            search();
            partition.remove(i);

        }

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

        n = 4;
        partition = new TreeSet<>();

        search();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haywire.out")));

        pw.close();

    }

}
