package bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 아이템 줍기
 */
public class PickupItem {
    public static final int[] dx = {0, 0, 1, -1};
    public static final int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) {
        int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
        int solution = solution(rectangle, 1, 3, 7, 8);
        System.out.println(solution);
    }

    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] dist = new int[101][101];
        int[][] border = new int[101][101];

        int n = rectangle.length;
        int[][] scaled = new int[n][4];

        for (int i = 0; i < n; i++) {
            int x1 = rectangle[i][0] * 2, y1 = rectangle[i][1] * 2;
            int x2 = rectangle[i][2] * 2, y2 = rectangle[i][3] * 2;
            scaled[i][0] = x1;
            scaled[i][1] = y1;
            scaled[i][2] = x2;
            scaled[i][3] = y2;

            for(int x = x1; x <= x2; x++) {
                for(int y = y1; y <= y2; y++) {
                    border[x][y] = 1;
                }
            }
        }

        for(int i=0; i<n; i++) {
            int x1 = scaled[i][0], y1 = scaled[i][1];
            int x2 = scaled[i][2], y2 = scaled[i][3];
            for (int x = x1 + 1; x < x2; x++) {
                for(int y = y1 + 1; y < y2; y++) {
                    border[x][y] = 0;
                }
            }
        }

        int startX = characterX * 2, startY = characterY * 2;
        int endX = itemX * 2, endY = itemY * 2;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY});

        dist[startX][startY] = 1;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];
            if(x == endX && y == endY) {
                return (dist[x][y] - 1) / 2;
            }

            for(int i=0; i<4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx > 100 || ny > 100) continue;
                if(dist[nx][ny] > 0 || border[nx][ny] == 0) continue;

                dist[nx][ny] = dist[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }
        System.out.println("No pickup item");
        return -1;
    }
}
