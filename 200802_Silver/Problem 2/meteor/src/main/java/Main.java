
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
    
    public static int[][] times;
    public static boolean[][] searched;
    
    public static int search() {
        
        ArrayList<int[]> queue = new ArrayList<>();
        
        queue.add(new int[] {0, 0, 0});
        
        while(queue.size() > 0) {
            
            int[] temp = queue.remove(0);
            
            if(searched[temp[0]][temp[1]]) {continue;}
            
            searched[temp[0]][temp[1]] = true;
            
            if(times[temp[0]][temp[1]] == Integer.MAX_VALUE) {return temp[2];}
            
            if(!searched[temp[0]+1][temp[1]] && times[temp[0]+1][temp[1]] > temp[2] + 1) {
                queue.add(new int[] {temp[0] + 1, temp[1], temp[2] + 1});
            }
            
            if(!searched[temp[0]][temp[1]+1] && times[temp[0]][temp[1]+1] > temp[2] + 1) {
                queue.add(new int[] {temp[0], temp[1] + 1, temp[2] + 1});
            }
            
            if(temp[0] > 0 && !searched[temp[0]-1][temp[1]] && times[temp[0]-1][temp[1]] > temp[2] + 1) {
                queue.add(new int[] {temp[0] - 1, temp[1], temp[2] + 1});
            }
            
            if(temp[1] > 0 && !searched[temp[0]][temp[1]-1] && times[temp[0]][temp[1]-1] > temp[2] + 1) {
                queue.add(new int[] {temp[0], temp[1] - 1, temp[2] + 1});
            }
            
        }
        
        return -1;
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("meteor.in"));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int numPoints = Integer.parseInt(st.nextToken());
        
        times = new int[305][305];
        searched = new boolean[305][305];
        
        for(int i=0; i < 305; i++) {
            for(int j=0; j < 305; j++) {times[i][j] = Integer.MAX_VALUE;}
        }
        
        for(int i=0; i < numPoints; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            
            if(times[x][y] > time) {times[x][y] = time;}
            if(times[x+1][y] > time) {times[x+1][y] = time;}
            if(times[x][y+1] > time) {times[x][y+1] = time;}
            if(x > 0 && times[x-1][y] > time) {times[x-1][y] = time;}
            if(y > 0 && times[x][y-1] > time) {times[x][y-1] = time;}
            
        }
        
        br.close();
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("meteor.out")));
        
        pw.println(search());
        
        pw.close();
        
    }
    
}
