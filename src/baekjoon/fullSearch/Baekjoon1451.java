package baekjoon.fullSearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1451 {
    static int N, M;
    static long ans;
    static long[][] ps;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //세로
        M = Integer.parseInt(st.nextToken()); //가로

        ans = 0;
        ps = new long[N+1][M+1];
        int[][] arr = new int[N+1][M+1];
        for (int r = 1; r <= N; r++) {
            String line = br.readLine();
            for (int c = 1; c <= M; c++) {
                arr[r][c] = line.charAt(c - 1) - '0';
            }
        }

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= M; c++) {
                ps[r][c] = ps[r-1][c] + ps[r][c-1] - ps[r-1][c-1] + arr[r][c];
            }
        }

        vertical(); //세로 3줄
        horizontal(); //가로 3줄
        left1Right2();
        left2Right1();
        top1bottom2();
        top2bottom1();

        System.out.println(ans);
    }

    static long rect(int r1, int c1, int r2, int c2) {
        return ps[r2][c2] - ps[r2][c1-1] - ps[r1-1][c2] + ps[r1-1][c1-1];
    }

    static void vertical(){
        for(int c1 = 1; c1 <= M-2; c1++){
            for(int c2 = c1+1; c2 <= M-1; c2++){
                long a = rect(1, 1, N, c1);
                long b = rect(1, c1+1, N, c2);
                long c = rect(1, c2+1, N, M);
                ans = Math.max(ans, a*b*c);
            }
        }
    }

    static void horizontal(){
        for(int r1 = 1; r1 <= N-2; r1++){
            for(int r2 = r1+1; r2 <= N-1; r2++){
                long a = rect(1, 1, r1, M);
                long b = rect(r1+1, 1, r2, M);
                long c = rect(r2+1, 1, N, M);
                ans = Math.max(ans, a*b*c);
            }
        }
    }

    //왼1, 오2 |=
    static void left1Right2(){
        for (int c1 = 1; c1 <= M - 1; c1++) {
            for(int r1=1; r1 <= N-1; r1++){
                long a = rect(1, 1, N, c1);
                long b = rect(1, c1+1, r1, M);
                long c = rect(r1+1, c1+1, N, M);
                ans = Math.max(ans, a*b*c);
            }
        }
    }

    //왼2, 오1 =|
    static void left2Right1(){
        for (int c1 = 1; c1 <= M - 1; c1++) {
            for(int r1=1; r1 <= N-1; r1++){
                long a = rect(1, c1+1, N, M); //오1
                long b = rect(1, 1, r1, c1);
                long c = rect(r1+1, 1, N, c1);
                ans = Math.max(ans, a*b*c);
            }
        }
    }

    static void top1bottom2(){
        //N - 세로, M - 가로
        for(int r1 = 1; r1 <= N-1; r1++){
            for(int c1 = 1; c1 <= M-1; c1++){
                long a = rect(1, 1, r1, M);
                long b = rect(r1+1, 1, N, c1);
                long c = rect(r1+1, c1+1, N, M);
                ans = Math.max(ans, a*b*c);
            }
        }
    }

    static void top2bottom1(){
        for (int r1 = 1; r1 <= N-1; r1++) {
            for (int c1 = 1; c1 <= M-1; c1++) {
                long a = rect(r1+1, 1, N, M);
                long b = rect(1, 1, r1, c1);
                long c = rect(1, c1+1, r1, M);
                ans = Math.max(ans, a*b*c);
            }
        }
    }
}
