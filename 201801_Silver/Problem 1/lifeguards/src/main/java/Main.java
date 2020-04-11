
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chensiyuan
 */
public class Main {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader("lifeguards.in"));
        
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        
        int numCows = Integer.parseInt(stringTokenizer.nextToken());
        
        ArrayList<Point> points = new ArrayList<Point>();
        
        for(int i=0; i < numCows; i++) {
            
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            
            points.add(new Point(Integer.parseInt(stringTokenizer.nextToken()), i, true));
            points.add(new Point(Integer.parseInt(stringTokenizer.nextToken()), i, false));
            
        }
        
        Collections.sort(points);
        
        bufferedReader.close();
        
        int[] independentTimes = new int[numCows];
        HashSet<Integer> cowsPresent = new HashSet<Integer>();
        int currentTime = 0;
        int totalTime = 0;
        
        for(Point point: points) {
            
            if(!cowsPresent.isEmpty()) {
                totalTime += point.getTime() - currentTime;
            }
            
            if(cowsPresent.size() == 1) {
                independentTimes[(Integer)cowsPresent.toArray()[0]] += point.getTime() - currentTime;
            }
            
            currentTime = point.getTime();
            
            if(point.getEnters()) {
                cowsPresent.add(point.getCow());
            } else {
                cowsPresent.remove(point.getCow());
            }
            
        }
        
        int minTime = Integer.MAX_VALUE;
        for(int i=0; i < numCows; i++) {
            if(minTime > independentTimes[i]) {
                minTime = independentTimes[i];
            }
        }
        
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("lifeguards.out")));
        
        printWriter.println(Integer.toString(totalTime - minTime));
        
        printWriter.close();
        
    }
    
}

class Point implements Comparable {
    
    private int time;
    private int cow;
    private boolean enters; // add: true
    
    public Point(int time, int cow, boolean enters) {
        
        this.time = time;
        this.cow = cow;
        this.enters = enters;
        
    }
    
    public int getTime() {
        return time;
    }
    
    public int getCow() {
        return cow;
    }
    
    public boolean getEnters() {
        return enters;
    }

    @Override
    public int compareTo(Object other) {
        return this.time - ((Point)other).time;
    }
    
    @Override
    public String toString() {
        return Integer.toString(time) + ", " + Integer.toString(cow) + ", " + Boolean.toString(enters);
    }
    
}
