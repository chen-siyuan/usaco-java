/*
ID: daniel26
LANG: JAVA
TASK: milk3
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static int[] sizeBuckets;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("milk3.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        sizeBuckets = new int[]{Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())};

        br.close();

        HashSet<State> searched = new HashSet<>();
        ArrayList<State> queue = new ArrayList<>();

        queue.add(new State(0, 0, sizeBuckets[2]));

        while(queue.size() > 0) {

            State state = queue.remove(0);

            if(searched.contains(state)) continue;

            searched.add(state);

            if(!searched.contains(state.pourAtoB())) queue.add(state.pourAtoB());
            if(!searched.contains(state.pourAtoC())) queue.add(state.pourAtoC());
            if(!searched.contains(state.pourBtoA())) queue.add(state.pourBtoA());
            if(!searched.contains(state.pourBtoC())) queue.add(state.pourBtoC());
            if(!searched.contains(state.pourCtoA())) queue.add(state.pourCtoA());
            if(!searched.contains(state.pourCtoB())) queue.add(state.pourCtoB());

        }

        ArrayList<Integer> answers = new ArrayList<>();

        for(State state: searched) {
            if(state.getA() == 0) answers.add(state.getC());
        }

        Collections.sort(answers);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));

        for(int i=0; i < answers.size() - 1; i++) {
            pw.print(answers.get(i));
            pw.print(" ");
        }

        pw.println(answers.get(answers.size() - 1));

        pw.close();

    }

}

class State {

    private int a;
    private int b;
    private int c;

    public State(int a, int b, int c) {
        
        this.a = a;
        this.b = b;
        this.c = c;
        
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public State pourAtoB() {

        if(a + b > Main.sizeBuckets[1]) {
            return new State(a + b - Main.sizeBuckets[1], Main.sizeBuckets[1], c);
        } else {
            return new State(0, a + b, c);
        }

    }

    public State pourAtoC() {
        return new State(0, b, a + c);
    }

    public State pourBtoA() {

        if(a + b > Main.sizeBuckets[0]) {
            return new State(Main.sizeBuckets[0], a + b - Main.sizeBuckets[0], c);
        } else {
            return new State(a + b, 0, c);
        }

    }

    public State pourBtoC() {
        return new State(a, 0, b + c);
    }

    public State pourCtoA() {

        if(a + c > Main.sizeBuckets[0]) {
            return new State(Main.sizeBuckets[0], b, a + c - Main.sizeBuckets[0]);
        } else {
            return new State(a + c, b, 0);
        }

    }

    public State pourCtoB() {

        if(b + c > Main.sizeBuckets[1]) {
            return new State(a, Main.sizeBuckets[1], b + c - Main.sizeBuckets[1]);
        } else {
            return new State(a, b + c, 0);
        }

    }

    @Override
    public boolean equals(Object object) {

        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        State other = (State) object;
        return a == other.a && b == other.b && c == other.c;

    }

    @Override
    public int hashCode() {
        return a*a*a + b*b + c;
    }

    public String toString() {
        return String.format("%d %d %d", a, b, c);
    }

}
