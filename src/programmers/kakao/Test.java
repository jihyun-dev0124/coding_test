package programmers.kakao;

import java.util.function.Predicate;

public class Test {
    public static void main(String[] args) {
        NewIdRecommend newIdRecommend = new NewIdRecommend();
        String id = ".#$@#amdbmc/..!..234_1,..";
        String result = newIdRecommend.newIdRecommend(id);
        System.out.println("result = " + result);

        Predicate<String> verification = newIdRecommend.verification();
        System.out.println("verification = " + verification.test(id));
        System.out.println("verification = " + verification.test(result));


        NewIdRecommend2 newIdRecommend2 = new NewIdRecommend2();
        String solution = newIdRecommend2.solution(".#$%23..asdf-33!@#.");
        System.out.println("solution = " + solution);
    }
}
