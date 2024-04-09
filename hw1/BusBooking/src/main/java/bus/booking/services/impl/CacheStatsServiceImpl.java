package bus.booking.services.impl;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;
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
    static final Logger log = getLogger(lookup().lookupClass());

    public CacheStatsServiceImpl() {
        this.hit = 0;
        this.miss = 0;
        this.put = 0;
    }

    public void hit() {
        log.info("Cache hit");
        hit++;
    }

    public void miss() {
        log.info("Cache miss");
        miss++;
    }

    public void put() {
        log.info("Cache put");
        put++;
    }

    public int getRequests() {
        return hit + miss;
    }
}
