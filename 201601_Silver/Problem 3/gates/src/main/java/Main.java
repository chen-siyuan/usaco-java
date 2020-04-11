
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

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
    
    public static ArrayList<Byte>[][] adjacencyList;
    public static boolean[][] searched;
    public static int[][] colors;
    
    public static void search(int x, int y) {
        
        if(searched[x][y]) {
            return;
        }
        
        searched[x][y] = true;
        
        for(byte b: adjacencyList[x][y]) {
            
            int[] delta = new int[]{0, 0};
            
            switch(b) {
                case 0:
                    delta = new int[]{-1, 0}; break;
                case 1:
                    delta = new int[]{0, -1}; break;
                case 2:
                    delta = new int[]{1, 0}; break;
                case 3:
                    delta = new int[]{0, 1}; break;
            }
            
            colors[x + delta[0]][y + delta[1]] = colors[x][y];
            
            search(x + delta[0], y + delta[1]);
            
        }
        
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        BufferedReader br = new BufferedReader(new FileReader("gates.in"));
        
        int numMoves = Integer.parseInt(br.readLine());
        byte[] moves = new byte[numMoves];
        String rawMoves = br.readLine();
        
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        
        int x = 0;
        int y = 0;
        
        for(int i=0; i < numMoves; i++) {
            
            switch(rawMoves.charAt(i)) {
                
                case 'W':
                    
                    moves[i] = 0;
                    x--;
                    if(minX > x) {minX = x;}
                    break;
                    
                case 'S':
                    
                    moves[i] = 1;
                    y--;
                    if(minY > y) {minY = y;}
                    break;
                    
                case 'E':
                    
                    moves[i] = 2;
                    x++;
                    if(maxX < x) {maxX = x;}
                    break;
                    
                case 'N':
                    
                    moves[i] = 3;
                    y++;
                    if(maxY < y) {maxY = y;}
                    break;
                    
            }
            
        }
        
        br.close();
        
        int sizeX = maxX - minX + 2;
        int sizeY = maxY - minY + 2;
        
        adjacencyList = new ArrayList[sizeX][sizeY];
        colors = new int[sizeX][sizeY];
        searched = new boolean[sizeX][sizeY];
        int count = 0;
        
        for(int i=0; i < sizeX; i++) {
            
            for(int j=0; j < sizeY; j++) {
                
                adjacencyList[i][j] = new ArrayList<Byte>();
                colors[i][j] = count++;
                
                for(byte b=0; b < 4; b++) {
                    adjacencyList[i][j].add(b);
                }
                
                if(i == 0) {adjacencyList[i][j].remove(Byte.valueOf((byte)0));}
                if(j == 0) {adjacencyList[i][j].remove(Byte.valueOf((byte)1));}
                if(i == sizeX - 1) {adjacencyList[i][j].remove(Byte.valueOf((byte)2));}
                if(j == sizeY - 1) {adjacencyList[i][j].remove(Byte.valueOf((byte)3));}
                
            }
            
        }
        
        int currentX = - minX;
        int currentY = - minY;
        
        for(int i=0; i < numMoves; i++) {
            
            switch(moves[i]) {
                
                case 0:
                    
                    adjacencyList[currentX][currentY].remove(Byte.valueOf((byte)3));
                    adjacencyList[currentX][currentY+1].remove(Byte.valueOf((byte)1));
                    currentX--;
                    break;
                    
                case 1:
                    
                    adjacencyList[currentX][currentY].remove(Byte.valueOf((byte)2));
                    adjacencyList[currentX+1][currentY].remove(Byte.valueOf((byte)0));
                    currentY--;
                    break;
                    
                case 2:
                    
                    adjacencyList[currentX+1][currentY].remove(Byte.valueOf((byte)3));
                    adjacencyList[currentX+1][currentY+1].remove(Byte.valueOf((byte)1));
                    currentX++;
                    break;
                    
                case 3:
                    
                    adjacencyList[currentX][currentY+1].remove(Byte.valueOf((byte)2));
                    adjacencyList[currentX+1][currentY+1].remove(Byte.valueOf((byte)0));
                    currentY++;
                    break;
                    
            }
            
        }
        
        for(int i=0; i < sizeX; i++) {
            for(int j=0; j < sizeY; j++) {
                search(i, j);
            }
        }
        
        HashSet<Integer> uniqueColors = new HashSet<Integer>();
        
        for(int i=0; i < sizeX; i++) {
            for(int j=0; j < sizeY; j++) {
                uniqueColors.add(colors[i][j]);
            }
        }
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("gates.out")));
        
        pw.println(uniqueColors.size() - 1);
        
        pw.close();
        
    }
    
}
