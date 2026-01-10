package baekjoon.dfsAndbfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon4963 {
    static int w, h;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1, 1, -1, -1, 1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    static int res;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            String read = br.readLine();
            if(read.equals("0 0")) break;

            StringTokenizer st = new StringTokenizer(read);
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            res = 0;
            visited = new boolean[h][w];
            graph = new int[h][w];

            for(int i=0; i<h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++){
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int r=0; r<h; r++){
                for(int c=0; c<w; c++){
                    if(visited[r][c] || graph[r][c] == 0) continue;
                    bfs(r, c);
                    res++;
                }
            }

            System.out.println(res);
        }
    }

    static void bfs(int r, int c){
        visited[r][c] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            for(int i=0; i<8; i++){
                int mr = cur[0] + dy[i];
                int mc = cur[1] + dx[i];
                if(mc < 0 || mc >= w || mr < 0 || mr >= h) continue;
                if(visited[mr][mc] || graph[mr][mc] == 0) continue;

                visited[mr][mc] = true;
                q.add(new int[]{mr, mc});
            }
        }
    }

    static void dfs(int r, int c){
        visited[r][c] = true;

        for(int i=0; i<8; i++){
            int mr = r + dy[i];
            int mc = c + dx[i];

            if(mc < 0 || mc >= w || mr < 0 || mr >= h) continue;
            if(visited[mr][mc] || graph[mr][mc] == 0) continue;

            dfs(mr, mc);
        }
    }
}
