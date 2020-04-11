
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

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
        
        BufferedReader bufferedReader = new BufferedReader(new FileReader("leftout.in"));
        
        int dimension = Integer.parseInt(bufferedReader.readLine());
        
        boolean[][] matrix = new boolean[dimension][dimension];
        
        for(int i=0; i < dimension; i++) {
            
            String line = bufferedReader.readLine();
            
            for(int j=0; j < dimension; j++) {
                matrix[i][j] = line.charAt(j) == 'R';
            }
            
        }
        
        bufferedReader.close();
        
        boolean flag;
        
        do {
            
            flag = false;
            
            for(int j=0; j < dimension; j++) {
                
                if(needFlipRow(matrix, j)) {
                    
                    flipRow(matrix, j);
                    flag = true;
                    
                }
                
            }
            
            for(int i=0; i < dimension; i++) {
                
                if(needFlipColumn(matrix, i)) {
                    
                    flipColumn(matrix, i);
                    flag = true;
                    
                }
                
            }
            
        } while(flag);
        
        int count = 0;
        int x = 0;
        int y = 0;
        
        for(int i=0; i < dimension; i++) {
            
            for(int j=0; j < dimension; j++) {
                
                if(matrix[i][j]) {
                    
                    count++;
                    x = i;
                    y = j;
                    
                }
                
            }
            
        }
        
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("leftout.out")));
        
        if(count == 1) {
            printWriter.println(String.format("%d %d", x + 1, y + 1));
        } else {
            printWriter.println(-1);
        }
        
//        for(boolean[] line: matrix) {
//            
//            for(boolean bool: line) {
//                printWriter.print(bool ? "1 " : "0 ");
//            }
//            
//            printWriter.println();
//            
//        }
        
        printWriter.close();
        
    }
    
    public static void flipRow(boolean[][] matrix, int index) {
        
        for(int j=0; j < matrix.length; j++) {
            matrix[index][j] = !matrix[index][j];            
        }
        
    }
    
    public static void flipColumn(boolean[][] matrix, int index) {
        
        for(int i=0; i < matrix.length; i++) {
            matrix[i][index] = !matrix[i][index];
        }
        
    }
    
    public static boolean needFlipRow(boolean[][] matrix, int index) {
        
        int count = 0;
        
        for(int j=0; j < matrix.length; j++) {
            count += matrix[index][j] ? 1 : -1;
        }
        
        if(count > 0) {
            return true;
        } else {
            return count == 0 && !matrix[index][0];
        }
        
    }
    
    public static boolean needFlipColumn(boolean[][] matrix, int index) {
        
        int count = 0;
        
        for(int i=0; i < matrix.length; i++) {
            count += matrix[i][index] ? 1 : -1;
        }
        
        if(count > 0) {
            return true;
        } else {
            return count == 0 && !matrix[0][index];
        }
        
    }
    
}
