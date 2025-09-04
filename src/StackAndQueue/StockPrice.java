package StackAndQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StockPrice {
    public static void main(String[] args) {
        solution(new int[]{1, 2, 3, 2, 3});
    }

    public static int[] solution(int[] prices) {
        int len = prices.length;
        int[] answer = new int[len];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int idx = stack.pop();
                answer[idx] = i - idx;
            }
            stack.push(i);
        }

        //끝까지 하락하지 않은 인덱스
        while (!stack.isEmpty()) {
            Integer idx = stack.pop();
            answer[idx] = len - idx - 1;
        }

        return answer;
    }
}
