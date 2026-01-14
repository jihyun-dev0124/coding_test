package leetcode.middle;

import java.util.*;

public class MinimumSizeSubarraySum {
    public static void main(String[] args) {
        int i = minSubArrayLen(213, new int[]{12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12});
        System.out.println("i = " + i);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 3);
        for (int j = 0; j < 4; j++) {
            map.merge(j, 1, Integer::sum);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);


        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2) - map.get(o1));

        List<Integer> result = new ArrayList<>();
        for(Integer key : keySet) {
            if (map.get(key) > 2) {
                result.add(key);
            }
            break;
        }

        Integer[] array = result.toArray(new Integer[result.size()]);

        int[] array1 = result.stream().mapToInt(Integer::intValue).toArray();


    }

    public static int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int min = n;

        Arrays.sort(nums);
        if(n==1 && nums[0] < target) return 0;
        if(nums[n-1] >= target) return 1;

        for(int i = 0; i < n-1; i++){
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int move = n - 1;
            int cnt = 1;
            int sum = nums[i];

            while(i < move && sum < target){
                sum += nums[move];
                move--;
                cnt++;
            }

            if (i == 4) {
                System.out.println("i = " + i);
                System.out.println("move = " + move);
                System.out.println("sum = " + sum);
            }

            if(i == 0 && sum < target){
                return 0;
            }

            if(sum >= target){
                min = Math.min(min, cnt);
            }
        }

        return min;
    }
}
