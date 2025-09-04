package heap;

import java.util.*;

//이중 우선순위 큐
public class DualPriorityQueue {
    public static void main(String[] args) {
        String[] operations = new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        String[] operations2 = new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"};
        int[] solution = solution(operations2);
        System.out.println("solution[0] = " + solution[0] + ", " + solution[1]);
    }

    public static int[] solution(String[] operations) {
        int[] answer = new int[2];
        DualPriority dualPriority = new DualPriority();

        
        for (String o : operations) {
            if (o.startsWith("I")) {
                int num = Integer.parseInt(o.split("I ")[1]);
                dualPriority.add(num);
            } else if (o.startsWith("D")) {
                int num = Integer.parseInt(o.split("D ")[1]);
                if(num > 0){
                    dualPriority.pollMax();
                }else{
                    dualPriority.pollMin();
                }
            }
        }

        answer[0] = dualPriority.peekMax();
        answer[1] = dualPriority.peekMin();
        return answer;
    }
}

class DualPriority{
    public final TreeMap<Integer, Integer> priorityMap = new TreeMap<>();

    public void add(Integer num) {
        priorityMap.merge(num, 1, Integer::sum);
    }

    //최대값 조회
    public int peekMax() {
        return priorityMap.isEmpty() ? 0 : priorityMap.lastKey();
    }

    //최소값 조회
    public int peekMin() {
        return priorityMap.isEmpty() ? 0 : priorityMap.firstKey();
    }

    //최대값 삭제
    public void pollMax() {
        if(priorityMap.isEmpty()) return;
        remove(priorityMap.lastEntry());
    }

    //최소값 삭제
    public void pollMin() {
        if(priorityMap.isEmpty()) return;
        remove(priorityMap.firstEntry());
    }

    //삭제
    private void remove(Map.Entry<Integer, Integer> entry) {
        if(entry == null) return;
        int k = entry.getKey(), v = entry.getValue();
        if(v > 1) {
            priorityMap.put(k, v - 1);
        } else priorityMap.remove(k);
    }

}
