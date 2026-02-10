package hackerRank.queueUsingTwoStacks;

import java.util.*;

public class RateLimiter20260125 {
    private static final long WINDOW = 60_000;

    interface RateLimiter{
        boolean allow(String userId, long timestampMillis);
    }

    static class SlidingWindowRateLimiter implements RateLimiter{
        private int limit;
        Map<String, Deque<Long>> requests = new HashMap<>();

        public SlidingWindowRateLimiter(int limit) {
            this.limit = limit;
        }

        @Override
        public boolean allow(String userId, long timestampMillis) {
            if(limit <= 0) return false;

            Deque<Long> bucket = requests.computeIfAbsent(userId, k -> new ArrayDeque<>());
            while (!bucket.isEmpty() && timestampMillis - bucket.peek() >= WINDOW) {
                bucket.poll();
            }

            if(bucket.size() > limit) return false;

            bucket.offer(timestampMillis);

            return true;
        }
    }
}
