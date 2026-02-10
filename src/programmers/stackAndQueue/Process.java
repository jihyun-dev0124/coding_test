package programmers.stackAndQueue;

import java.util.*;

public class Process {
    public static void main(String[] args) {
        int[] priorities = new int[]{1, 1, 9, 1, 1, 1};
        int solution = solution(priorities, 0);
        System.out.println("solution = " + solution);
    }

    public static int solution(int[] priorities, int location) {
        int answer = 0;
        int l = location;

        Deque<Integer> process = new ArrayDeque<>(priorities.length);
        for (int i = 0; i < priorities.length; i++) {
            process.offer(priorities[i]);
        }

        Arrays.sort(priorities);
        int idx = priorities.length - 1;
        while (!process.isEmpty()) {
            int firstProcess = process.poll();
            int priority = priorities[idx - answer]; //가장 높은 우선순위
            if (priority == firstProcess) {
                answer++;
                l--;
                if (l < 0) break;
            }else{
                process.offer(firstProcess);
                l--;
                if(l < 0) l = process.size() - 1;  //위치 맨 뒤로 재설정
            }
        }

        return answer;
    }

}




