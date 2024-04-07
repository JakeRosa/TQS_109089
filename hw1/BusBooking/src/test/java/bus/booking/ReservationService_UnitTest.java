package bus.booking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bus.booking.entities.Reservation;
import bus.booking.entities.Trip;
import bus.booking.entities.DTO.ReservationDTO;
import bus.booking.repositories.ReservationRepository;
import bus.booking.repositories.TripRepository;
import bus.booking.services.impl.ReservationServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ReservationService_UnitTest {
        @Mock(lenient = true)
        private ReservationRepository reservationRepository;

        @Mock(lenient = true)
        private TripRepository tripRepository;

        @InjectMocks
        private ReservationServiceImpl reservationService;

        private ReservationDTO reservationDTO;
        private Reservation reservation;

        @BeforeEach
        public void setUp() {
                Date date = new java.util.Date();
                reservationDTO = new ReservationDTO("John Smith Carabina",
                                "john.smith.carabina@gmail.com",
                                "123456789", date, "Visa", "EUR", 1L, 20.0);
                Trip trip = new Trip(1L, "Porto", "Lisboa", LocalDate.of(2024, 05, 05), Time.valueOf("08:00:00"),
                                Time.valueOf("12:00:00"), "4h", 20.0, "BUS001", 50);
                reservation = new Reservation("1234567890", "John Smith Carabina",
                                "john.smith.carabina@gmail.com",
                                "123456789", date, "Visa", "EUR", 20.0, trip);

                when(tripRepository.findById(1L)).thenReturn(java.util.Optional.of(trip));
                when(reservationRepository.findById("1234567890")).thenReturn(java.util.Optional.of(reservation));
                when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        }

        @Test
        public void testCreateReservation() {
                assertThat(reservationService.createReservation(reservationDTO)).isEqualTo(reservation);

                verify(tripRepository, times(1)).findById(1L);
        }

        @Test
        public void testGetReservationById() {
                assertThat(reservationService.getReservationById("1234567890")).isEqualTo(reservation);

                verify(reservationRepository, times(1)).findById("1234567890");
        }

}
