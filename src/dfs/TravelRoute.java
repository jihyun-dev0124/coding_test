package dfs;

import java.util.*;
import java.util.stream.Collectors;

public class TravelRoute {
    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] tickets2 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String[] solution = solution(tickets2);
        for (String s : solution) {
            System.out.print(s + ", ");
        }
    }

    public static List<String> answer = new ArrayList<>();
    public static boolean[] visited;
    public static boolean finished;

    public static String[] solution(String[][] tickets) {
        List<String> path = new ArrayList<>();
        visited = new boolean[tickets.length];
        Arrays.sort(tickets, Comparator.comparing((String[] t) -> t[0]).thenComparing(t -> t[1]));

        path.add("ICN");
        dfs("ICN", tickets, path, 0);

        return answer.toArray(new String[0]);
    }

    public static void dfs(String from, String[][] tickets, List<String> path, int used){
        if(finished) return;

        if(used == tickets.length){
            finished = true;
            answer = new ArrayList<>(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if(!from.equals(tickets[i][0]) || visited[i]) continue;

            visited[i] = true;
            path.add(tickets[i][1]);

            dfs(tickets[i][1], tickets, path,used + 1);

            visited[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
