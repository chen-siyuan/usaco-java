
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader("maxcross.in"));
        
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        
        int numPoints = Integer.parseInt(stringTokenizer.nextToken());
        int length = Integer.parseInt(stringTokenizer.nextToken());
        int numBrokens = Integer.parseInt(stringTokenizer.nextToken());
        
        HashSet<Integer> brokens = new HashSet<Integer>();
        
        for(int i=0; i < numBrokens; i++) {
            brokens.add(Integer.parseInt(bufferedReader.readLine()) - 1);
        }
        
        bufferedReader.close();
        
        int current = 0;
        
        for(int i=0; i < length; i++) {
            
            if(brokens.contains(i)) {
                current++;
            }
            
        }
        
        int min = current;
        
        for(int i=length; i < numPoints; i++) {
            
            if(brokens.contains(i)) {
                current++;
            }
            
            if(brokens.contains(i - length)) {
                current--;
            }
            
            if(min > current) {
                min = current;
            }
            
        }
        
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        
        printWriter.println(min);
        
        printWriter.close();
        
    }
    
}
