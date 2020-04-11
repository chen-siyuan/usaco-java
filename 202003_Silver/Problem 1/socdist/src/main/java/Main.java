
import java.io.BufferedReader;
import java.io.BufferedWriter;
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
    
    public static int numCows;
    public static int numIntervals;
    public static long[] endpoints;
    
    public static boolean couldFit(long distance) {
        
        int numLeft = numCows - 1;
        
        int pointer = 0;
        long position = endpoints[0];
        
        while(numLeft > 0) {
            
            position += distance;
            
            while((pointer + 2 < numIntervals * 2) && (position > endpoints[pointer + 1])) {
                pointer += 2;
            }
            
            if(position > endpoints[pointer + 1]) {
                return false;
            }
            
            if(position < endpoints[pointer]) {
                position = endpoints[pointer];
            }
            
            numLeft--;
            
        }
        
        return true;
    }
    
    public static long search(long lower, long upper) {
        
        if(upper - lower <= 1) {
            return lower;
        }
        
        return couldFit((lower + upper) / 2) ? search((lower + upper) / 2, upper) : search(lower, (lower + upper) / 2);
        
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("socdist.in"));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        numCows = Integer.parseInt(st.nextToken());
        numIntervals = Integer.parseInt(st.nextToken());
        
        endpoints = new long[numIntervals * 2];
        
        for(int i=0; i < numIntervals; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            endpoints[i*2] = Long.parseLong(st.nextToken());
            endpoints[i*2 + 1] = Long.parseLong(st.nextToken());
            
        }

        br.close();
        
        Arrays.sort(endpoints);
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        
        pw.println(search(0, Long.MAX_VALUE));
        
        pw.close();

    }
    
}
