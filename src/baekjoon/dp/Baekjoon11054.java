package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon11054 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] inc = new int[n]; //증가하는 수열
        int[] dec = new int[n]; //감소하는 수열
        Arrays.fill(inc, 1);
        Arrays.fill(dec, 1);

        //증가
        for(int i=0; i<n; i++){
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j]) inc[i] = Math.max(inc[i], inc[j]+1);
            }
        }
        //감소
        for(int i=n-1; i>=0; i--){
            for(int j=n-1; j>i; j--){
                if(arr[i] > arr[j]) dec[i] = Math.max(dec[i], dec[j]+1);
            }
        }

        int result = 0;
        for(int i=0; i<n; i++){
            result = Math.max(result, inc[i] + dec[i] - 1);
        }

        System.out.println(result);

    }
}
