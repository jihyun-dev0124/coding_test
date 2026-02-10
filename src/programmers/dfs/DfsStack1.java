package programmers.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DfsStack1 {
    public static boolean[] visited;
    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(2);
        graph.get(1).add(3);
        graph.get(2).add(1);
        graph.get(2).add(4);
        graph.get(2).add(5);
        graph.get(3).add(1);
        graph.get(3).add(5);
        graph.get(4).add(2);
        graph.get(4).add(5);
        graph.get(4).add(6);
        graph.get(5).add(2);
        graph.get(5).add(3);
        graph.get(5).add(4);
        graph.get(5).add(6);
        graph.get(6).add(4);
        graph.get(6).add(5);

        dfs(1, graph);
    }

    public static void dfs(int start, List<List<Integer>> graph) {
        visited = new boolean[graph.size() + 1];
        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                System.out.println();
                System.out.println("node : " + node);

                for (int i : graph.get(node)) {
                    if (!visited[i]) {
                        System.out.print("    i = " + i + " ");
                        stack.push(i);
                       // System.out.println("stack.push(i) = " + i);
                    }
                }

            }

        }

    }
}
