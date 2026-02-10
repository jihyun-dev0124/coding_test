package programmers.fullSearch;

public class minRectangle {
    public static void main(String[] args) {
        int[][] sizes = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
        //int[][] sizes2 = {[10, 7], [12, 3], [8, 15], [14, 7], [5, 15]};
        int solution = solution(sizes);
        System.out.println("solution = " + solution);
    }

    public static int solution(int[][] sizes) {
        int maxW = 0, maxH = 0;
        for (int[] size : sizes) {
            int max = Math.max(size[0], size[1]);
            int min = Math.min(size[0], size[1]);

            maxW = Math.max(maxW, max);
            maxH = Math.max(maxH, min);
        }
        return maxW * maxH;
    }
}
