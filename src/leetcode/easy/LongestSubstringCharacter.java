package leetcode.easy;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringCharacter {
    public static void main(String[] args) {
        String s = "abcabcbb";
        int i = lengthOfLongestSubstring2(s);
        System.out.println("i = " + i);
    }
    public static int beforeLengthOfLongestSubstring(String s) {
        int len = s.length();
        int result = 0;
        int left = 0;
        String answers = "";

        StringBuilder sb = new StringBuilder(s);


        for(int right=0; right < len; right++){
            char cur = s.charAt(right);

            while(left < right && answers.indexOf(cur) >= 0){
                left++;
                answers = s.substring(left, right);
            }

            answers = s.substring(left, right + 1);

            result = Math.max(result, answers.length());
        }

        return result;
    }

    public static int lengthOfLongestSubstring1(String s) {
        int result = 0;
        StringBuilder sb = new StringBuilder(s);
        for (char c : s.toCharArray()) {
            String cur = String.valueOf(c);
            int index = sb.indexOf(cur);
            if (index != -1) {
                sb.delete(0, index + 1);
            }
            sb.append(cur);
            result = Math.max(result, sb.length());
        }
        return result;
    }

    public static int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int result = 0;

        for(int right = 0; right < s.length(); right++){
            char cur = s.charAt(right);

            while (set.contains(cur)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(cur);
            result = Math.max(result, right - left + 1);
        }

        return result;
    }
}
