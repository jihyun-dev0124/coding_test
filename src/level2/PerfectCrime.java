package level2;

import java.util.Arrays;

public class PerfectCrime {
    public static void main(String[] args) {

    }

    public static int solution(int[][] info, int n, int m) {
        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // dp[b]에서 b의 값일때 a의 최소값을 담음.

        for (int[] ints : info) {
            int addA = ints[0];
            int addB = ints[1];

            int[] next = new int[m];
            Arrays.fill(next, Integer.MAX_VALUE);

            for (int b = 0; b < m; b++) {
                if(dp[b] == Integer.MAX_VALUE) continue;
                int a = dp[b];
                if (a + addA < n) {
                    next[b] = Math.min(next[b], a + addA);
                }

                if (b + addB < m) {
                    next[b + addB] = Math.min(next[b + addB], a);
                }
            }

            dp = next;
        }

        int ans = Integer.MAX_VALUE;
        for (int b = 0; b < m; b++) {
            ans = Math.min(ans, dp[b]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
