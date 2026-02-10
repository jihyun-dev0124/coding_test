package programmers.kakao;


import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
 * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
 * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
 * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
 * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
 * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
 *      만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
 * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
 */
public class NewIdRecommend {
    public String newIdRecommend(String newId) {
        return normalize(newId);
    }

    public Predicate<String> verification(){
        return Verification.isValidId;
    }

    //핵심 엔진
    private String normalize(String input){
        String s = input == null ? "" : input;
        List<UnaryOperator<String>> steps = StepRegistry.getSteps(Region.KR);
        for (UnaryOperator<String> step : steps) {
            s = step.apply(s);
        }

        return s;
    }

    //Step Library
    //static -> NewIdRecommend 인스턴스가 생성될때마다 함께 존재해야 하는 객체가 아니기 때문에(인스턴스 라이프 사이클과 무관),
    // ->왜? : 공유하는 외부 필드가 없음.
    // NewIdRecommend 인스턴스에 종속되지 않도록 static 내부 클래스로 선언함.
    private static class Steps{
        //소문자 변경
        String toLower(String s){
            return s.toLowerCase();
        }

        //알파벳 소문자, 숫자, ., _, - 제외하고 삭제
        String filterAllowed(String s) {
            return s.replaceAll("[^a-z0-9._-]", "");
        }

        //마침표 연속 두번 이상 -> 1번으로 수정
        String collapseDot(String s) {
            return s.replaceAll("\\.+", ".");
        }

        //빈 문자열이면 "a' 반환
        String emptyToA(String s) {
            return s.isEmpty() ? "a" : s;
        }

        //16자리 이상이면 15자리로 자르고, 마지막 마침표 삭제
        String limitLength(String s){
            if(s.length() < 16) return s;
            s = s.substring(0, 15);
            return collapseDot(s);
        }

        //2자리 이하면 3자리가 될때까지 마지막 문자 반복
        String padToLength(String s) {
            while (s.length() < 3) {
                s += s.charAt(s.length() - 1);
            }
            return s;
        }
    }

    enum Region{KR, JP, EN}

    //규칙셋 등록 - 이건 조건별로 steps를 다르게 구현이 필요할떄
    private static class StepRegistry{
        private static final Steps steps = new Steps();
        private static final Map<Region, List<UnaryOperator<String>>> RULES;
        //읽기 전용 공유 데이터
        static {
            Map<Region, List<UnaryOperator<String>>> rules = new HashMap<>();
            rules.put(Region.KR, Collections.unmodifiableList(Arrays.asList(
                    steps::toLower,
                    steps::filterAllowed,
                    steps::collapseDot,
                    steps::emptyToA,
                    steps::limitLength,
                    steps::padToLength
            )));

            RULES = Collections.unmodifiableMap(rules);
        }

        static List<UnaryOperator<String>> getSteps(Region region){
            if(RULES.get(region) == null) return Collections.emptyList();
            return RULES.get(region);
        }
    }

    //검증
    static class Verification{
        static Predicate<String> isAllowed = s -> s.matches("[a-z0-9._-]+");
        static Predicate<String> isCollapsedDot = s -> !s.contains("..");
        static Predicate<String> isTrimDot = s -> !s.isEmpty() && (s.charAt(0) != '.' && s.charAt(s.length() - 1) != '.');
        static Predicate<String> isNotEmpty = s -> !s.isEmpty();
        static Predicate<String> lengthRange = s -> s.length() >= 3 && s.length() <= 15;

        static Predicate<String> isValidId = isAllowed
                .and(isCollapsedDot)
                .and(isNotEmpty)
                .and(isTrimDot)
                .and(lengthRange);

    }
}
