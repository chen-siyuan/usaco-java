
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
    
    public static boolean[] searched;
    public static ArrayList<Integer>[] adjacencyList;
    
    public static void search(int current) {
        
        searched[current] = true;
        
        for(int neighbor: adjacencyList[current]) {
            
            if(!searched[neighbor]) {
                search(neighbor);
            }
            
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int numPoints = Integer.parseInt(st.nextToken());
        
        int[][] points = new int[numPoints][3];
        
        for(int i=0; i < numPoints; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            points[i][0] = Integer.parseInt(st.nextToken());
            points[i][1] = Integer.parseInt(st.nextToken());
            points[i][2] = Integer.parseInt(st.nextToken());
            
        }
        
        br.close();
        
        adjacencyList = new ArrayList[numPoints];
        searched = new boolean[numPoints];
        
        for(int i=0; i < numPoints; i++) {
            adjacencyList[i] = new ArrayList<Integer>();
        }
        
        for(int i=0; i < numPoints; i++) {
            
            for(int j=0; j < numPoints; j++) {
                
                if(i != j && Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2) <= Math.pow(points[i][2], 2)) {
                    adjacencyList[i].add(j);
                }
                
            }
            
        }
        
        int max = Integer.MIN_VALUE;
        
        for(int i=0; i < numPoints; i++) {
            
            for(int j=0; j < numPoints; j++) {
                searched[j] = false;
            }
        
            search(i);
        
            int count = 0;
        
            for(int j=0; j < numPoints; j++) {
                if(searched[j]) {
                    count++;
                }
            }
        
            if(max < count) {
                max = count;
            }
            
        }
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        
        pw.println(max);
        
        pw.close();
        
    }
    
}
