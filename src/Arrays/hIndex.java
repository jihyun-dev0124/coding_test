package Arrays;

import java.util.*;
import java.util.Arrays;
import java.util.Comparator;

// H index
public class hIndex {
    public static void main(String[] args) {
        int solution = solution(new int[]{2, 3, 4, 7, 6, 1, 5});
        System.out.println("solution = " + solution);
    }

    public static int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);
        int len = citations.length;
        for (int i = 0; i < len; i++) {
            int h = len - i;
            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }
        return answer;
    }
}
