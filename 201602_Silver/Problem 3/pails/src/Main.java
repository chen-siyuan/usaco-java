import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    public static int maxX;
    public static int maxY;
    public static int maxAge;
    public static int targetVolume;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("pails.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        maxX = Integer.parseInt(st.nextToken());
        maxY = Integer.parseInt(st.nextToken());
        maxAge = Integer.parseInt(st.nextToken());
        targetVolume = Integer.parseInt(st.nextToken());

        br.close();

        ArrayList<State> states = new ArrayList<>();
        states.add(new State(0, 0, 0));

        HashSet<State> searched = new HashSet<>();

        int min = Integer.MAX_VALUE;

        while(states.size() > 0) {

            State current = states.remove(0);

            if(searched.contains(current) || current.getAge() > maxAge) {continue;}
            searched.add(current);

            min = Math.min(min, current.getDistance());

            for(int i=0; i < 6; i++) {states.add(current.generate(i));}

        }
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));

        pw.println(min);

        pw.close();

    }

}

class State {

    private final int x;
    private final int y;
    private final int age;

    public State(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    public int getAge() {return age;}

    public int getDistance() {return Math.abs(x + y - Main.targetVolume);}

    public State generate(int type) {

        // fill X
        if(type == 0) return new State(Main.maxX, y, age + 1);
        // fill Y
        if(type == 1) return new State(x, Main.maxY, age + 1);
        // empty x
        if(type == 2) return new State(0, y, age + 1);
        // empty y
        if(type == 3) return new State(x, 0, age + 1);
        // x to y
        if(type == 4) return new State(x - Math.min(Main.maxY - y, x), y + Math.min(Main.maxY - y, x), age + 1);
        // y to x
        if(type == 5) return new State(x + Math.min(Main.maxX - x, y), y - Math.min(Main.maxX - x, y), age + 1);

        return null;
    }

    @Override
    public String toString() {return String.format("%d %d", x, y);}

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        State state = (State) object;
        return x == state.x && y == state.y;
    }

    @Override
    public int hashCode() {return Objects.hash(x, y);}

}
