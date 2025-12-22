package baekjoon.dfsAndbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon1707 {
    static int K, V, E;
    static List<Integer>[] graph;
    static int[] colors;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken()); //정점 개수
            E = Integer.parseInt(st.nextToken()); //간선 개수

            graph = new List[V+1];
            colors = new int[V+1];

            for(int j=1; j<=V; j++) graph[j] = new ArrayList<>();

            for(int j=0; j<E; j++){
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                graph[s].add(e);
                graph[e].add(s);
            }

            boolean result = true;
            for(int j=1; j<=V; j++){
                if(colors[j] != 0) continue;
                if(!bfs(j, 1)){
                    result = false;
                    break;
                }
            }

            if(result) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb.toString());
    }

    static boolean bfs(int start, int color){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        colors[start] = color;

        while(!q.isEmpty()){
            int cur = q.poll();
            for(int next : graph[cur]){
                if(colors[cur] == colors[next]) return false;
                if(colors[next] == 0){
                    colors[next] = colors[cur] * -1;
                    q.offer(next);
                }
            }
        }
        return true;
    }
}
