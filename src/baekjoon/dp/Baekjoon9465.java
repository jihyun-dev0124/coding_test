package baekjoon.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Baekjoon9465 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for(int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][n];
            for(int j = 0; j < 2; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int k = 0; k < n; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][n];
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];

            for(int j = 1; j < n; j++) {
                if (j < 2) {
                    dp[0][j] = dp[1][j - 1] + sticker[0][j];
                    dp[1][j] = dp[0][j - 1] + sticker[1][j];
                }else{
                    dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + sticker[0][j];
                    dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + sticker[1][j];
                }
            }

            bw.write(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\n");
        }

        bw.flush();
    }
}
