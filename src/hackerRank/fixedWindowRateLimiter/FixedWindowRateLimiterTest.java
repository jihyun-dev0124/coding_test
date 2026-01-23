package hackerRank.fixedWindowRateLimiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FixedWindowRateLimiterTest {

    @Test
    void samWindow_allowsUpToMaxRequests() {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(2, 1000);
        assertTrue(limiter.allow("A", 100));
        assertTrue(limiter.allow("A", 200));
        assertFalse(limiter.allow("A", 300));
    }

    @Test
    void newWindow_resetsCounter() {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(2, 1000);
        assertTrue(limiter.allow("A", 100));
        assertTrue(limiter.allow("A", 100));
        assertFalse(limiter.allow("A", 100));

        //new window
        assertTrue(limiter.allow("A", 1000));
    }

    @Test
    void differentClients_areIsolated() {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(2, 1000);
        assertTrue(limiter.allow("A", 100));
        assertTrue(limiter.allow("B", 100));
        assertTrue(limiter.allow("A", 100));
    }

    @Test
    void windowBoundary_isCalculatedCorrectly() {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(1, 1000);
        assertTrue(limiter.allow("A", 999)); //window 0
        assertTrue(limiter.allow("A", 1000)); //window 1000 (new)
    }

    @Test
    void nonMononicTimestamp_isHandledByEventTime() {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(1, 1000);
        assertTrue(limiter.allow("A", 1000)); //window 1000
        assertTrue(limiter.allow("A", 100)); //window 100(reset)
        assertTrue(limiter.allow("A", 200)); //window 0
        assertFalse(limiter.allow("A", 400)); //window 0 exceeds
    }




}