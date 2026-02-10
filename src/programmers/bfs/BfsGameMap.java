package programmers.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class BfsGameMap {
    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        int[][] maps1 = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        int solution = solution(maps1);
        System.out.println("solution = " + solution);
    }

    public static int solution(int[][] maps){
        int n = maps.length; // 행
        int m = maps[0].length; // 열

        boolean[][] visited = new boolean[n][m];
        int[][] dist = new int[n][m];

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int h = now[0], r = now[1];

            if(h == n - 1 && r == m - 1){
                return dist[h][r];
            }

            for (int i = 0; i < 4; i++) {
                int mh = h + dx[i];
                int mr = r + dy[i];

                if(mh < 0 || mr < 0 || mh >= n || mr >= m) continue;
                if(visited[mh][mr] || maps[mh][mr] == 0) continue;

                visited[mh][mr] = true;
                dist[mh][mr] = dist[h][r] + 1;
                q.offer(new int[]{mh, mr});
            }
        }

        return -1;
    }
}
