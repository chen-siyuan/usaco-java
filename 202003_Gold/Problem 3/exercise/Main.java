import java.util.*;
import java.io.*;

public class Main {

    public static long add(long mod, long a, long b) {
        return (a + b) % mod;
    }

    public static long mul(long mod, long a, long b) {
        return (a * b) % mod;
    }
    
    public static void main(String[] args) {
        
        int n = 0;
        long m = 0;

        try(BufferedReader br = new BufferedReader(new FileReader("exercise.in"))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Long.parseLong(st.nextToken());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        boolean[] sieve = new boolean[n];
        sieve[0] = true;
        List<Integer> primes = new ArrayList<>();

        for(int i=0; i < n; i++) if(!sieve[i]) {
            int j = i * 2 + 1;
            while(j < n) {
                sieve[j] = true;
                j += i + 1;
            }
            primes.add(i + 1);
        }

        int p = primes.size();

        long[][] dp = new long[p + 1][n + 1];
        for(int i=0; i <= n; i++) dp[0][i] = 1;

        int i = 0;
        for(int prime: primes) {

            i++;
            for(int j=0; j <= n; j++) {
                
                dp[i][j] = dp[i - 1][j];

                int temp = prime;
                while(temp <= j) {
                    dp[i][j] = add(m, dp[i][j], mul(m, (long)temp, dp[i - 1][j - temp]));
                    temp *= prime;
                }

            }

        }

        try(PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")))) {
            pw.println(dp[p][n]);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
