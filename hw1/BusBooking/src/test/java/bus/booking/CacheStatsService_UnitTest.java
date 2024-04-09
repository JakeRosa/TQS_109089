package bus.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bus.booking.services.impl.CacheStatsServiceImpl;

class CacheStatsService_UnitTest {

    private CacheStatsServiceImpl cacheStatsService;

    @BeforeEach
    void setUp() {
        cacheStatsService = new CacheStatsServiceImpl();
    }

    @Test
    void testHit() {
        cacheStatsService.hit();
        assertEquals(1, cacheStatsService.getHit());
    }

    @Test
    void testMiss() {
        cacheStatsService.miss();
        assertEquals(1, cacheStatsService.getMiss());
    }

    @Test
    void testPut() {
        cacheStatsService.put();
        assertEquals(1, cacheStatsService.getPut());
    }

    @Test
    void testGetRequests() {
        cacheStatsService.hit();
        cacheStatsService.miss();
        assertEquals(2, cacheStatsService.getRequests());
    }
}