package baekjoon.etc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Baekjoon2331 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        int index = 0;
        int cur = A;
        Map<Integer,Integer> map = new HashMap<>();
        while(!map.containsKey(cur)){
            map.put(cur,index++);
            cur = calc(cur, P);
        }

        System.out.println(map.get(cur));
    }

    //지수 계산
    static int pow(int base, int exc){
        int result = 1;
        for(int i=0; i<exc; i++){
            result *= base;
        }
        return result;
    }

    //수열 계산
    static int calc(int num, int exc){
        int result = 0;
        while(num > 0){
            int n = num % 10;
            result += pow(n, exc);
            num /= 10;
        }
        return result;
    }
}
