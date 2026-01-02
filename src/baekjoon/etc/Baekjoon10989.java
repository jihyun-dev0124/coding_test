package baekjoon.etc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon10989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[10001];
        for(int i=0; i<N; i++){
            int n = Integer.parseInt(br.readLine());
            arr[n]+=1;
        }

        StringBuilder st = new StringBuilder();
        for(int i=1; i<=10000; i++){
            if(arr[i] == 0) continue;
            for(int j=0; j<arr[i]; j++){
                st.append(i+"\n");
            }
        }

        System.out.print(st);

        /*
        모두 배열에 저장해서 출력하는건 메모리 초과!
        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        for(int i=0; i<N; i++) System.out.println(arr[i]);*/
    }
}
