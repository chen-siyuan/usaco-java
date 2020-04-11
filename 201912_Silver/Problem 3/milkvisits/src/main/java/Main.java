
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader("milkvisits.in"));
        
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int numFarms = Integer.parseInt(stringTokenizer.nextToken());
        int numFriends = Integer.parseInt(stringTokenizer.nextToken());
        
        ArrayList<Integer>[] adjacencyList = new ArrayList[numFarms];
        for(int i=0; i < numFarms; i++) {
            adjacencyList[i] = new ArrayList<Integer>();
        }
        
        boolean[] colors = new boolean[numFarms]; // H: false, G: true
        String rawColors = bufferedReader.readLine();
        for(int i=0; i < numFarms; i++) {
            colors[i] = rawColors.charAt(i) == 'G';
        }
        
        for(int i=0; i < numFarms - 1; i++) {
            
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            
            adjacencyList[a - 1].add(b - 1);
            adjacencyList[b - 1].add(a - 1);
            
        }
        
        ArrayList<Integer> farmStack = new ArrayList<Integer>();
        ArrayList<Integer> colonyStack = new ArrayList<Integer>();
        boolean[] searched = new boolean[numFarms];
        int[] colonies = new int[numFarms];
        
        for(int i=0; i < numFarms; i++) {
            
            farmStack.add(i);
            colonyStack.add(i);
            
        }
        
        while(farmStack.size() > 0) {
            
            int thisFarm = farmStack.remove(farmStack.size() - 1);
            int thisColony = colonyStack.remove(colonyStack.size() - 1);
            
            if(searched[thisFarm]) {
                continue;
            }
            
            for(int adjacentFarm: adjacencyList[thisFarm]) {
                
                if((!searched[adjacentFarm]) && (colors[thisFarm] == colors[adjacentFarm])) {
                    
                    farmStack.add(adjacentFarm);
                    colonyStack.add(thisColony);
                    
                }
                
            }
            
            searched[thisFarm] = true;
            colonies[thisFarm] = thisColony;
            
        }
        
        char[] answers = new char[numFriends];
        
        for(int i=0; i < numFriends; i++) {
            
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            
            int startFarm = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            int endFarm = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            boolean color = stringTokenizer.nextToken().equals("G");
            
            if((colonies[startFarm] == colonies[endFarm]) && (colors[startFarm] != color)) {
                answers[i] = '0';
            } else {
                answers[i] = '1';
            }
            
        }
        
        bufferedReader.close();
        
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));

        printWriter.println(new String(answers));
        
        printWriter.close();
        
    }
    
}
