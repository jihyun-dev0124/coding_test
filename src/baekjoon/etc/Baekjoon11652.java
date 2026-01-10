package baekjoon.etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon11652 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        for(int i = 0; i < n; i++) arr[i] = Long.parseLong(br.readLine());

        Arrays.sort(arr);

        long best = arr[0];
        int bestCnt = 1;
        long cur = arr[0];
        int curCnt = 1;

        for(int i = 1; i < n; i++){
            if(cur == arr[i]){
                curCnt++;
            } else{
                if(curCnt > bestCnt){
                    best = cur;
                    bestCnt = curCnt;
                }
                cur = arr[i];
                curCnt = 1;
            }
        }

        if(curCnt > bestCnt){
            best = cur;
        }

        System.out.println(best);
    }
}
