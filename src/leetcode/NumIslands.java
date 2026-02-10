package leetcode;

public class NumIslands {
    private static int[] dr = {1, -1, 0, 0};
    private static int[] dc = {0, 0, 1, -1};
    private static boolean[][] visited;

    public static void main(String[] args) {
        char[][] grid = new char[][]{{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
        int i = numIslands(grid);


    }

    public static int numIslands(char[][] grid) {
        //주위가 0이면 섬
        visited = new boolean[grid.length][grid[0].length];

        int islandCnt = 0;
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if(visited[i][j] || grid[i][j] == '0') continue;
                dfs(i, j, grid);
                islandCnt++;
            }
        }

        return islandCnt;
    }

    public static void dfs(int r, int c, char[][] grid){
        visited[r][c] = true;

        for(int i=0; i<4; i++){
            int mr = r + dr[i];
            int mc = c + dc[i];
            if(mr < 0 || mr >= grid.length || mc < 0 || mc >= grid[0].length) continue;
            if(visited[mr][mc] || grid[mr][mc] == '0') continue;
            dfs(mr, mc, grid);
        }
}
}
