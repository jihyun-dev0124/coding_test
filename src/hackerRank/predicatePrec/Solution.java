package hackerRank.predicatePrec;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Solution {
    static class Member {
        final String id;
        final int age;
        final boolean active;
        final boolean vip;
        final int point;

        Member(String id, int age, boolean active, boolean vip, int point) {
            this.id = id;
            this.age = age;
            this.active = active;
            this.vip = vip;
            this.point = point;
        }
    }

    interface FilterEngine {
        List<String> filter(List<Member> members, List<String> filters);
    }

    static class FilterEnginImpl implements FilterEngine {
        @Override
        public List<String> filter(List<Member> members, List<String> filters) {
            Predicate<Member> condition = m -> true; //조건 0개면 전부 통과

            for (String token : filters) {
                if(token == null || token.isEmpty()) {
                    throw new IllegalArgumentException("Empty filter token");
                }
                condition = condition.and(parseToken(token.trim()));
            }
            return members.stream()
                    .filter(condition)
                    .map(m -> m.id)
                    .collect(Collectors.toList());
        }

        private Predicate<Member> parseToken(String token) {
            if (token.startsWith("NOT:")) {
                String inner = token.substring("NOT:".length());
                if(inner.isBlank()) throw new IllegalArgumentException("NOT: requires inner token");
                return parseToken(inner.trim()).negate();
            }
            return null;
        }
    }


}

