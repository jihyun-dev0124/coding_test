package programmers.kakao;

import java.util.*;
import java.util.List;
import java.util.function.UnaryOperator;

interface StepFactory{
    List<UnaryOperator<String>> getSteps();
}

public class DefaultStepFactory implements StepFactory {
    @Override
    public List<UnaryOperator<String>> getSteps() {
        List<UnaryOperator<String>> steps = Arrays.asList(
                this::toLower,
                this::filterAllowed,
                this::collapseDot,
                this::emptyToA,
                this::limitLength,
                this::padToLength
        );

        return steps;
    }

    //소문자 변경
    private String toLower(String s){
        return s.toLowerCase();
    }

    //알파벳 소문자, 숫자, ., _, - 제외하고 삭제
    private String filterAllowed(String s) {
        return s.replaceAll("[^a-z0-9._-]", "");
    }

    //마침표 연속 두번 이상 -> 1번으로 수정
    private String collapseDot(String s) {
        return s.replaceAll("\\.+", "");
    }

    //빈 문자열이면 "a' 반환
    private String emptyToA(String s) {
        return s == null ? "a" : s;
    }

    //16자리 이상이면 15자리로 자르고, 마지막 마침표 삭제
    private String limitLength(String s){
        if(s.length() < 16) return s;
        s = s.substring(0, 15);
        return collapseDot(s);
    }

    //2자리 이하면 3자리가 될때까지 마지막 문자 반복
    public String padToLength(String s) {
        while (s.length() < 3) {
            s += s.charAt(s.length() - 1);
        }

        return s;
    }

}
