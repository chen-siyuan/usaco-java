
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
        
        BufferedReader br = new BufferedReader(new FileReader("cowjump.in"));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int numLines = Integer.parseInt(st.nextToken());
        
        Line[] lines = new Line[numLines];
        
        for(int i=0; i < numLines; i++) {
            
            st = new StringTokenizer(br.readLine());
            
            lines[i] = new Line(
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
            
        }
        
        br.close();
        
        int i = 0;
        int j = 0;
        
        boolean flag = true;
        
        while(flag && i < numLines - 1) {
            
            j = i + 1;
            
            while(flag && j < numLines) {
                
                flag = !intersects(lines[i], lines[j]);
                j++;
                
            }
            
            i++;
            
        }
        
        i--;
        j--;
        
        flag = false;
        
        for(int k=i+1; k < numLines; k++) {
            
            if(k != j && intersects(lines[k], lines[j])) {
                flag = true;
            }
            
        }
        
        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("cowjump.out")));
        
        printWriter.println(flag ? j + 1 : i + 1);
        
        printWriter.close();
        
    }
    
    public static boolean intersects(Line line1, Line line2) {
        
        long[] u = new long[2];
        long[] v = new long[2];
        long[] w = new long[2];
        
        u[0] = line2.getX1() - line1.getX1();
        u[1] = line2.getY1() - line1.getY1();
        
        v[0] = line2.getX2() - line1.getX1();
        v[1] = line2.getY2() - line1.getY1();
        
        w[0] = line1.getX2() - line1.getX1();
        w[1] = line1.getY2() - line1.getY1();
        
        long denominator = u[1]*v[0] - u[0]*v[1];
        
        if(denominator == 0) {
            return Math.abs(line2.getX2() - line2.getX1()) == Math.abs(u[0]) + Math.abs(v[0]) &&
                    Math.abs(line2.getY2() - line2.getY1()) == Math.abs(u[1]) + Math.abs(v[1]);
        }
        
        double alpha = (v[0]*w[1] - v[1]*w[0]) * 1. / denominator;
        double beta = (u[1]*w[0] - u[0]*w[1]) * 1. / denominator;
        
        return alpha >= 0 && beta >= 0 && alpha + beta >= 1;
    }
    
}

class Line {
    
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    
    public Line(int x1, int y1, int x2, int y2) {
        
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        
    }
    
    public int getX1() {
        return x1;
    }
    
    public int getY1() {
        return y1;
    }
    
    public int getX2() {
        return x2;
    }
    
    public int getY2() {
        return y2;
    }
    
    public String toString() {
        return String.format("%d %d %d %d", x1, y1, x2, y2);
    }
    
}
