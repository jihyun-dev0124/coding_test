package programmers.dynamicProgramming;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinimumExchangeCoin {
    public static void main(String[] args) {
        // 코인 N값과 코인 배열 {1,3,4}이 있다고 할때, 코인 배열안의 코인들을 합쳐서 코인 N값이 나오는 최소 코인 수를 구하시오 (중복 가능)
        int[] coins = {1, 3, 4};
        int n = 6;
        int solution = solution(coins, n);
        System.out.println("solution = " + solution);

    }

    public static int solution(int[] coins, int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // dp[i] -> 합이 i일때 코인 합의 최소값을 저장

        for (int i = 1; i <= n; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1); //현재 코인 적용 전 최소 경우의 수 + 1;
                }
            }
        }

        Deque<Integer> server = new ArrayDeque<>();
        

        return dp[n] == Integer.MAX_VALUE ? -1 : dp[n];
    }
}
