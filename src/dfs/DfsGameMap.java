package dfs;

// 게임 맵 최단 거리
public class DfsGameMap {
//    public static int[] dx = {0, 0, 1, -1};
//    public static int[] dy = {1, -1, 0, 0};
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
    public static boolean[][] visited;
    public static int answer = -1;
    public static int n, m, count;

    public static void main(String[] args) {
        int[][] maps1 = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        int[][] maps2 = {{1,0,1,1,1}, {1,0,1,0,1}, {1,0,1,1,1}, {1,1,1,0,0}, {0,0,0,0,1}};

        int solution = solution(maps1);
        System.out.println("solution = " + solution);
    }

    public static int solution(int[][] maps) {
        n = maps.length; // 행
        m = maps[0].length; // 열

        visited = new boolean[n][m];

        dfs(0, 0, maps);

        return answer;
    }

    public static void dfs(int h, int r, int[][] maps) {
        if(maps[h][r] == 0) return;
        visited[h][r] = true;
        count++;

        System.out.println("h = " + h + ", r = " + r + ", count = " + count);

        if(h == m - 1 && r == n - 1){
            if(answer == -1) answer = count;
            answer = Math.min(count, answer);

            visited[h][r] = false;
            count--;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int mh = h + dx[i]; // 행
            int mr = r + dy[i]; // 열
            if(mh < 0 || mr < 0 || mh >= n || mr >= m
                    || maps[mh][mr] == 0 || visited[mh][mr]) continue;

            dfs(mh, mr, maps);
        }

        visited[h][r] = false;
        count--;
    }
}
