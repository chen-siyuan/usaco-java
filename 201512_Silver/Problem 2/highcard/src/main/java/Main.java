
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
        
        BufferedReader br = new BufferedReader(new FileReader("highcard.in"));
        
        int numCards = Integer.parseInt(br.readLine());
        
        boolean[] cards = new boolean[numCards * 2];
        
        for(int i=0; i < numCards; i++) {
            cards[Integer.parseInt(br.readLine()) - 1] = true;
        }
        
        br.close();
        
        int temp = 0;
        int count = 0;
        
        for(int i=0; i < numCards * 2; i++) {
            
            if(cards[i]) {
                temp++;
            } else {
                
                if(temp > 0) {
                    
                    temp--;
                    count++;
                    
                }
                
            }
            
        }
        
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        
        pw.println(count);
        
        pw.close();
        
    }
    
}
