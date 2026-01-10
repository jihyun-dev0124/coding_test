package baekjoon.dfsAndbfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Baekjoon7576 {
    static int M, N;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] box;
    static boolean[][] visited;
    static Queue<int[]> q;
    static int changeCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); //가로
        N = Integer.parseInt(st.nextToken()); //세로

        box = new int[N][M];
        visited = new boolean[N][M];
        q = new LinkedList();

        int emptyCnt = 0;
        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine());
            for(int c=0; c<M; c++){
                int tomato = Integer.parseInt(st.nextToken());
                box[r][c] = tomato;
                if(tomato == 1) q.add(new int[]{r, c, 0});
                if(tomato == -1) emptyCnt++;
            }
        }

        int tomatoCnt = (M*N) - emptyCnt; //총 토마토 수
        int ripeCnt = q.size(); //익어있는 토마토 수
        int unripeCnt = tomatoCnt - ripeCnt; //익어야하는 토마토 수

        //익은 토마토가 하나도 없는 경우
        if(ripeCnt == 0 && unripeCnt > 0) {
            System.out.println(-1);
            return;
        }

        //토마토가 모두 익어있는 경우
        if(unripeCnt == 0){
            System.out.println(0);
            return;
        }

        int days = bfs();
        if(changeCnt < unripeCnt){ //안익은 토마토가 존재하는 경우
            System.out.println(-1);
            return;
        }
        System.out.println(days);
    }

    static int bfs(){
        int curDepth = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int depth = cur[2];
            visited[r][c] = true;

            for(int j=0; j<4; j++){
                int mr = r + dy[j];
                int mc = c + dx[j];
                int mdepth = depth + 1;

                if(mr < 0 || mr >= N || mc < 0 || mc >= M) continue;
                if(visited[mr][mc] || box[mr][mc] != 0) continue;

                visited[mr][mc] = true;
                q.offer(new int[]{mr, mc, mdepth});
                changeCnt++;
                curDepth = mdepth;
            }
            /*System.out.println("depth = " + depth);
            System.out.println("curDepth = " + curDepth);*/
        }
        return curDepth;
    }
}
