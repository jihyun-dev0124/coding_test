package baekjoon.dp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Backjoon11724 {
    static int n, m;
    static List<Integer>[] graph;
    static boolean[] visited;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        for(int i=1; i<=n; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }

        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            dfs(i);
            cnt++;
        }

        System.out.println(cnt);
    }

    public static void dfs(int cur){
        visited[cur] = true;
        List<Integer> nodes = graph[cur];
        for(int i=0; i<nodes.size(); i++){
            int next = nodes.get(i);
            if(!visited[next]) dfs(next);
        }
    }
}
