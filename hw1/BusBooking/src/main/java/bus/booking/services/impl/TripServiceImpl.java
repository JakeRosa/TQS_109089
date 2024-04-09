package bus.booking.services.impl;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import bus.booking.entities.Trip;
import bus.booking.repositories.TripRepository;
import bus.booking.services.TripService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;

    static final Logger log = getLogger(lookup().lookupClass());

    @Override
    public Set<String> getAllCities() {
        log.info("Getting all cities from trips");

        Set<String> cities = new HashSet<>();

        tripRepository.findAll().forEach(trip -> {
            cities.add(trip.getOrigin());
            cities.add(trip.getDestination());
        });

        return cities;
    }

    @Override
    public List<Trip> getTripsByOriginDestinationDate(String origin, String destination, LocalDate date) {
        log.info("Getting trips by origin, destination and date");
        return tripRepository.findByOriginAndDestinationAndDate(origin, destination, date);
    }

    @Override
    public Trip getTripById(Long id) {
        log.info("Getting trip by ID: {}", id);
        return tripRepository.findById(id).orElse(null);
    }
}
