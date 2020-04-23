import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numMirrors;
    public static Mirror[] mirrors;
    public static int sortCriterion; // [ -1  0  1  ] == [  X  T  Y  ]
    public static int width;
    public static int height;
    public static int[][] map;
    public static int startX;
    public static int startY;
    public static int endX;
    public static int endY;
    public static boolean flag; // to counter loops

    public static int solve() {

        sortCriterion = 0;
        Arrays.sort(mirrors);

        for(int i=0; i < numMirrors + 2; i++) {

            Mirror chosen = mirrors[i];

            inverseMirror(chosen.getX(), chosen.getY());

            flag = false;
            if(test(startX, startY, 0)) return chosen.getIndex();

            inverseMirror(chosen.getX(), chosen.getY());

        }

        return -1;
    }

    public static void inverseMirror(int x, int y) {
        if(map[x][y] % 2 != 0) map[x][y] *= -1;
    }

    public static int nextDirection(int x, int y, int thisDirection) {

        if(x < 0 || x >= width || y < 0 || y >= height) return 0;

        if(map[x][y] % 2 == 0) return thisDirection;

        if(map[x][y] == -1) {
            return thisDirection < 2 ? 1 - thisDirection : 5 - thisDirection;
        } else {
            return 3 - thisDirection;
        }

    }

    public static boolean test(int x, int y, int direction) {

        if(x < 0 || x >= width || y < 0 || y >= height) return false;
        if(x == startX && y == startY && direction == 0 && flag) return false;
        flag = true;

        if(x == endX && y == endY) return true;

        switch(direction) {
            case 0:
                return test(x + 1, y, nextDirection(x + 1, y, direction));
            case 1:
                return test(x, y + 1, nextDirection(x, y + 1, direction));
            case 2:
                return test(x - 1, y, nextDirection(x - 1, y, direction));
            case 3:
                return test(x, y - 1, nextDirection(x, y - 1, direction));
            default:
                return false;
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("mirrors.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numMirrors = Integer.parseInt(st.nextToken());

        mirrors = new Mirror[numMirrors + 2];
        mirrors[0] = new Mirror(0, 0, -2, 0);
        mirrors[numMirrors + 1] = new Mirror(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 2, numMirrors + 1);

        for(int i=0; i < numMirrors; i++) {
            st = new StringTokenizer(br.readLine());
            mirrors[i + 1] = new Mirror(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    "/".equals(st.nextToken()) ? -1 : 1,
                    i + 1);
        }

        br.close();

        // Reindex X

        sortCriterion = -1;
        Arrays.sort(mirrors);
        width = 0;

        int first = 0;
        while(first < numMirrors + 2) {

            int second = 0;
            while(first + second + 1 < numMirrors + 2
                    && mirrors[first + second + 1].getX() == mirrors[first + second].getX()) second++;

            for(int i=0; i < second + 1; i++) mirrors[i + first].setX(width);

            first = first + second + 1;
            width++;

        }

        // Reindex Y

        sortCriterion = 1;
        Arrays.sort(mirrors);
        height = 0;

        first = 0;
        while(first < numMirrors + 2) {

            int second = 0;
            while(first + second + 1 < numMirrors + 2
                    && mirrors[first + second + 1].getY() == mirrors[first + second].getY()) second++;

            for(int i=0; i < second + 1; i++) mirrors[i + first].setY(height);

            first = first + second + 1;
            height++;

        }

        // Construct map

        map = new int[width][height];

        for(Mirror mirror: mirrors) {

            map[mirror.getX()][mirror.getY()] = mirror.getType();

            if(mirror.getType() == -2) {
                startX = mirror.getX();
                startY = mirror.getY();
            }

            if(mirror.getType() == 2) {
                endX = mirror.getX();
                endY = mirror.getY();
            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mirrors.out")));

        pw.println(solve());

        pw.close();

    }

}

class Mirror implements Comparable<Mirror> {

    private int x;
    private int y;
    private final int type; // [-2 -1  0  1  2 ] == [ S  /  .  \  E ]
    private final int index;

    public Mirror(int x, int y, int type, int index) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getType() {
        return type;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public int compareTo(Mirror other) {

        switch(Main.sortCriterion) {
            case -1:
                return x - other.x;
            case 0:
                return index - other.index;
            case 1:
                return y - other.y;
            default:
                return 0;
        }

    }

    @Override
    public String toString() {
        return String.format("%d %d %d", x, y, type);
    }

}
