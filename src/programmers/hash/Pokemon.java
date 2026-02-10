package programmers.hash;

import java.util.HashSet;
import java.util.Set;

public class Pokemon {
    public static void main(String[] args) {
        int result = solution(new int[]{1,2,3,4,5,6,7,8,9});
        System.out.println("result = " + result);
    }

    public static int solution(int[] nums) {
        int maxSize = nums.length / 2;

        Set<Integer> pokemon = new HashSet<>();

        for (int num : nums) {
            pokemon.add(num);
            if (maxSize <= pokemon.size()) {
                return maxSize;
            }
        }

        return pokemon.size();
    }


}
