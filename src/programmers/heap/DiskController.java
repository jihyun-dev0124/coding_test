package programmers.heap;

import java.util.*;

public class DiskController {
    public static void main(String[] args) {
        int[][] jobs = new int[][]{{0, 7}, {0, 3}, {2, 9}, {3, 5}};
        int solution = solution(jobs);
        System.out.println("solution = " + solution);
    }

    public static int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt((int[] a) -> a[0]));

        PriorityQueue<int[]> waitQueue = new PriorityQueue<>(
                Comparator.comparingInt((int[] a) -> a[1])
                        .thenComparingInt(a -> a[0]));
        Collections.addAll(waitQueue, jobs);
        int[] peek = waitQueue.peek();
        System.out.println("peek = " + peek[0] + " , " + peek[1]);

        int len = jobs.length;
        int idx = 0, done = 0;
        int totalTime = 0, now = 0;

        while (done < len) {
            //현재 시간까지 도작한 작업들
            while (idx < len && jobs[idx][0] <= now) {
                waitQueue.offer(jobs[idx++]);
            }

            //현재 시간까지 도착한 작업이 없으면, 현재 시간 건너뛰기
            if (waitQueue.isEmpty()) {
                now = Math.max(now, jobs[idx][0]);
                continue;
            }

            int[] job = waitQueue.poll();
            int start = Math.max(now, job[0]);
            int end = start + job[1];
            now = end;
            totalTime += (end - job[0]);

            done++;
        }

        return totalTime / len;
    }
}