package hackerRank.predicatePrec;

import java.util.List;
import java.util.function.Predicate;

public class WindowAnalyzer {

    public static <T> int longestWindowWithKBad(List<T> events, Predicate<T> isGood, int k) {
        int left = 0;
        int badCount = 0;
        int best = 0;

        for(int right = 0; right < events.size(); right++) {
            if(!isGood.test(events.get(right))) {
                badCount++;
            }

            while (badCount > k) {
                if(!isGood.test(events.get(left))) {
                    badCount--;
                }
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }

}
