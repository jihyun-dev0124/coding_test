package baekjoon.dfsAndbfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Baekjoon1260 {
    public static int n;
    public static int m;
    public static int v;
    public static boolean[] visited;
    public static List<Integer>[] graph;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        graph = new List[n+1];

        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
            graph[e].add(s);
        }

        for(int i=1; i<=n; i++) Collections.sort(graph[i]);

        visited = new boolean[n+1];
        dfs(v);
        sb.append("\n");

        visited = new boolean[n+1];
        bfs(v);

        System.out.println(sb.toString());

    }

    public static void dfs(int cur){
        visited[cur] = true;
        sb.append(cur).append(' ');
        for(int next : graph[cur]){
            if(!visited[next]) dfs(next);
        }
    }

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()){
            int cur = q.poll();
            if(visited[cur]) continue;
            visited[cur] = true;
            sb.append(cur).append(' ');

            for(int next : graph[cur]){
                if(!visited[next]) q.offer(next);
            }
        }
    }
}
