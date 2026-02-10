package programmers.heap;

import java.util.PriorityQueue;
import java.util.stream.IntStream;

//더 맴게
public class Scoville {
    public static void main(String[] args) {
        int solution = solution(new int[]{1, 2, 3, 9, 10, 12}, 7);
        System.out.println("solution = " + solution);
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(IntStream.of(scoville).boxed().toList());

        while (queue.peek() < K) {
            if (queue.size() == 1) {
                return  -1;
            }
            int mix = queue.poll() + (queue.poll() * 2);
            queue.offer(mix);
            answer++;
        }

        return answer;
    }
}
