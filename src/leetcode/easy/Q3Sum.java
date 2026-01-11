package leetcode.easy;

import java.util.*;

public class Q3Sum {
    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1,0,1,2,-1,-4});
        System.out.println(lists.toString());
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        //세 수의 합이 0인 배열
        //two pointer
        //정렬을 해서 중복 처리를 쉽게 만들고 i를 하나 고정한 뒤 나머지 구간을 투포인터로 탐색합니다.
        //합이 작으면 left를 늘려 합을 키우고
        //합이 크면 right를 줄여 합을 낮춥니다.
        //합이 0이면 결과에 추가한 뒤 중복값을 스킵해 결과 중복을 방지합니다. 전체 시간은 O(n2)

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            if(nums[i] > 0) break;
            if(i > 0 && nums[i] == nums[i-1]) continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while(left < right && nums[left] == nums[left + 1]) left++;
                    while(left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }

        }

        return res;
    }



}
