package bus.booking.services;

import bus.booking.entities.Reservation;
import bus.booking.entities.dto.ReservationDTO;

public interface ReservationService {
    Reservation createReservation(ReservationDTO reservation);

    Reservation getReservationById(String id);
}
