package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[1001][1001];
        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i <= n; i++) {
            for(int j = 0; j <= 9; j++) {
                int count = 0;
                for(int k = j; k <= 9; k++) {
                    System.out.println("dp["+(i - 1)+"]["+k+"]="+dp[i-1][k]);
                    count += dp[i-1][k];
                }

                System.out.println(">>>>>>>>> dp["+i+"]["+j+"]="+count);
                dp[i][j] = count % 10007;
            }
        }

        long result = 0L;
        for(int i = 0; i <= 9; i++) {
            result = (result + dp[n][i]) % 10007;
        }

        System.out.println(result);
    }
}
