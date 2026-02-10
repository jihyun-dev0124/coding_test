package programmers.fullSearch;

import java.util.ArrayDeque;
import java.util.Queue;

// 전력망을 둘로 나누기
public class ElectricGridDivision {
    static int[][] graph;

    public static void main(String[] args) {
        int solution = solution(9, new int[][]{{1, 3}, {2, 3}, {3, 4}, {4, 5}, {4, 6}, {4, 7}, {7, 8}, {7, 9}});
        System.out.println("solution = " + solution);
    }

    public static int solution(int n, int[][] wires) {
        int answer = n;

        graph = new int[n + 1][n + 1]; //n개의 노드가 있음.

        for (int i = 0; i < wires.length; i++) {
            int from = wires[i][0], to = wires[i][1];
            graph[from][to] = 1;
            graph[to][from] = 1;
        }

        for (int i = 0; i < wires.length; i++) {
            int from = wires[i][0], to = wires[i][1];
            //하나씩 끊어봄.
            graph[from][to] = 0;
            graph[to][from] = 0;
            answer = Math.min(answer, bfs(n, from));
            // 다시 연결
            graph[from][to] = 1;
            graph[to][from] = 1;
        }

        return answer;
    }

    public static int bfs(int n, int from) {
        boolean[] visited = new boolean[n + 1];
        visited[from] = true;

        Queue<Integer> que = new ArrayDeque<>();

        que.offer(from);

        int count = 1;
        while (!que.isEmpty()) {
            int temp = que.poll();
            for (int i = 1; i <= n; i++) {
                if (!visited[i] && graph[temp][i] == 1) {
                    visited[i] = true;
                    count++;
                    que.offer(i);
                }
            }
        }


        return (int) Math.abs(count - (n - count));
    }

}
