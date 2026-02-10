package programmers.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// 가장 큰 수
public class LargestNumber {
    public static void main(String[] args) {
        int[] numbers = {3, 30, 34, 5, 9}; // 답은 9534330,
        String solution = solution(numbers);
        System.out.println("solution = " + solution);
    }

    public static String solution(int[] numbers) {
        List<String> strings = Arrays.stream(numbers).mapToObj(n -> String.valueOf(n)).collect(Collectors.toList());
        Collections.sort(strings, ((o1, o2) -> (o2 + o1).compareTo(o1 + o2)));

        if(strings.get(0).equals("0")) return  "0";

        return strings.stream().collect(Collectors.joining());
    }
}
