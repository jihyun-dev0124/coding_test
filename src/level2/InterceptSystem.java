package level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;

// 요격 시스템
public class InterceptSystem {
    public static void main(String[] args) {
        int[][] targets = new int[][]{{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};
        int[][] targets2 = new int[][]{{1, 100}, {50, 51}, {99, 100}};

        int solution = solution(targets);
        System.out.println("solution = " + solution);
    }

    public static int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));

        int s = 0, e = 0;

        for (int[] target : targets) {
            if (e <= target[0]) {
                s = target[0];
                e = target[1];
                answer++;
                continue;
            }

            s = Math.min(s, target[0]);
            e = Math.min(e, target[1]);
        }

        return answer;


        /*double s = targets[0][0] + 0.1;
        double e = targets[0][1] - 0.1;

        for (int i = 1; i < targets.length; i++) {
            double ts = targets[i][0] + 0.1;
            double te = targets[i][1] - 0.1;

            if (Double.compare(s, ts) <= 0 && Double.compare(e, ts) >= 0){
                e = Math.min(e, te);
                continue;
            }

            s = ts;
            e = te;
            answer++;
        }

        return ++answer;*/
    }
}
