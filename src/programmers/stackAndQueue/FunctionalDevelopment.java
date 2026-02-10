package programmers.stackAndQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FunctionalDevelopment {
    public static void main(String[] args) {
        int[] progresses = new int[]{95, 90, 99, 99, 80, 99};
        int[] speeds = new int[]{100, 1, 1, 1, 1, 1};
        int[] solution = solution(progresses, speeds);
        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }

    public static int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> workDay = new ArrayDeque<>();
        List<Integer> workList = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            int remain = 100 - progresses[i];
            int day = (remain + speeds[i] - 1) / speeds[i]; // 공식!!!!!!
            Integer firstWorkDay = workDay.peek();
            if(firstWorkDay != null && firstWorkDay < day){
                workList.add(workDay.size());
                workDay.clear();
            }
            workDay.offer(day);
        }

        if (workDay.size() > 0) {
            workList.add(workDay.size());
            workDay.clear();
        }

        return workList.stream().mapToInt(Integer::intValue).toArray();
    }
}
