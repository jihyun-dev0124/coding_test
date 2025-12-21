package baekjoon.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] lis = new int[n];
        int len = 0;

        for (int i = 0; i < n; i++) {
            int x = arr[i];

            int pos = lowerBound(lis, 0, len, x);
            lis[pos] = x;
            if(pos == len) {
                len++;
            }
        }

        System.out.println(len);

    }

    private static int lowerBound(int[] arr, int lo, int hi, int x) {
        while(lo < hi) {
            int mid = (lo + hi) >>> 1;
            if(arr[mid] < x) {
                lo = mid + 1;
            }else{
                hi = mid;
            }
        }
        return lo;
    }
}
