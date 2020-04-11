
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
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
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader("hps.in"));
        
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        
        int numGestures = Integer.parseInt(stringTokenizer.nextToken());
        
        char[] gestures = new char[numGestures];
        
        int[] forwardCount = new int[3];
        int[] backwardCount = new int[3];
        
        for(int i=0; i < numGestures; i++) {
            
            gestures[i] = bufferedReader.readLine().charAt(0);
            
            switch(gestures[i]) {
                
                case 'H':
                    backwardCount[0]++;
                    break;
                    
                case 'P':
                    backwardCount[1]++;
                    break;
                    
                case 'S':
                    backwardCount[2]++;
                    break;
                
            }
            
        }
        
        bufferedReader.close();
        
        int maxMaxWin = Integer.MIN_VALUE;
        
        for(int i=0; i < numGestures; i++) {
            
            if(maxMaxWin < maxWin(forwardCount, backwardCount)) {
                maxMaxWin = maxWin(forwardCount, backwardCount);
            }
            
            switch(gestures[i]) {
                
                case 'H':
                    forwardCount[0]++;
                    backwardCount[0]--;
                    break;
                    
                case 'P':
                    forwardCount[1]++;
                    backwardCount[1]--;
                    break;
                    
                case 'S':
                    forwardCount[2]++;
                    backwardCount[2]--;
                    break;
                
            }
            
        }
        
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
        
        printWriter.println(maxMaxWin);
        
        printWriter.close();
        
    }
    
    public static int getMax(int[] anArray) {
        
        int max = Integer.MIN_VALUE;
        
        for(int i=0; i < anArray.length; i++) {
            if(max < anArray[i]) {
                max = anArray[i];
            }
        }
        
        return max;
    }
    
    public static int maxWin(int[] forwardCount, int[] backwardCount) {
        return getMax(forwardCount) + getMax(backwardCount);
    }
    
}
