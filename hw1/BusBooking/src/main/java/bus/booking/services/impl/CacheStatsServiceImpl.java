package bus.booking.services.impl;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Service
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CacheStatsServiceImpl {
    private int hit;
    private int miss;
    private int put;

    public CacheStatsServiceImpl() {
        this.hit = 0;
        this.miss = 0;
        this.put = 0;
    }

    public void hit() {
        hit++;
    }

    public void miss() {
        miss++;
    }

    public void put() {
        put++;
    }

    public int getRequests() {
        return hit + miss;
    }
}
