package hackerRank.predicatePrec;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MockTargetSelector {
    interface TargetSelector{
        List<User> select(List<User> users, int maxTargetCount);
    }

    static class TargetSelectorImpl implements TargetSelector{
        /*@Override
        public List<User> select(List<User> users, int maxTargetCount){
            return users.stream()
                    .filter(User::isActive)
                    .sorted(Comparator.comparingInt(User::getLastLoginDaysAgo)
                            .thenComparing((Comparator.comparing(User::getPurchaseCount).reversed()))
                    )
                    .limit(maxTargetCount)
                    .toList();
        }*/

        @Override
        public List<User> select(List<User> users, int maxTargetCount){
            PriorityQueue<User> heap = new PriorityQueue<>(
                    Comparator.comparingInt(User::getLastLoginDaysAgo).reversed()
                            .thenComparingInt(User::getPurchaseCount));

            for (User user : users) {
                if(!user.isActive) continue;
                heap.offer(user);

                if(heap.size() > maxTargetCount){
                    heap.poll();
                }
            }

            return new ArrayList<>(heap);
        }


    }

    class User{
        private Long id;
        private int lastLoginDaysAgo;
        private int purchaseCount;
        private boolean isActive;

        public User(Long id, int lastLoginDaysAgo, int purchaseCount, boolean isActive) {
            this.id = id;
            this.lastLoginDaysAgo = lastLoginDaysAgo;
            this.purchaseCount = purchaseCount;
            this.isActive = isActive;
        }

        public Long getId() {
            return this.id;
        }

        public int getLastLoginDaysAgo() {
            return this.lastLoginDaysAgo;
        }

        public int getPurchaseCount() {
            return this.purchaseCount;
        }

        public boolean isActive() {
            return this.isActive;
        }
    }
}
