import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int origin;
    public static int destination;
    public static int numRoutes;
    public static int min;
    public static ArrayList<Integer> costs;
    public static ArrayList<ArrayList<Integer>> routes;
    public static int numRoutesConsidering;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("cowroute.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        origin = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        numRoutes = Integer.parseInt(st.nextToken());

        min = Integer.MAX_VALUE;

        numRoutesConsidering = 0;
        routes = new ArrayList<>();
        costs = new ArrayList<>();

        for(int i=0; i < numRoutes; i++) {

            boolean starts = false;
            boolean considered = false;

            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());
            int numCities = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            ArrayList<Integer> route = new ArrayList<>();

            for(int j=0; j < numCities; j++) {

                int city = Integer.parseInt(st.nextToken());

                route.add(city);

                if(city == origin) starts = true;

                if(starts && city == destination) {
                    considered = true;
                    min = Math.min(min, cost);
                }

            }

            if(!considered) {
                numRoutesConsidering++;
                routes.add(route);
                costs.add(cost);
            }

        }

        br.close();

        for(int i=0; i < numRoutesConsidering; i++) for(int j=0; j < numRoutesConsidering; j++) if(i != j) {

            ArrayList<Integer> firstRoute = routes.get(i);

            int pointer = 0;

            while(pointer < firstRoute.size() && firstRoute.get(pointer) != origin) pointer++;

            if(pointer == firstRoute.size()) continue;

            HashSet<Integer> transferCities = new HashSet<>();

            while(pointer + 1 < firstRoute.size()) transferCities.add(firstRoute.get(++pointer));

            boolean starts = false;

            ArrayList<Integer> secondRoute = routes.get(j);

            for(int city: secondRoute) {
                if(transferCities.contains(city)) starts = true;
                if(starts && city == destination) min = Math.min(min, costs.get(i) + costs.get(j));
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cowroute.out")));

        pw.println(min == Integer.MAX_VALUE ? -1 : min);

        pw.close();

    }

}
