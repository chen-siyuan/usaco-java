/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author chensiyuan
 */
public class Main {
    
    public static void switchCows(int x, int y, int[] weights, int[] locations, int[] directions) {
        
        if(x == y) {
            return;
        }
        
        int itemp = weights[x];
        weights[x] = weights[y];
        weights[y] = itemp;
        
        itemp = locations[x];
        locations[x] = locations[y];
        locations[y] = itemp;
        
        itemp = directions[x];
        directions[x] = directions[y];
        directions[y] = itemp;
        
    }
    
    public static void sortCows(int head, int tail, int[] weights, int[] locations, int[] directions) {
        
        if(tail - head < 2) {
            return;
        }
        
        int mid = (head + tail) / 2;
        
        sortCows(head, mid, weights, locations, directions);
        sortCows(mid, tail, weights, locations, directions);
        
        ArrayList<int[]> sortedSegment = new ArrayList<int[]>();
        
        int i = head;
        int j = mid;
        
        for(int k=0; k < tail - head; k++) {
            
            if(i == mid) {
                sortedSegment.add(new int[] {weights[j], locations[j], directions[j++]});
                continue;
            }
            
            if(j == tail) {
                sortedSegment.add(new int[] {weights[i], locations[i], directions[i++]});
                continue;
            }
            
            if(locations[i] < locations[j]) {
                sortedSegment.add(new int[] {weights[i], locations[i], directions[i++]});
            } else {
                sortedSegment.add(new int[] {weights[j], locations[j], directions[j++]});
            }
            
        }
        
        for(int k=0; k < tail - head; k++) {
            
            weights[k + head] = sortedSegment.get(k)[0];
            locations[k + head] = sortedSegment.get(k)[1];
            directions[k + head] = sortedSegment.get(k)[2];
            
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        // Input
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader("meetings.in"));
        
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        
        int numCows = Integer.parseInt(stringTokenizer.nextToken());
        int length = Integer.parseInt(stringTokenizer.nextToken());
        
        int[] initialWeights = new int[numCows];
        int[] initialLocations = new int[numCows];
        int[] initialDirections = new int[numCows];
        
        int numPositives = 0;
        int sumWeights = 0;
        
        for(int i=0; i < numCows; i++) {
            
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            
            initialWeights[i] = Integer.parseInt(stringTokenizer.nextToken());
            initialLocations[i] = Integer.parseInt(stringTokenizer.nextToken());
            initialDirections[i] = Integer.parseInt(stringTokenizer.nextToken());
            
            if(initialDirections[i] == 1) {
                numPositives++;
            }
            
            sumWeights += initialWeights[i];
            
        }
        
        bufferedReader.close();
        
        // Adjust such that all cows are going to the left, weights and locations fixed
        
        sortCows(0, numCows, initialWeights, initialLocations, initialDirections);
        
        int initialSumIndices = 0; // used to detect num collisions; only count the indices of cows going right
        
        int[] actualWeights = new int[numCows];
        
        int[] actualLocations = new int[numCows];
        int nCounter = 0;
        int pCounter = numCows - numPositives;
        
        int[] actualDirections = new int[numCows];
        
        for(int i = 0; i < numCows; i++) {
            
            actualWeights[i] = initialWeights[i];
            
            if(initialDirections[i] == 1) {
                initialSumIndices += i;
                actualLocations[pCounter++] = length - initialLocations[i];
            } else {
                actualLocations[nCounter++] = initialLocations[i];
            }
            
            actualDirections[i] = -1;
            
        }
        
        sortCows(0, numCows, actualWeights, actualLocations, actualDirections);
        
        // Get the time
        
        int currentSum = 0;
        int currentCow = -1;
        
        while(currentSum * 2 < sumWeights) {
            currentSum += actualWeights[++currentCow];
        }
        
        int time = actualLocations[currentCow];
        
        // Use the time and initialSumIndices to get numCollisions
        
        for(int i=0; i < numCows; i++) {
            initialLocations[i] += time * initialDirections[i];
        }
        
        sortCows(0, numCows, initialWeights, initialLocations, initialDirections);
        
        int finalSumIndices = 0;
        
        for(int i=0; i < numCows; i++) {
            
            if(initialDirections[i] == 1) {
                finalSumIndices += i;
            }
            
        }
        
        int numCollisions = finalSumIndices - initialSumIndices;
        
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("meetings.out")));
        
//        printWriter.println(Arrays.toString(initialWeights));
//        printWriter.println(Arrays.toString(initialLocations));
//        printWriter.println(Arrays.toString(initialDirections));
//        printWriter.println(initialSumIndices);
//        printWriter.println(Arrays.toString(actualWeights));
//        printWriter.println(Arrays.toString(actualLocations));
//        printWriter.println(Arrays.toString(actualDirections));
//        printWriter.println(sumWeights);
//        printWriter.println(time);

        printWriter.println(numCollisions);
        
        printWriter.close();
        
    }
    
}

