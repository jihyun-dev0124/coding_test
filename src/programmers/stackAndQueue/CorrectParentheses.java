package programmers.stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

//올바른 괄호
public class CorrectParentheses {
    public static void main(String[] args) {
        boolean solution = solution("(())()");
        System.out.println("solution = " + solution);
    }

    public static boolean solution(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        char[] charArray = s.toCharArray();

        for (char c : charArray) {
            Character last = deque.peek();
            if(last != null && !last.equals(c)){
                deque.pop();
                continue;
            }

            if (c == ')') return false;

            deque.push(c);
        }

        return deque.size() > 0 ? false : true;
    }
}
