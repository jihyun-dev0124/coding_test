package baekjoon.dfsAndbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Baekjoon2667 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N, cnt;
    static boolean[][] visited;
    static int[][] arr;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];
        arr = new int[N][N];

        for(int i=0; i<N; i++){
            String read = br.readLine();
            for(int j=0; j<N; j++){
                arr[i][j] = read.charAt(j) - '0';
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == 0 || visited[i][j]) continue;
                cnt = 0;
                dfs(i, j);
                result.add(cnt);
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (Integer i : result) {
            System.out.println(i);
        }
    }

    static void dfs(int i, int j){
        visited[i][j] = true;
        cnt++;

        for(int m=0; m<4; m++){
            int mx = i + dx[m];
            int my = j + dy[m];
            if(mx < 0 || mx >= N || my < 0 || my >= N) continue;
            if(visited[mx][my] || arr[mx][my] == 0) continue;
            dfs(mx, my);
        }
    }
}
