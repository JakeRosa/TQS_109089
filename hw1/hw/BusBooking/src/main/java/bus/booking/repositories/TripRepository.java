package bus.booking.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bus.booking.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByOriginAndDestinationAndDate(String origin, String destination, LocalDate date);
}
