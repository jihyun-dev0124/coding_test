package hackerRank.fixedWindowRateLimiter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowMillis;
    private final Map<String, WindowCounter> state = new HashMap<>();

    private static class WindowCounter {
        long windowStart;
        int count;
        long lastSeen;

        public WindowCounter(long windowStart, int count, long lastSeen) {
            this.windowStart = windowStart;
            this.count = count;
            this.lastSeen = lastSeen;
        }
    }

    private long calls = 0;
    private final long cleanupEvery; // e.g., every 10_000 calls
    private final long retentionMillis; // e.g., keep entries seen within last X ms

    public FixedWindowRateLimiter(int maxRequests, long windowMillis) {
        if(maxRequests<=0) throw new IllegalArgumentException("maxRequests must be greater than 0");
        if(windowMillis<=0) throw new IllegalArgumentException("windowMillis must be greater than 0");

        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;

        //Optional tuning (can be simplified/removed)
        this.cleanupEvery = 10_000; //10초
        this.retentionMillis = windowMillis * 10; // keep last 10 windows worth of activity
    }

    @Override
    public boolean allow(String clientId, long timestampMilis) {
        if(clientId == null) throw new IllegalArgumentException("clientId cannot be null");
        if(timestampMilis <= 0) throw new IllegalArgumentException("timestampMilis must be greater than 0");

        long windowStart = (timestampMilis / windowMillis) * windowMillis;
        WindowCounter wc = state.get(clientId);
        if(wc==null) {
            state.put(clientId, new WindowCounter(windowStart, 1, timestampMilis));
            maybeCleanup(timestampMilis);
            return true;
        }

        wc.lastSeen = timestampMilis;
        if(wc.windowStart != windowStart) {
            wc.windowStart = windowStart;
            wc.count = 1;
            maybeCleanup(timestampMilis);
            return true;
        }

        //same window
        if(wc.count < maxRequests) {
            wc.count++;
            maybeCleanup(timestampMilis);
            return true;
        }

        maybeCleanup(timestampMilis);
        return false;
    }

    private void maybeCleanup(long now) {
        calls++;
        if(calls % cleanupEvery != 0) return;
        //Remove entries that haven't been seen for retentionMillis (best-effort)
        //Note: since timestamps are not monotonic, "now" could be lower than some lastSeen.
        //We only remove when now >= lastSeen and the gap is large
        Iterator<Map.Entry<String, WindowCounter>> it = state.entrySet().iterator();
        while(it.hasNext()) {
            WindowCounter wc = it.next().getValue();
            if(now >= wc.windowStart && (now - wc.lastSeen) > retentionMillis) {
                it.remove();
            }
        }
    }
}


