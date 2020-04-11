
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        BufferedReader br = new BufferedReader(new FileReader("cereal.in"));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int numCows = Integer.parseInt(st.nextToken());
        int numColors = Integer.parseInt(st.nextToken());
        
        int[][] preferences = new int[numCows][3];
        
        for(int i=0; i < numCows; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            preferences[i][0] = Integer.parseInt(st.nextToken()) - 1;
            preferences[i][1] = Integer.parseInt(st.nextToken()) - 1;
            preferences[i][2] = 0; //0: first choice; 1: second choice
            
        }
        
        br.close();
        
        int[] answers = new int[numCows];
        int[] takenBy = new int[numColors];
        int taken = 0;
        
        for(int i=0; i < numColors; i++) {
            takenBy[i] = -1;
        }
        
        for(int i=numCows - 1; i >= 0; i--) {
            
            if(taken == numColors) {
                answers[i] = numColors;
                continue;
            }
            
            if(takenBy[preferences[i][0]] == -1) {
                
                takenBy[preferences[i][0]] = numCows - 1 - i;
                taken++;
                
            } else {
                
                int pointer = numCows - 1 - takenBy[preferences[i][0]];
                
                takenBy[preferences[i][0]] = numCows - 1 - i;
                
                boolean flag = true;
                
                while(flag) {
                    
                    flag = false;
                    
                    if(preferences[pointer][2] == 0 && (pointer < numCows - 1 - takenBy[preferences[pointer][1]])) {
                        
                        preferences[pointer][2] = 1;
                        
                        if(takenBy[preferences[pointer][1]] != -1) {
                            
                            int temp = takenBy[preferences[pointer][1]];
                            takenBy[preferences[pointer][1]] = numCows - 1 - pointer;
                            pointer = numCows - 1 - temp;
                            flag = true;
                            
                        } else {
                            
                            takenBy[preferences[pointer][1]] = numCows - 1 - pointer;
                            taken++;
                            
                        }
                        
                    } else {
                        preferences[pointer][2] = -1;
                    }
                    
                }
                
            }
            
            answers[i] = taken;
            
        }
                
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("cereal.out")));
        
        for(int i: answers) {
            pw.println(i);
        }
        
        pw.close();
        
    }
    
}
