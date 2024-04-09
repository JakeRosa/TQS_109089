package bus.booking.services.impl;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import bus.booking.entities.Reservation;
import bus.booking.entities.dto.ReservationDTO;
import bus.booking.repositories.ReservationRepository;
import bus.booking.repositories.TripRepository;
import bus.booking.services.ReservationService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    ReservationRepository reservationRepository;
    TripRepository tripRepository;

    @Override
    public Reservation createReservation(ReservationDTO reservationDTO) {
        ModelMapper modelMapper = new ModelMapper();

        Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);

        reservation.setTrip(tripRepository.findById(reservationDTO.getTripId()).orElse(null));

        reservation.setId(generateReservationId());

        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservationById(String id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public String generateReservationId() {
        String reservationId = UUID.randomUUID().toString().replace("-", "");

        return reservationId.substring(0, 16);
    }
}
