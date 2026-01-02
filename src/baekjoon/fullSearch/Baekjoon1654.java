package baekjoon.fullSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1654 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] arr = new long[K];
        long high = 0;
        for(int i=0; i<K; i++){
            arr[i] = Long.parseLong(br.readLine());
            if(high < arr[i]) high = arr[i];
        }

        long low = 1;
        long ans = 0;
        while(low <= high){
            long cnt = 0;
            long mid = (high + low) / 2;

            for(int i=0; i<K; i++){
                cnt += (arr[i] / mid);
            }

            if(cnt >= N){
                ans = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }

        System.out.println(ans);
    }
}
