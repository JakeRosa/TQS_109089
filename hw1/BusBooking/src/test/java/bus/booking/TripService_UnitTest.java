package bus.booking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import bus.booking.entities.Trip;
import bus.booking.repositories.TripRepository;
import bus.booking.services.impl.TripServiceImpl;

@ExtendWith(MockitoExtension.class)
class TripService_UnitTest {
    @Mock(lenient = true)
    private TripRepository tripRepository;

    @InjectMocks
    private TripServiceImpl tripService;

    @BeforeEach
    void setUp() {
        Trip trip1 = new Trip(1L, "Porto", "Lisboa", LocalDate.of(2024, 05, 05), Time.valueOf("08:00:00"),
                Time.valueOf("12:00:00"), "4h", 20.0, "BUS001", 50);

        Trip trip2 = new Trip(2L, "Lisboa", "Porto", LocalDate.of(2024, 05, 05), Time.valueOf("08:00:00"),
                Time.valueOf("12:00:00"), "4h", 20.0, "BUS002", 50);

        List<Trip> trips = Arrays.asList(trip1, trip2);

        when(tripRepository.findByOriginAndDestinationAndDate("Porto", "Lisboa", LocalDate.of(2024, 05, 05)))
                .thenReturn(Arrays.asList(trip1));
        when(tripRepository.findByOriginAndDestinationAndDate("Lisboa", "Porto", LocalDate.of(2024, 05, 05)))
                .thenReturn(Arrays.asList(trip2));
        when(tripRepository.findById(1L)).thenReturn(java.util.Optional.of(trip1));
        when(tripRepository.findById(2L)).thenReturn(java.util.Optional.of(trip2));
        when(tripRepository.findAll()).thenReturn(trips);
    }

    @Test
    void testGetAllCities() {
        Set<String> cities = tripService.getAllCities();

        assertThat(cities, hasSize(2));

        verify(tripRepository, times(1)).findAll();
    }

    @Test
    void testGetTripsByOriginDestinationDate() {
        List<Trip> trips = tripService.getTripsByOriginDestinationDate("Porto", "Lisboa", LocalDate.of(2024, 05, 05));

        assertThat(trips, hasSize(1));

        verify(tripRepository, times(1)).findByOriginAndDestinationAndDate("Porto", "Lisboa",
                LocalDate.of(2024, 05, 05));
    }

    @Test
    void testGetTripById() {
        Trip trip = tripService.getTripById(1L);

        assertThat(trip.getOrigin(), is("Porto"));
        assertThat(trip.getDestination(), is("Lisboa"));
        assertThat(trip.getBusId(), is("BUS001"));

        verify(tripRepository, times(1)).findById(1L);
    }

}
