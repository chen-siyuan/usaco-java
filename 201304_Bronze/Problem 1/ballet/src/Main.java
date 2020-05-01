import java.io.*;
import java.util.HashMap;

public class Main {

    public static int numInstructions;
    public static int[][] positions;
    public static int[] boundaries;
    public static int direction;
    public static boolean flag;

    public static int StringToFootIndex(String string) {

        HashMap<String, Integer> mapping = new HashMap<>();

        mapping.put("FR", 0);
        mapping.put("FL", 1);
        mapping.put("RL", 2);
        mapping.put("RR", 3);

        return mapping.get(string);
    }

    public static int charToAbsoluteDirection(char relativeDirection) {

        HashMap<Character, Integer> mapping = new HashMap<>();

        mapping.put('P', -1 - direction);
        mapping.put('F', 0);
        mapping.put('L', 1);
        mapping.put('B', 2);
        mapping.put('R', 3);

        return (mapping.get(relativeDirection) + direction) % 4;
    }

    public static void move(int footIndex, int absoluteDirection) {

        if(absoluteDirection == -1) {

            for(int i=0; i < 4; i++) if(i != footIndex) {

                int deltaX = positions[i][0];
                int deltaY = positions[i][1];

                positions[i][0] = (deltaY - positions[footIndex][1]) + positions[footIndex][0];
                positions[i][1] = (positions[footIndex][0] - deltaX) + positions[footIndex][1];

            }

            direction = (direction + 3) % 4;

        } else {

            HashMap<Integer, int[]> mapping = new HashMap<>();

            mapping.put(0, new int[]{1, 0});
            mapping.put(1, new int[]{0, 1});
            mapping.put(2, new int[]{-1, 0});
            mapping.put(3, new int[]{0, -1});

            for(int i=0; i < 2; i++) positions[footIndex][i] += mapping.get(absoluteDirection)[i];

        }

    }

    public static boolean collides() {

        for(int i=0; i < 3; i++) for(int j=i + 1; j < 4; j++)
            if(positions[i][0] == positions[j][0] && positions[i][1] == positions[j][1])
                return true;

        return false;
    }

    public static void refreshBoundaries() {

        for(int i=0; i < 4; i++) {

            boundaries[0] = Math.min(boundaries[0], positions[i][0]);
            boundaries[1] = Math.min(boundaries[1], positions[i][1]);
            boundaries[2] = Math.max(boundaries[2], positions[i][0]);
            boundaries[3] = Math.max(boundaries[3], positions[i][1]);

        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("ballet.in"));

        numInstructions = Integer.parseInt(br.readLine());

        positions = new int[][]{new int[]{0, 0},
                new int[]{-1, 0},
                new int[]{-1, -1},
                new int[]{0, -1}};
        boundaries = new int[]{-1, -1, 0, 0};
        direction = 1;
        flag = false;

        for(int i=0; i < numInstructions; i++) {

            String instruction = br.readLine();

            move(StringToFootIndex(instruction.substring(0, 2)),
                    charToAbsoluteDirection(instruction.charAt(2)));

            flag = flag || collides();

            refreshBoundaries();

        }

        br.close();

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ballet.out")));

        pw.println(flag ? -1 :
                (boundaries[2] - boundaries[0] + 1) * (boundaries[3] - boundaries[1] + 1));

        pw.close();

    }

}
