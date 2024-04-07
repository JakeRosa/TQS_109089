package bus.booking.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @Column(unique = true, nullable = false)
    private String id;
    @Column(nullable = false)
    private String passengerName;
    @Column(nullable = false)
    private String passengerEmail;
    @Column(nullable = false)
    private String passengerPhone;
    @Column(nullable = false)
    private Date reservationDate;
    @Column(nullable = false)
    private String paymentMethod;
    @Column(nullable = false)
    private String currency;
    @Column(nullable = false)
    private double finalPrice;

    @ManyToOne
    @JoinColumn(name = "tripId", nullable = false)
    private Trip trip;
}
