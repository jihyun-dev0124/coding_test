package hackerRank.frequencyQueries;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FreqencyQeries20260126 {
    private static Map<Integer, Integer> cntMap = new HashMap<>();
    private static TreeMap<Integer, Integer> freqMap = new TreeMap<>();

    interface FrequencyStore{
        void add(int x); //값 x를 1개 추가
        void remove(int x); //값 x를 1개 제거 (없으면 무시)
        int count(int x); //값 x의 현재 개수 반환
        int existsExact(int k); //빈도가 정확히 k값이 하나라도 있으면 1, 아니면 0
        int existsAtLeast(int k); //빈도가 k이상인 값 하나라도 있으면1, 아니면 0
    }

    static class FrequncyStoreImpl implements FrequencyStore{
        @Override
        public void add(int x) {
            int before = cntMap.getOrDefault(x, 0);
            int after = before + 1;

            cntMap.put(x, after);

            if(before > 0){
                freqMap.put(before, freqMap.get(before) - 1);
            }

            freqMap.merge(after, 1, Integer::sum);
        }

        @Override
        public void remove(int x) {
            int before = cntMap.getOrDefault(x, 0);
            if(before == 0) return;

            int after = before - 1;
            freqMap.put(before, freqMap.get(before) - 1);

            if (after == 0) {
                cntMap.remove(before);
            }else{
                cntMap.put(x, after);
                freqMap.merge(after, 1, Integer::sum);
            }

        }

        @Override
        public int count(int x) {
            Integer count = cntMap.get(x);
            return count == null ? 0 : count;
        }

        @Override
        public int existsExact(int k) {
            return freqMap.getOrDefault(k, 0) > 0 ? 1 : 0;
        }

        @Override
        public int existsAtLeast(int k) {
            return freqMap.lastKey() >= k ? 1 : 0;
        }
    }

    public static void main(String[] args) {
        FrequencyStore fs = new FrequncyStoreImpl();
        fs.add(1);
        fs.add(2);
        fs.add(3);
        fs.add(1);
        System.out.println(fs.count(1) == 2);  //true;
        System.out.println(fs.existsExact(2) == 1);
        System.out.println(fs.existsAtLeast(2) == 1);
        Assertions.assertEquals(2, fs.count(1));
        System.out.println("---------------------");

        fs.remove(1);
        fs.remove(1);
        System.out.println(fs.count(1) == 0);
    }

}
