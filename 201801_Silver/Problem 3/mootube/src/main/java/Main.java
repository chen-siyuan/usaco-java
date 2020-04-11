
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
    
    public static int numPoints;
    public static ArrayList<int[]>[] adjacencyList;
    public static boolean[] searched;
    
    public static void search(int weight, int point) {
        
        searched[point] = true;
        
        for(int[] neighbor: adjacencyList[point]) {
            
            if(!searched[neighbor[0]] && neighbor[1] >= weight) {
                search(weight, neighbor[0]);
            }
            
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        numPoints = Integer.parseInt(st.nextToken());
        int numQueries = Integer.parseInt(st.nextToken());
        
        adjacencyList = new ArrayList[numPoints];
        
        for(int i=0; i < numPoints; i++) {
            adjacencyList[i] = new ArrayList<>();
        }
        
        for(int i=0; i < numPoints - 1; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            int p1 = Integer.parseInt(st.nextToken()) - 1;
            int p2 = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            
            adjacencyList[p1].add(new int[]{p2, weight});
            adjacencyList[p2].add(new int[]{p1, weight});
            
        }
        
        searched = new boolean[numPoints];
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        
        for(int i=0; i < numQueries; i++) {
            
            for(int j=0; j < numPoints; j++) {
                searched[j] = false;
            }
            
            st = new StringTokenizer(br.readLine());
            
            search(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
            
            int count = 0;
            
            for(int j=0; j < numPoints; j++) {
                
                if(searched[j]) {
                    count++;
                }
                
            }
            
            pw.println(count - 1);
            
        }
        
        br.close();
        pw.close();
        
    }
    
}
