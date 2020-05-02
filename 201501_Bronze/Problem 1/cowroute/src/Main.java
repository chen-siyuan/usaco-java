import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static int origin;
    public static int destination;
    public static boolean departed;
    public static int numRoutes;
    public static int min;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cowroute.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        origin = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        numRoutes = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;

        for(int i=0; i < numRoutes; i++) {

            departed = false;

            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int numCities = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            for(int j=0; j < numCities; j++) {

                int city = Integer.parseInt(st.nextToken());

                if(city == origin) departed = true;
                if(departed && city == destination) min = Math.min(min, cost);

            }

        }

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));

        pw.println(min == Integer.MAX_VALUE ? -1 : min);

        pw.close();

    }

}
