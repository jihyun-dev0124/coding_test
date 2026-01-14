package leetcode.middle;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        int result = characterReplacement("ABAA", 0);
        System.out.println("result = " + result);
    }

    public static int characterReplacement(String s, int k) {
        int ans = 0;
        int maxFreq = 0;
        int[] count = new int[26];
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            int cur = s.charAt(right) - 'A';
            count[cur]++;
            maxFreq = Math.max(maxFreq, count[cur]);

            while ((right - left + 1) - maxFreq > k) {
                count[s.charAt(left) - 'A']--;
                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
