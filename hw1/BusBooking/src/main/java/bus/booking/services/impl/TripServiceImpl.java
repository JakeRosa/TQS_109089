package bus.booking.services.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import bus.booking.entities.Trip;
import bus.booking.repositories.TripRepository;
import bus.booking.services.TripService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;

    @Override
    public Set<String> getAllCities() {
        Set<String> cities = new HashSet<String>();

        tripRepository.findAll().forEach(trip -> {
            cities.add(trip.getOrigin());
            cities.add(trip.getDestination());
        });

        return cities;
    }

    @Override
    public List<Trip> getTripsByOriginDestinationDate(String origin, String destination, LocalDate date) {
        return tripRepository.findByOriginAndDestinationAndDate(origin, destination, date);
    }

    @Override
    public Trip getTripById(Long id) {
        return tripRepository.findById(id).orElse(null);
    }
}
