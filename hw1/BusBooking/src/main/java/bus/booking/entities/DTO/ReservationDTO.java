package bus.booking.entities.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private String passengerName;
    private String passengerEmail;
    private String passengerPhone;
    private Date reservationDate;
    private String paymentMethod;
    private String currency;
    private Long tripId;
    private double finalPrice;
}
