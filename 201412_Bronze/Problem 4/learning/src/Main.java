import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int numCows;
    public static int startPosition;
    public static int endPosition;
    public static Cow[] cows;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader("learning.in"));

        StringTokenizer st = new StringTokenizer(br.readLine());

        numCows = Integer.parseInt(st.nextToken());
        startPosition = Integer.parseInt(st.nextToken());
        endPosition = Integer.parseInt(st.nextToken()) + 1;

        cows = new Cow[numCows];

        for(int i=0; i < numCows; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new Cow("S".equals(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        br.close();

        Arrays.sort(cows);

        int count = 0;

        if(startPosition < cows[0].getPosition()) {
            if(cows[0].isSpotted()) count += cows[0].getPosition() - startPosition;
            startPosition = cows[0].getPosition();
        }

        if(endPosition > cows[numCows - 1].getPosition()) {
            if(cows[numCows - 1].isSpotted()) count += endPosition - cows[numCows - 1].getPosition();
            endPosition = cows[numCows - 1].getPosition();
        }

        for(int i=0; i < numCows - 1; i++) {

            if(startPosition >= cows[i + 1].getPosition()) continue;
            if(endPosition <= cows[i].getPosition()) continue;

            if(startPosition <= cows[i].getPosition()) {

                if(endPosition >= cows[i + 1].getPosition()) {
                    // full interval

                    if(cows[i].isSpotted()) {

                        if(cows[i + 1].isSpotted()) {
                            count += cows[i + 1].getPosition() - cows[i].getPosition();
                        } else {
                            count += (cows[i + 1].getPosition() - cows[i].getPosition()) / 2 + 1;
                        }

                    } else {

                        if(cows[i + 1].isSpotted()) {
                            count += (cows[i + 1].getPosition() - cows[i].getPosition()) / 2;
                        } else {
                            count += 0;
                        }

                    }

                } else {
                    // ends early

                    if(cows[i].isSpotted()) {

                        if(cows[i + 1].isSpotted()) {
                            count += endPosition - cows[i].getPosition();
                        } else {
                            count += Math.min(endPosition, (cows[i].getPosition() + cows[i + 1].getPosition()) / 2 + 1)
                                    - cows[i].getPosition();
                        }

                    } else {

                        if(cows[i + 1].isSpotted()) {
                            count += Math.max(0, endPosition - (cows[i].getPosition() + cows[i + 1].getPosition() + 1) / 2);
                        } else {
                            count += 0;
                        }

                    }

                }

            } else {

                if(endPosition >= cows[i + 1].getPosition()) {
                    // starts late

                    if(cows[i].isSpotted()) {

                        if(cows[i + 1].isSpotted()) {
                            count += cows[i + 1].getPosition() - startPosition;
                        } else {
                            count += Math.max(0, (cows[i].getPosition() + cows[i + 1].getPosition()) / 2 + 1 - startPosition);
                        }

                    } else {

                        if(cows[i + 1].isSpotted()) {
                            count += cows[i + 1].getPosition() -
                                    Math.max(startPosition, (cows[i].getPosition() + cows[i + 1].getPosition() + 1) / 2);
                        } else {
                            count += 0;
                        }

                    }

                } else {
                    // starts late ends early

                    if(cows[i].isSpotted()) {

                        if(cows[i + 1].isSpotted()) {
                            count += endPosition - startPosition;
                        } else {
                            count += Math.max(0, Math.min(endPosition,
                                    (cows[i].getPosition() + cows[i + 1].getPosition()) / 2 + 1) - startPosition);
                        }

                    } else {

                        if(cows[i + 1].isSpotted()) {
                            count += Math.max(0, endPosition - Math.max(startPosition,
                                    (cows[i].getPosition() + cows[i + 1].getPosition() + 1) / 2));
                        } else {
                            count += 0;
                        }

                    }

                }

            }

        }

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("learning.out")));

        pw.println(count);

        pw.close();

    }

}

class Cow implements Comparable<Cow> {

    private final int position;
    private final boolean spotted;

    public Cow(boolean spotted, int position) {
        this.position = position;
        this.spotted = spotted;
    }

    public int getPosition() {
        return position;
    }

    public boolean isSpotted() {
        return spotted;
    }

    @Override
    public int compareTo(Cow cow) {
        return position - cow.position;
    }

    @Override
    public String toString() {
        return position + (spotted ? " *" : " _");
    }

}
