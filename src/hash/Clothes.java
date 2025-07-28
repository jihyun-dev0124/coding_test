package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Clothes {
    public static void main(String[] args) {
        int result = solution(new String[][]{{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}});
        System.out.println("result = " + result);
    }

    public static int solution(String[][] clothes) {
        Map<String, Integer> clothesMap = new HashMap<>();

        for (String[] clothe : clothes) {
            clothesMap.merge(clothe[1], 1, Integer::sum);
        }

        int result = 1;
        for (Integer value : clothesMap.values()) {
            result *= (value + 1);
        }

        return result - 1;
    }
}
