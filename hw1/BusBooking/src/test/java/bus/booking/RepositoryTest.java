package bus.booking;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import bus.booking.entities.Reservation;
import bus.booking.entities.Trip;
import bus.booking.repositories.ReservationRepository;
import bus.booking.repositories.TripRepository;

@DataJpaTest
class RepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    void testTripRepository() throws ParseException {
        Trip trip = createTrip();

        entityManager.persistAndFlush(trip);

        Optional<Trip> foundTrip = tripRepository.findById(trip.getId());

        assertThat(foundTrip.get().getId()).isEqualTo(trip.getId());
    }

    @Test
    void testReservationRepository() throws ParseException {
        Trip trip = createTrip();

        entityManager.persistAndFlush(trip);

        Reservation reservation = new Reservation("1234567890", "John Smith Carabina",
                "john.smith.carabina@gmail.com",
                "123456789", new java.util.Date(), "Visa", "EUR", 20.0, trip);

        entityManager.persistAndFlush(reservation);

        Optional<Reservation> foundReservation = reservationRepository.findById(reservation.getId());

        assertThat(foundReservation.get().getId()).isEqualTo(reservation.getId());
    }

    Trip createTrip() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d1 = format.parse("08:00:00");
        java.sql.Time departureTime = new java.sql.Time(d1.getTime());

        java.util.Date d2 = format.parse("12:00:00");
        java.sql.Time arrivalTime = new java.sql.Time(d2.getTime());

        Trip trip = new Trip("Lisboa", "Porto", LocalDate.parse("2024-05-05"), departureTime, arrivalTime, "4h",
                20.0, "BUS001", 20);
        return trip;
    }
}
