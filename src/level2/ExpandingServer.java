package level2;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ExpandingServer {
    public static void main(String[] args) {
        //int solution = solution(new int[]{0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5}, 3, 5);
        int solution = solution(new int[]{0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1}, 1, 1);
        System.out.println("solution = " + solution);
    }

    public static int solution(int[] players, int m, int k) {
        Queue<Integer> servers = new ArrayDeque<>();
        int time = 0;
        int addServer = 0;
        for (int player : players) {
            removeServer(time, servers);
            int n = servers.size();
            int max = (n + 1) * m;
            if(player >= max){
                int expandServerCnt = (player - max + m) / m;
                addServer += expandServerCnt;
                expandServer(time+k, expandServerCnt, servers);
            }
            System.out.println("time: " + time + "~" + (time + 1) + ", player: "+player+", expandServer: " + servers.size() + ", addServer: " + addServer);
            time++;
        }

        return addServer;
    }

    public static void expandServer(int endTime, int expandServerCnt, Queue<Integer> servers) {
        for (int i = 0; i < expandServerCnt; i++) {
            servers.offer(endTime);
        }
    }

    public static void removeServer(int time, Queue<Integer> servers){ //endTime
        if(servers.isEmpty()) return;

        while (!servers.isEmpty()) {
            Integer endTime = servers.peek();
            if(time != endTime) break;
            servers.poll();
        }
    }


}


