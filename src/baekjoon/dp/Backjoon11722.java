package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Backjoon11722 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] dp = new int[n];
        int maxLen = 0;

        for(int i=0; i<n; i++){
            int cur = arr[i];
            dp[i] = 1;

            for(int j=0; j<i; j++){
                if(arr[j] > cur){
                    dp[i] = Math.max(dp[i], dp[j] + 1); //key: dp[j]들과 비교해서 가장 긴 len에 나를 더해야 하니까!!
                }
            }

            maxLen = Math.max(maxLen, dp[i]);
        }

        System.out.println(maxLen);
    }
}
