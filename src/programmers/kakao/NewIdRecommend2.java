package programmers.kakao;

import java.util.*;

public class NewIdRecommend2 {
    public String solution(String new_id){
        String s = new_id == null ? "" : new_id;
        int[][] board = new int[s.length()][s.length()];
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        for(int[] b : board){
            for(int i=b.length; i<b.length; i++){
                map.computeIfAbsent(i+1, k -> new ArrayDeque<>()).push(b[i]);
            }
        }

        String id = new NewId(s)
                .toLower()
                .filter()
                .collapseDot()
                .trimDot()
                .emptyToA()
                .limitLength()
                .addToLength()
                .getId();

        return id;
    }


    private static class NewId{
        private String s;

        NewId(String s) {
            this.s = s;
        }

        private NewId toLower() {
            s = s.toLowerCase();
            return this;
        }

        private NewId filter(){
            s = s.replaceAll("[^a-z0-9._-]", "");
            return this;
        }

        private NewId collapseDot() {
            s = s.replaceAll("[.]{2,}", ".");
            return this;
        }

        private NewId trimDot() {
            s = s.replaceAll("^[.]|[.]$", "");
            return this;
        }

        private NewId emptyToA() {
            s = s.isEmpty() ? "a" : s;
            return this;
        }

        private NewId limitLength() {
            if(s.length() < 16) return this;
            s = s.substring(0, 15);
            s = s.replaceAll("[.]$", "");
            return this;
        }

        private NewId addToLength() {
            while (s.length() < 3) {
                s += s.charAt(s.length() - 1);
            }

            return this;
        }

        private String getId() {
            return this.s;
        }
    }

}
