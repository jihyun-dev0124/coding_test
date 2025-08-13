package StackAndQueue;

import java.util.*;

public class Number {
    public static void main(String[] args) {
        int[] solution = solution(new int[]{1, 1, 3, 3, 0, 1, 1});
        for (int i : solution) {
            System.out.print(i + " ,");
        }
    }

    public static int[] solution(int []arr) {
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i : arr) {
            if(deque.size() == 0 || !deque.peekLast().equals(i)){
                deque.addLast(i);
            }
        }

        return deque.stream().mapToInt(Integer::intValue).toArray();
    }
}
