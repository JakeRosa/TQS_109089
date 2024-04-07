package bus.booking.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import bus.booking.entities.Trip;

public interface TripService {
    Set<String> getAllCities();

    List<Trip> getTripsByOriginDestinationDate(String origin, String destination, LocalDate date);

    Trip getTripById(Long id);
}
