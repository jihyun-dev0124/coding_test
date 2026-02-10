package programmers.dynamicProgramming;

// 계단 오르기
public class GoUpTheStairs {
    public static void main(String[] args) {
        //총 n개의 계단을 오를 때 1칸 또는 2칸씩 올라갈 수 있다. n개의 계단을 끝까지 오를 경우 경우의 수를 구하시오

    }

    public static int solution(int n) {
        if(n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[n] = dp[n - 1] + dp[n - 2];
        }

        return dp[n];
    }

}
