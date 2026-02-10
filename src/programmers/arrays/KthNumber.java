package programmers.arrays;

import java.util.Arrays;

//k번째 수
public class KthNumber {
    public static void main(String[] args) {
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commnads ={{2, 5, 3},{4, 4, 1},{1, 7, 3}};
        int[] solution = solution(array, commnads);

        for (int i : solution) {
            System.out.println(i);
        }
    }

    public static int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int c = 0; c < commands.length; c++) {
            int[] command = commands[c];
            int i = command[0] - 1, j = command[1], k = command[2] - 1;
            int[] copyInts = Arrays.copyOfRange(array, i, j);
            Arrays.sort(copyInts);
            answer[c] = copyInts[k];
        }

        return answer;
    }
}
