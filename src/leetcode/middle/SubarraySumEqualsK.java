package leetcode.middle;

import java.util.*;

public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int i = subarraySum(new int[]{1, 2, 3, -3, 3}, 3);
        System.out.println("i = " + i);

    }

    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        int sum = 0;
        int ans = 0;
        for (int num : nums) {
            sum += num;
            ans += prefix.getOrDefault(sum - k, 0);
            prefix.put(sum, prefix.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}
