package bus.booking.entities;

import java.sql.Time;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "trips")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(nullable = false)
    private String origin;

    @NonNull
    @Column(nullable = false)
    private String destination;

    @NonNull
    @Column(nullable = false)
    private LocalDate date;

    @NonNull
    @Column(nullable = false)
    private Time departureTime;

    @NonNull
    @Column(nullable = false)
    private Time arrivalTime;

    @NonNull
    @Column(nullable = false)
    private String duration;

    @NonNull
    @Column(nullable = false)
    private double priceEuro;

    @NonNull
    @Column(nullable = false)
    private String busId;

    @NonNull
    @Column(nullable = false)
    private int availableSeats;
}
