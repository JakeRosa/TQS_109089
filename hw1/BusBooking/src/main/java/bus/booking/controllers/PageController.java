package bus.booking.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import bus.booking.entities.Reservation;
import bus.booking.entities.Trip;
import bus.booking.entities.DTO.ReservationDTO;
import bus.booking.entities.DTO.ReservationRequest;
import bus.booking.services.impl.CurrencyServiceImpl;
import bus.booking.services.impl.ReservationServiceImpl;
import bus.booking.services.impl.TripServiceImpl;

@Controller
public class PageController {
    @Autowired
    private TripServiceImpl tripService;
    @Autowired
    private ReservationServiceImpl reservationService;
    @Autowired
    private CurrencyServiceImpl currencyService;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("cities", tripService.getAllCities());

        return "index";
    }

    @GetMapping("/results")
    public String results(@RequestParam String origin, @RequestParam String destination, @RequestParam String date,
            @RequestParam(required = false, defaultValue = "EUR") String currency, Model model) {
        // HashMap to store trip and price in the currency selected
        HashMap<Trip, Double> tripsFound = new HashMap<>();

        // add attributes from previous page
        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("date", date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);

        // depending on the currency, prices will be converted
        model.addAttribute("currency", currency);

        // converts the price to the selected currency
        for (Trip trip : tripService.getTripsByOriginDestinationDate(origin, destination, localDate)) {
            if (currency.equals("EUR")) {
                tripsFound.put(trip, trip.getPriceEuro());
            } else {
                tripsFound.put(trip, currencyService.convertCurrency(trip.getPriceEuro(), currency));
            }
        }

        // add the trips with the converted prices to the model
        model.addAttribute("results", tripsFound);

        // add the list of currencies to the model
        model.addAttribute("currencies", currencyService.getAllCurrencies());

        return "results";
    }

    @GetMapping("/checkout")
    public String checkout(@RequestParam String origin, @RequestParam String destination, @RequestParam String date,
            @RequestParam("tripId") Long tripId, @RequestParam String currency,
            @RequestParam("finalPrice") double finalPrice, Model model) {
        // add attributes from previous page
        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);
        model.addAttribute("date", date);
        model.addAttribute("tripId", tripId);

        // depending on the currency, we will convert the prices
        model.addAttribute("currency", currency);
        model.addAttribute("finalPrice", finalPrice);

        return "checkout";
    }

    @PostMapping("create-reservation")
    public ResponseEntity<String> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = reservationService.createReservation(reservationDTO);

        return ResponseEntity.ok(reservation.getId());
    }

    @GetMapping("/success")
    public String success(@RequestParam("reservationId") String reservationId, Model model) {
        model.addAttribute("reservationId", reservationId);

        return "success";
    }

    @GetMapping("/reservation")
    public String reservation(Model model) {
        model.addAttribute("reservationRequest", new ReservationRequest());
        return "reservation";
    }

    @PostMapping("/reservation")
    public String reservationResponse(@ModelAttribute("reservationRequest") ReservationRequest reservationRequest,
            Model model) {
        Reservation reservation = reservationService.getReservationById(reservationRequest.getReservationId());

        model.addAttribute("reservation", reservation);

        return "reservation";
    }
}
