package StackAndQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class StockPrice {
    public static void main(String[] args) {

    }

    public static int[] solution(int[] prices) {
        Deque<Integer> dequePrices = new ArrayDeque<>();
        for (int price : prices) {
            dequePrices.offer(price);
        }

        List<Integer> answerList = new ArrayList<>();
        int count = 0;
        while (!dequePrices.isEmpty()) {
            Integer firstPrice = dequePrices.poll();
            for (int price : dequePrices) {
                count++;
                if (firstPrice > price) {
                    break;
                }
            }
            answerList.add(count);
            count = 0;
        }


        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
