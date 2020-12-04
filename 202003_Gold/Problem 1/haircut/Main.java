import java.util.*;
import java.io.*;

public class Main {

    private static class Node {
        
        Node left, right;
        int val, sum, dup;

        public Node(int _val) {
            val = _val;
            sum = 0;
            dup = 1;
        }

    }
    
    private static Node insert(Node root, int num, int[] temp) {

        if(root == null) return new Node(num);

        if(num == root.val) {
            root.dup++;
            temp[0] += root.sum;
        } else if(num > root.val) {
            root.sum++;
            root.left = insert(root.left, num, temp);
        } else {
            temp[0] += root.dup + root.sum;
            root.right = insert(root.right, num, temp);
        }

        return root;
    }

    public static void main(String[] args) {

        int n = 0;
        Node root = null;
        long[] cache = null;

        try(BufferedReader br = new BufferedReader(new FileReader("haircut.in"))) {

            n = Integer.parseInt(br.readLine());
            cache = new long[n];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i < n; i++) {
                int num = Integer.parseInt(st.nextToken());
                int[] temp = new int[1];
                root = insert(root, num, temp);
                if(num != n) cache[num] += temp[0];
            }
            
        } catch(IOException e) {}

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")))) {
            long res = 0;
            pw.println(res);
            for(int i=0; i < n - 1; i++) pw.println((res += cache[i]));
        } catch(IOException e) {}

    }

}

