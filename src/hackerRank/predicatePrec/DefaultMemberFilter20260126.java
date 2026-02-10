package hackerRank.predicatePrec;

import java.util.List;
import java.util.function.Predicate;

public class DefaultMemberFilter20260126 {
    public interface MemberFilter {
        List<Member> filter(List<Member> members, Predicate<Member> condition);
    }

    public class DefunctMemberFilter implements MemberFilter {
        @Override
        public List<Member> filter(List<Member> members, Predicate<Member> condition) {
            return members.stream()
                    .filter(m -> condition.test(m))
                    .toList();
        }
    }

    public class Member {
        private final String id;
        private final int age;
        private final boolean active;
        private final String grade; // "NORMAL", "VIP", "VVIP"

        public Member(String id, int age, boolean active, String grade) {
            this.id = id;
            this.age = age;
            this.active = active;
            this.grade = grade;
        }

        public String getId() { return id; }
        public int getAge() { return age; }
        public boolean isActive() { return active; }
        public String getGrade() { return grade; }
    }



}
