
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
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
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader bufferedWriter = new BufferedReader(new FileReader("moop.in"));
        
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedWriter.readLine());
        
        int numPoints = Integer.parseInt(stringTokenizer.nextToken());
        
        ArrayList<Point> points = new ArrayList<Point>();
        
        for(int i=0; i < numPoints; i++) {
            
            stringTokenizer = new StringTokenizer(bufferedWriter.readLine());
            points.add(new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken())));
            
        }
        
        bufferedWriter.close();
        
        Collections.sort(points);
        
        int[] ys = new int[numPoints];
        
        for(int i=0; i < numPoints; i++) {
            ys[i] = points.get(i).getY();
        }
        
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
        
        printWriter.println(numColonies(ys));
        
        printWriter.close();
        
    }
    
    public static int numColonies(int[] intArray) {
        
        ArrayList<Integer> colonies = new ArrayList<Integer>();
        
        for(int i: intArray) {
            
            if(colonies.size() == 0) {
                colonies.add(i);
            } else {
                
                int min = colonies.get(colonies.size() - 1);
                
                if(min > i) {
                    min = i;
                }
                
                while(colonies.size() > 0 && colonies.get(colonies.size() - 1) <= i) {
                    colonies.remove(colonies.size() - 1);
                }
                
                colonies.add(min);
                
            }
            
        }
        
        return colonies.size();
    }
    
}

class Point implements Comparable {
    
    private int x;
    private int y;
    
    public Point(int x, int y) {
        
        this.x = x;
        this.y = y;
        
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Object arg0) {
        
        if(this.x == ((Point)arg0).x) {
            return this.y - ((Point)arg0).y;
        } else {
            return this.x - ((Point)arg0).x;
        }
        
    }
    
}
