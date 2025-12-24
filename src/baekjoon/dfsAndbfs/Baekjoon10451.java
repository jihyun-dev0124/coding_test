package baekjoon.dfsAndbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon10451 {
    static int[] graph;
    static boolean[] visited;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int k=0; k<T; k++){
            int N = Integer.parseInt(br.readLine());
            graph = new int[N+1];
            visited = new boolean[N+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            cnt = 0;
            for(int i=1; i<=N; i++){
                graph[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=N; i++){
                if(visited[i]) continue;
                dfs(i);
            }

            System.out.println(cnt);
        }
    }

    static void dfs(int cur){
        visited[cur] = true;
        int next = graph[cur];
        if(visited[next]){
            cnt++;
            return;
        }
        dfs(next);
    }
}
