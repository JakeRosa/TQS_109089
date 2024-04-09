package bus.booking.services;

public interface CacheStatsService {
    void hit();

    void miss();

    void put();

    void getRequests();
}
