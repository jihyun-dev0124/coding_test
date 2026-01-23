package hackerRank.fixedWindowRateLimiter;

public interface RateLimiter {
    boolean allow(String clientId, long timestampMilis);
}
