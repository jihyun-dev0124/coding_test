package baekjoon.dfsAndbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon9466 {
    static int res;
    static boolean[] visited;
    static boolean[] finished;
    static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int k=0; k<T; k++){
            int n = Integer.parseInt(br.readLine());
            res = 0;
            arr = new int[n+1];
            visited = new boolean[n+1];
            finished = new boolean[n+1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1; i<=n; i++) arr[i] = Integer.parseInt(st.nextToken());

            for(int i=1; i<=n; i++){
                if(visited[i]) continue;
                dfs(i);
            }

            System.out.println(n - res);
        }

    }

    static void dfs(int cur){
        visited[cur] = true;
        int next = arr[cur];
        if(!visited[next]){
            dfs(next);
        }else if(!finished[next]){
            res++;
            for(int x = arr[next]; x != next; x = arr[x]){
                res++;
            }
        }
        finished[cur] = true;
        System.out.println("cur = " + cur);
    }
}
