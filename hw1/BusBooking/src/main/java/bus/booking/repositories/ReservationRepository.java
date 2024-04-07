package bus.booking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import bus.booking.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

}
