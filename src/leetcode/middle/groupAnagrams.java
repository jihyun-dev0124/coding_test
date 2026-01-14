package leetcode.middle;

import java.util.*;

public class groupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        groupAnagrams(strs);
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        //문자열의 애너그램이 동일한 것끼리 묶어 반환하는 문제로 동일한 키를 기준으로 값을 매핑하는 방식으로 해결
        //HashMap의 key는 중복된 값을 허용하지 않으므로 HashMap을 사용했고
        //각 문자열을 오름차순으로 정렬하여 key로 넣고 동일한 값이 있으면 list에 담음. -> 구현이 단순하고 쉬움
        //키를 사용하기 위해 문자열 정렬을 사용하는데 길이가 길어질수록 비용이 커지고,
        //모든 문자열을 HashMap에 담기 때문에 입력이 커지면 메모리 사용량도 증가함.

        List<List<String>> res = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(str);
        }

        res.addAll(map.values());


        return res;
    }

}
