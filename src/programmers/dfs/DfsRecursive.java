package programmers.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DfsRecursive {
    public static boolean[] visited;
    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(1).addAll(Arrays.asList(2, 3));
        graph.get(2).addAll(Arrays.asList(1, 4, 5));
        graph.get(3).addAll(Arrays.asList(1, 5));
        graph.get(4).addAll(Arrays.asList(2, 5, 6));
        graph.get(5).addAll(Arrays.asList(2, 3, 4, 6));
        graph.get(6).addAll(Arrays.asList(4, 5));

        visited = new boolean[graph.size() + 1];
        dfs(1, graph);
    }

    public static void dfs(int node, List<List<Integer>> graph) {
        visited[node] = true; // 현재 노드 방문 처리
        System.out.print(node + " ");
        for (int i : graph.get(node)) {
            if (!visited[i]) { // 인접 노드 방문하지 않은 경우
                dfs(i, graph); // 재귀함수 호출
            }
        }
    }
}
