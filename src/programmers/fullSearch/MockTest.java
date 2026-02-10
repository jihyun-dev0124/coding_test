package programmers.fullSearch;

import java.util.*;

public class MockTest {
    public static void main(String[] args) {
        //int[] solution = solution(new int[]{1, 2, 3, 4, 5});
        int[] solution = solution(new int[]{1, 3, 2, 4, 2});

        for (int i : solution) {
            System.out.println("i = " + i);
        }
    }

    public static int[] solution(int[] answers) {
        int acnt = 0, bcnt=0, ccnt =0;
        int[] a = {1,2,3,4,5};
        int[] b = {2,1,2,3,2,4,2,5};
        int[] c = {3,3,1,1,2,2,4,4,5,5};

        int alen = a.length, blen = b.length, clen = c.length;
        for (int i = 0; i < answers.length; i++) {
            if(a[(alen + i) % alen] == answers[i]) acnt++;
            if(b[(blen + i) % blen] == answers[i]) bcnt++;
            if(c[(clen + i) % clen] == answers[i]) ccnt++;
        }

        return select(acnt, bcnt, ccnt);
    }

    public static int[] select(int a, int b, int c){
        List<Integer> result = new ArrayList<>();
        int max = Math.max(a, Math.max(b, c));
        if(max == a) result.add(1);
        if(max == b) result.add(2);
        if(max == c) result.add(3);

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
