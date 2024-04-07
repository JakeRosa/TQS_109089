package bus.booking;

import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import bus.booking.entities.Reservation;
import bus.booking.entities.Trip;
import bus.booking.entities.DTO.ReservationDTO;
import bus.booking.repositories.ReservationRepository;
import bus.booking.repositories.TripRepository;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class PageControllerIT {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private Trip trip1;

    @AfterEach
    public void resetDb() {
        reservationRepository.deleteAll();
        tripRepository.deleteAll();
    }

    @BeforeEach
    public void setUp() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        java.util.Date d1 = format.parse("08:00:00");
        java.sql.Time departureTime = new java.sql.Time(d1.getTime());

        java.util.Date d2 = format.parse("12:00:00");
        java.sql.Time arrivalTime = new java.sql.Time(d2.getTime());

        trip1 = new Trip(1L, "Lisboa", "Porto", LocalDate.parse("2024-05-05"), departureTime,
                arrivalTime, "4h", 10.0, "BUS007", 50);

        trip1 = tripRepository.save(trip1);
    }

    @Test
    public void testIndex() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("index"))
                .andExpect(model().attribute("cities",
                        new HashSet<>(Arrays.asList("Lisboa", "Porto"))));
    }

    @Test
    public void testResults() throws Exception {
        Map<Trip, Double> tripsFound = new HashMap<>();

        tripsFound.put(trip1, trip1.getPriceEuro());

        mvc.perform(get("/results").param("origin", "Lisboa").param("destination", "Porto").param("date", "2024-05-05")
                .param("currency", "EUR")).andExpect(status().isOk()).andExpect(view().name("results"))
                .andExpect(model().attribute("origin", "Lisboa"))
                .andExpect(model().attribute("destination", "Porto"))
                .andExpect(model().attribute("date", "2024-05-05"))
                .andExpect(model().attribute("currency", "EUR"))
                .andExpect(model().attribute("results", hasKey(trip1)))
                .andExpect(model().attribute("currencies",
                        Arrays.asList("EUR", "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AZN", "BBD",
                                "BDT",
                                "BGN", "BHD", "BIF", "BND", "BOB", "BRL", "BSD", "BWP", "BYN", "BZD", "CAD", "CHF",
                                "CLP", "CNY", "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP",
                                "ETB", "FJD", "GBP", "GEL", "GHS", "GMD", "GNF", "GTQ", "HKD", "HNL", "HRK", "HTG",
                                "HUF", "IDR", "ILS", "INR", "IQD", "ISK", "JMD", "JOD", "JPY", "KES", "KHR", "KRW",
                                "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD",
                                "MMK", "MOP", "MRO", "MUR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK",
                                "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON",
                                "RSD", "RUB", "RWF", "SAR", "SCR", "SDG", "SEK", "SGD", "SOS", "STD", "SVC", "SZL",
                                "THB", "TJS", "TND", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS",
                                "VEF", "VND", "XAF", "XCD", "XOF", "XPF", "ZAR", "ZMK")))
                .andExpect(model().attribute("results", hasValue(10.0)));
    }

    // doesn't work because host cache is not recognized
    @Test
    @Disabled
    public void testResultsWithUSD() throws Exception {
        Map<Trip, Double> tripsFound = new HashMap<>();

        tripsFound.put(trip1, trip1.getPriceEuro());

        mvc.perform(get("/results").param("origin", "Lisboa").param("destination", "Porto").param("date", "2024-05-05")
                .param("currency", "USD")).andExpect(status().isOk()).andExpect(view().name("results"))
                .andExpect(model().attribute("origin", "Lisboa"))
                .andExpect(model().attribute("destination", "Porto"))
                .andExpect(model().attribute("date", "2024-05-05"))
                .andExpect(model().attribute("currency", "USD"))
                .andExpect(model().attribute("results", hasKey(trip1)))
                .andExpect(model().attribute("currencies",
                        Arrays.asList("EUR", "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AZN", "BBD",
                                "BDT",
                                "BGN", "BHD", "BIF", "BND", "BOB", "BRL", "BSD", "BWP", "BYN", "BZD", "CAD", "CHF",
                                "CLP", "CNY", "COP", "CRC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD", "EGP",
                                "ETB", "FJD", "GBP", "GEL", "GHS", "GMD", "GNF", "GTQ", "HKD", "HNL", "HRK", "HTG",
                                "HUF", "IDR", "ILS", "INR", "IQD", "ISK", "JMD", "JOD", "JPY", "KES", "KHR", "KRW",
                                "KWD", "KYD", "KZT", "LAK", "LBP", "LKR", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD",
                                "MMK", "MOP", "MRO", "MUR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO", "NOK",
                                "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN", "PYG", "QAR", "RON",
                                "RSD", "RUB", "RWF", "SAR", "SCR", "SDG", "SEK", "SGD", "SOS", "STD", "SVC", "SZL",
                                "THB", "TJS", "TND", "TRY", "TTD", "TWD", "TZS", "UAH", "UGX", "USD", "UYU", "UZS",
                                "VEF", "VND", "XAF", "XCD", "XOF", "XPF", "ZAR", "ZMK")))
                .andExpect(model().attribute("results", not(hasValue(10.0))));
    }

    @Test
    public void testCheckout() throws Exception {
        mvc.perform(get("/checkout").param("origin", "Lisboa").param("destination", "Porto").param("date", "2024-05-05")
                .param("tripId", "1").param("currency", "EUR").param("finalPrice", "10.0")).andExpect(status().isOk())
                .andExpect(view().name("checkout")).andExpect(model().attribute("origin", "Lisboa"))
                .andExpect(model().attribute("destination", "Porto")).andExpect(model().attribute("date", "2024-05-05"))
                .andExpect(model().attribute("tripId", 1L)).andExpect(model().attribute("currency", "EUR"))
                .andExpect(model().attribute("finalPrice", 10.0));
    }

    @Test
    public void testCreateReservation() throws Exception {
        ReservationDTO reservationDTO = new ReservationDTO("John Smith Carabina", "john.smith.carabina@example.com",
                "123456789", new java.util.Date(), "Visa", "EUR", trip1.getId(), 20.0);

        mvc.perform(post("/create-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(reservationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string(hasLength(16)));
    }

    @Test
    public void testSuccess() throws Exception {
        Reservation reservation = new Reservation("1234567890", "John Smith Carabina",
                "john.smith.carabina@example.com", "123456789", new java.util.Date(), "Visa", "EUR", 20.0, trip1);

        reservationRepository.save(reservation);

        mvc.perform(get("/success").param("reservationId", "1234567890")).andExpect(status().isOk())
                .andExpect(view().name("success")).andExpect(model().attribute("reservationId", "1234567890"));
    }

    @Test
    public void testReservation() throws Exception {
        mvc.perform(get("/reservation")).andExpect(status().isOk()).andExpect(view().name("reservation"))
                .andExpect(model().attribute("reservationRequest", notNullValue()));
    }

    @Test
    public void testReservationResponse() throws Exception {
        Reservation reservation = new Reservation("1234567890", "John Smith Carabina",
                "john.smith.carabina@example.com", "123456789", new java.util.Date(), "Visa", "EUR", 20.0, trip1);

        reservationRepository.save(reservation);

        mvc.perform(post("/reservation")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("reservationId", "1234567890"))
                .andExpect(status().isOk())
                .andExpect(view().name("reservation"))
                .andExpect(model().attribute("reservation", reservation));
    }

}
