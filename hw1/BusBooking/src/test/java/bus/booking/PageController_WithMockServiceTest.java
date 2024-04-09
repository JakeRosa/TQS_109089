package bus.booking;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import bus.booking.controllers.PageController;
import bus.booking.entities.Reservation;
import bus.booking.entities.Trip;
import bus.booking.entities.dto.ReservationDTO;
import bus.booking.services.impl.CurrencyServiceImpl;
import bus.booking.services.impl.ReservationServiceImpl;
import bus.booking.services.impl.TripServiceImpl;

@WebMvcTest(PageController.class)
class PageController_WithMockServiceTest {
        @Autowired
        private MockMvc mvc;

        @MockBean
        private TripServiceImpl tripService;

        @MockBean
        private ReservationServiceImpl reservationService;

        @MockBean
        private CurrencyServiceImpl currencyService;

        private Trip trip1;
        private ReservationDTO reservationDTO;
        private Reservation reservation;

        @BeforeEach
        void setUp() throws Exception {
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                java.util.Date d1 = format.parse("08:00:00");
                java.sql.Time departureTime = new java.sql.Time(d1.getTime());

                java.util.Date d2 = format.parse("12:00:00");
                java.sql.Time arrivalTime = new java.sql.Time(d2.getTime());

                trip1 = new Trip(1L, "Lisboa", "Porto", LocalDate.parse("2024-05-05"), departureTime, arrivalTime, "4h",
                                20.0,
                                "bus1", 50);

                reservationDTO = new ReservationDTO();
                reservationDTO.setTripId(1L);
                reservationDTO.setPassengerName("John Smith Carabina");
                reservationDTO.setPassengerEmail("jonh.smith.carabina@example.com");
                reservationDTO.setPassengerPhone("123456789");
                reservationDTO.setReservationDate(new java.util.Date());
                reservationDTO.setPaymentMethod("Visa");
                reservationDTO.setCurrency("EUR");
                reservationDTO.setTripId(1L);
                reservationDTO.setFinalPrice(20.0);

                reservation = new Reservation();
                reservation.setId("1234567890");
                reservation.setTrip(trip1);
                reservation.setPassengerName("John Smith Carabina");
                reservation.setPassengerEmail("john.smith.carabina@example.com");
                reservation.setPassengerPhone("123456789");
                reservation.setReservationDate(new java.util.Date());
                reservation.setPaymentMethod("Visa");
                reservation.setCurrency("EUR");
                reservation.setFinalPrice(20.0);
        }

        @Test
        void testIndex() throws Exception {
                when(tripService.getAllCities())
                                .thenReturn(new HashSet<>(Arrays.asList("Lisboa", "Porto", "Coimbra", "Faro", "Braga",
                                                "Aveiro")));

                mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
                                .andExpect(model().attribute("cities",
                                                new HashSet<>(Arrays.asList("Lisboa", "Porto", "Coimbra", "Faro",
                                                                "Braga", "Aveiro"))));
        }

        @Test
        void testResultsWithEUR() throws Exception {
                when(tripService.getTripsByOriginDestinationDate("Lisboa", "Porto", LocalDate.parse("2024-05-05")))
                                .thenReturn(Arrays.asList(trip1));

                when(currencyService.getAllCurrencies()).thenReturn(Arrays.asList("EUR", "USD"));

                when(currencyService.convertCurrency(trip1.getPriceEuro(), "EUR")).thenReturn(trip1.getPriceEuro());

                Map<Trip, Double> tripsFound = new HashMap<>();

                tripsFound.put(trip1, trip1.getPriceEuro());

                mvc.perform(get("/results").param("origin", "Lisboa").param("destination", "Porto")
                                .param("date", "2024-05-05")
                                .param("currency", "EUR")).andExpect(status().isOk()).andExpect(view().name("results"))
                                .andExpect(model().attribute("origin", "Lisboa"))
                                .andExpect(model().attribute("destination", "Porto"))
                                .andExpect(model().attribute("date", "2024-05-05"))
                                .andExpect(model().attribute("currency", "EUR"))
                                .andExpect(model().attribute("results", tripsFound))
                                .andExpect(model().attribute("currencies", Arrays.asList("EUR", "USD")));
        }

        @Test
        void testResultsWithUSD() throws Exception {
                when(tripService.getTripsByOriginDestinationDate("Lisboa", "Porto", LocalDate.parse("2024-05-05")))
                                .thenReturn(Arrays.asList(trip1));

                when(currencyService.getAllCurrencies()).thenReturn(Arrays.asList("EUR", "USD"));

                when(currencyService.convertCurrency(trip1.getPriceEuro(), "USD")).thenReturn(22.0);

                Map<Trip, Double> tripsFound = new HashMap<>();

                tripsFound.put(trip1, 22.0);

                mvc.perform(get("/results").param("origin", "Lisboa").param("destination", "Porto")
                                .param("date", "2024-05-05")
                                .param("currency", "USD")).andExpect(status().isOk()).andExpect(view().name("results"))
                                .andExpect(model().attribute("origin", "Lisboa"))
                                .andExpect(model().attribute("destination", "Porto"))
                                .andExpect(model().attribute("date", "2024-05-05"))
                                .andExpect(model().attribute("currency", "USD"))
                                .andExpect(model().attribute("results", tripsFound))
                                .andExpect(model().attribute("currencies", Arrays.asList("EUR", "USD")));
        }

        @Test
        void testCheckout() throws Exception {
                mvc.perform(get("/checkout")
                                .param("origin", "Lisboa")
                                .param("destination", "Porto")
                                .param("date", "2024-05-05")
                                .param("tripId", "1")
                                .param("currency", "EUR")
                                .param("finalPrice", "20.0"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("checkout"))
                                .andExpect(model().attribute("origin", "Lisboa"))
                                .andExpect(model().attribute("destination", "Porto"))
                                .andExpect(model().attribute("date", "2024-05-05"))
                                .andExpect(model().attribute("tripId", 1L))
                                .andExpect(model().attribute("currency", "EUR"))
                                .andExpect(model().attribute("finalPrice", 20.0));
        }

        @Test
        void testCreateReservation() throws Exception {
                when(reservationService.createReservation(any(ReservationDTO.class))).thenReturn(reservation);

                mvc.perform(post("/create-reservation")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(reservationDTO)))
                                .andExpect(status().isOk())
                                .andExpect(content().string("1234567890"));

        }

        @Test
        void testSuccess() throws Exception {
                mvc.perform(get("/success").param("reservationId", "1234567890"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("success"))
                                .andExpect(model().attribute("reservationId", "1234567890"));
        }

        @Test
        void testReservation() throws Exception {
                mvc.perform(get("/reservation"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("reservation"))
                                .andExpect(model().attribute("reservationRequest", notNullValue()));
        }

        @Test
        void testReservationResponse() throws Exception {
                when(reservationService.getReservationById("1234567890")).thenReturn(reservation);

                mvc.perform(post("/reservation")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .param("reservationId", "1234567890"))
                                .andExpect(status().isOk())
                                .andExpect(view().name("reservation"))
                                .andExpect(model().attribute("reservationResponse", reservation));
        }

}
