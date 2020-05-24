import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int dimension;
    public static int[][] map;
    public static int color;
    public static int count;
    public static boolean[][] searched;
    public static boolean flag;
    public static int singleMax;
    public static int doubleMax;
    public static HashMap<HashSet<Integer>, Integer> pairs;
    public static HashSet<Integer> pair;
    public static HashMap<Integer, Integer> record;

    public static boolean check(int x, int y) {

        if(flag) {
            return x >= 0 && y >= 0 && x < dimension && y < dimension && pair.contains(map[x][y]);
        } else {
            return x >= 0 && y >= 0 && x < dimension && y < dimension && map[x][y] == color;
        }

    }

    public static void search(int x, int y) {

        if(searched[x][y]) return;
        searched[x][y] = true;

        count++;

        if(check(x + 1, y)) search(x + 1, y);
        if(check(x - 1, y)) search(x - 1, y);
        if(check(x, y + 1)) search(x, y + 1);
        if(check(x, y - 1)) search(x, y - 1);

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("multimoo.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        dimension = Integer.parseInt(st.nextToken());

        map = new int[dimension][dimension];
        pairs = new HashMap<>();

        flag = false;

        for(int i=0; i < dimension; i++) {

            st = new StringTokenizer(br.readLine());

            for(int j=0; j < dimension; j++) {

                map[i][j] = Integer.parseInt(st.nextToken());

                if(i != 0 && map[i][j] != map[i - 1][j]) {

                    HashSet<Integer> temp = new HashSet<>();

                    temp.add(map[i][j]);
                    temp.add(map[i - 1][j]);

                    if(!pairs.containsKey(temp)) pairs.put(temp, 0);

                    pairs.put(temp, pairs.get(temp) + 1);

                }

                if(j != 0 && map[i][j] != map[i][j - 1]) {

                    HashSet<Integer> temp = new HashSet<>();

                    temp.add(map[i][j]);
                    temp.add(map[i][j - 1]);

                    if(!pairs.containsKey(temp)) pairs.put(temp, 0);

                    pairs.put(temp, pairs.get(temp) + 1);

                }

            }

        }

        br.close();

        searched = new boolean[dimension][dimension];
        singleMax = 0;
        record = new HashMap<>();

        for(int i=0; i < dimension; i++) for(int j=0; j < dimension; j++) {

            color = map[i][j];
            count = 0;

            search(i, j);

            record.put(color, count);

            singleMax = Math.max(singleMax, count);

        }

        flag = true;

        for(HashSet<Integer> temp: pairs.keySet()) {

            int sum = 0;

            for(int value: temp) sum += record.get(value);

            if(pairs.get(temp) + sum <= doubleMax) continue;

            pair = temp;

            searched = new boolean[dimension][dimension];

            for(int i=0; i < dimension; i++) for(int j=0; j < dimension; j++)
                if(temp.contains(map[i][j])) {

                    count = 0;

                    search(i, j);

                    doubleMax = Math.max(doubleMax, count);

                }

        }

//        System.out.println(doubleMax);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("multimoo.out")));

        pw.println(singleMax);
        pw.println(doubleMax);

        pw.close();

    }

}
